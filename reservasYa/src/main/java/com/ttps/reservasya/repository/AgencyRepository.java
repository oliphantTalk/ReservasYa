package com.ttps.reservasya.repository;

import com.ttps.reservasya.models.businessentity.Agency;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AgencyRepository extends JpaRepository<Agency, Long> {

}
