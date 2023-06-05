package com.tfe.fournil.repository;

import com.tfe.fournil.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * The interface Role repository.
 */
public interface RoleRepository extends JpaRepository<Role,Integer> {
    /**
     * Find by role role.
     *
     * @param role the role
     * @return the role
     */
    Role findByRole(String role);
}
