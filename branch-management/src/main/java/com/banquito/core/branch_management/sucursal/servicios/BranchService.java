package com.banquito.core.branch_management.sucursal.servicios;

import com.banquito.core.branch_management.sucursal.excepcion.NotFoundException;
import com.banquito.core.branch_management.sucursal.modelo.Branch;
import com.banquito.core.branch_management.sucursal.repositorios.BranchRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BranchService {

    private final BranchRepository repositorio;
    
    public static final String ENTITY_NAME = "Sucursal";
    
    public static final String ACTIVO = "ACT";
    public static final String INACTIVO = "INA";

    public List<Branch> obtenerTodas() {
        return repositorio.findAll();
    }

    public Branch buscarPorId(Integer id) {
        return this.repositorio.findById(id)
                .orElseThrow(() -> new NotFoundException(id.toString(), ENTITY_NAME));
    }

    public void registrarSucursal(Branch branch) {
        branch.setState(BranchService.ACTIVO);
        branch.setFechaCreacion(LocalDateTime.now(ZoneId.systemDefault()));
        branch.setFechaActualizacion(LocalDateTime.now(ZoneId.systemDefault()));

        repositorio.save(branch);
    }

    public void actualizar(Branch branch) {
        branch.setFechaActualizacion(LocalDateTime.now(ZoneId.systemDefault()));
        repositorio.save(branch);
    }

    public void actualizarTelefono(Integer id, String nuevoTelefono) {
        Branch branch = buscarPorId(id);
        branch.setPhoneNumber(nuevoTelefono);
        branch.setFechaActualizacion(LocalDateTime.now(ZoneId.systemDefault()));
        repositorio.save(branch);
    }

    public void eliminarSucursal(Integer id) {
        if (!repositorio.existsById(id)) {
            throw new NotFoundException(id.toString(), ENTITY_NAME);
        }
        repositorio.deleteById(id);
    }
}
