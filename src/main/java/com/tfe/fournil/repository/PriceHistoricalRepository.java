package com.tfe.fournil.repository;


import com.tfe.fournil.entity.ProductVersion;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * The interface Price historical repository.
 */
public interface PriceHistoricalRepository extends JpaRepository<ProductVersion,Long> {
}

