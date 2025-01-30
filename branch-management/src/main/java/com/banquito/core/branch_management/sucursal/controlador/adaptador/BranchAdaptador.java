package com.banquito.core.branch_management.sucursal.controlador.adaptador;

import com.banquito.core.branch_management.sucursal.controlador.peticion.BranchPeticion;
import com.banquito.core.branch_management.sucursal.modelo.Branch;
import com.banquito.core.branch_management.sucursal.servicios.BranchService;
import org.springframework.stereotype.Component;

@Component
public class BranchAdaptador {

    private final BranchService servicio;

    public BranchAdaptador(BranchService servicio) {
        this.servicio = servicio;
    }

    public Branch peticionASucursal(Integer id, BranchPeticion peticion) {
        Branch branch = servicio.buscarPorId(id);
        branch.setName(peticion.getName());
        branch.setEmailAddress(peticion.getEmailAddress());
        branch.setPhoneNumber(peticion.getPhoneNumber());
        branch.setState(peticion.getState());
        return branch;
    }
}
