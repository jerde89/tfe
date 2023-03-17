package com.tfe.fournil.repository;

import com.tfe.fournil.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role,Integer> {
    Role findByRole(String role);
}
