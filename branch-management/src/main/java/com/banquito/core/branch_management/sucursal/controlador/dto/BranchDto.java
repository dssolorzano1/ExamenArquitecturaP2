package com.banquito.core.branch_management.sucursal.controlador.dto;

import lombok.Data;

@Data
public class BranchDto {
    private Integer id;
    private String name;
    private String emailAddress;
    private String phoneNumber;
    private String state;
}
