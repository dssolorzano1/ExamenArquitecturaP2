package com.banquito.core.branch_management.sucursal.controlador;

import com.banquito.core.branch_management.sucursal.controlador.dto.BranchDto;
import com.banquito.core.branch_management.sucursal.controlador.adaptador.BranchAdaptador;
import com.banquito.core.branch_management.sucursal.controlador.mapper.BranchMapper;
import com.banquito.core.branch_management.sucursal.controlador.peticion.BranchPeticion;
import com.banquito.core.branch_management.sucursal.excepcion.NotFoundException;
import com.banquito.core.branch_management.sucursal.modelo.Branch;
import com.banquito.core.branch_management.sucursal.servicios.BranchService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping("v1/branches")
@CrossOrigin("*")
public class BranchControlador {

    private final BranchService servicio;
    private final BranchAdaptador adaptador;
    private final BranchMapper mapper;

    public BranchControlador(BranchService servicio, BranchAdaptador adaptador, BranchMapper mapper) {
        this.servicio = servicio;
        this.adaptador = adaptador;
        this.mapper = mapper;
    }

    /**
     * Obtener una sucursal por su ID
     */
    @Operation(summary = "Obtener una sucursal por su ID", description = "Devuelve los detalles de una sucursal específica basado en su ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Sucursal encontrada", content = @Content(mediaType = "application/json", schema = @Schema(implementation = BranchDto.class))),
            @ApiResponse(responseCode = "404", description = "Sucursal no encontrada", content = @Content)
    })
    @GetMapping("/{id}")
    public ResponseEntity<BranchDto> mostrar(
            @Parameter(description = "ID de la sucursal que se desea buscar", required = true) @PathVariable("id") Integer id) {
        try {
            Branch branch = this.servicio.buscarPorId(id);
            return ResponseEntity.ok(this.mapper.toDto(branch));
        } catch (NotFoundException nfe) {
            log.error("Error al buscar sucursal con ID {}: no encontrada.", id, nfe);
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Modificar una sucursal existente
     */
    @Operation(summary = "Modificar una sucursal existente", description = "Permite actualizar los datos de una sucursal basado en su ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Sucursal actualizada exitosamente", content = @Content(mediaType = "application/json", schema = @Schema(implementation = BranchDto.class))),
            @ApiResponse(responseCode = "404", description = "Sucursal no encontrada", content = @Content)
    })
    @PutMapping("/{id}")
    public ResponseEntity<?> modificar(
            @Parameter(description = "ID de la sucursal a modificar", required = true) @PathVariable Integer id,
            @RequestBody BranchPeticion peticion) {
        try {
            Branch branch = this.adaptador.peticionASucursal(id, peticion);
            this.servicio.actualizar(branch);
            return ResponseEntity.ok(mapper.toDto(branch));
        } catch (NotFoundException nfe) {
            log.error("Error al modificar sucursal con ID {}: no encontrada.", id, nfe);
            return ResponseEntity.status(404).body("Sucursal no encontrada");
        } catch (Exception ex) {
            log.error("Error inesperado al modificar sucursal con ID {}.", id, ex);
            return ResponseEntity.status(500).body("Error interno del servidor");
        }
    }
}
