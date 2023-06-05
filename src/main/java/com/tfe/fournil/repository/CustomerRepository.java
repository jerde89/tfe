package com.tfe.fournil.repository;

import com.tfe.fournil.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * The interface Customer repository.
 */
public interface CustomerRepository extends JpaRepository<Customer,Long> {
}