package com.ttps.reservasya.repository;

import com.ttps.reservasya.models.LocalParameters;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LocalParametersRepository extends JpaRepository<LocalParameters, Long> {

}
