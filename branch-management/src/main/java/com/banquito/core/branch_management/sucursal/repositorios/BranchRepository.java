package com.banquito.core.branch_management.sucursal.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;

import com.banquito.core.branch_management.sucursal.modelo.Branch;

import java.util.Optional;

public interface BranchRepository extends JpaRepository<Branch, Integer> {

    Optional<Branch> findByName(String name);

    Optional<Branch> findByState(String state);
}
