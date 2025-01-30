package com.banquito.core.branch_management.sucursal.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.banquito.core.branch_management.sucursal.modelo.BranchHoliday;

import java.util.List;

@Repository
public interface BranchHolidayRepository extends JpaRepository<BranchHoliday, Integer> {
    List<BranchHoliday> findByBranchId(Integer branchId);
}
