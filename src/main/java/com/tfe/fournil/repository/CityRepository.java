package com.tfe.fournil.repository;

import com.tfe.fournil.entity.City;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * The interface City repository.
 */
public interface CityRepository extends JpaRepository<City,Long> {
}
