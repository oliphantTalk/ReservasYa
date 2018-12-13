package com.ttps.reservasya.repository.hotel;

import com.ttps.reservasya.models.businessitem.hotel.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HotelRepository extends JpaRepository<Hotel, Long> {
}
