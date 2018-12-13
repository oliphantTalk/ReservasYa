package com.ttps.reservasya.repository.agency;

import com.ttps.reservasya.models.businessitem.agency.Agency;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AgencyRepository extends JpaRepository<Agency, Long> {

}
