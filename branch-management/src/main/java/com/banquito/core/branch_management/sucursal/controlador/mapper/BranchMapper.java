package com.banquito.core.branch_management.sucursal.controlador.mapper;

import com.banquito.core.branch_management.sucursal.controlador.dto.BranchDto;
import com.banquito.core.branch_management.sucursal.modelo.Branch;
import org.springframework.stereotype.Component;

@Component
public class BranchMapper {

    public BranchDto toDto(Branch branch) {
        BranchDto dto = new BranchDto();
        dto.setId(branch.getId());
        dto.setName(branch.getName());
        dto.setEmailAddress(branch.getEmailAddress());
        dto.setPhoneNumber(branch.getPhoneNumber());
        dto.setState(branch.getState());
        return dto;
    }
}
