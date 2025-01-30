package com.banquito.core.branch_management.sucursal.controlador.peticion;

import lombok.Data;

@Data
public class BranchPeticion {
    private String name;
    private String emailAddress;
    private String phoneNumber;
    private String state;
}
