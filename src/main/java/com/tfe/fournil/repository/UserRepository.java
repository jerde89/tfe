package com.tfe.fournil.repository;

import com.tfe.fournil.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
}
