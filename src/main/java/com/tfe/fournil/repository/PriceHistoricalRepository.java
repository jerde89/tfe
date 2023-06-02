package com.tfe.fournil.repository;


import com.tfe.fournil.entity.ProductVersion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PriceHistoricalRepository extends JpaRepository<ProductVersion,Long> {
}
