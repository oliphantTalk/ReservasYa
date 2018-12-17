package com.ttps.reservasya.repository.hotel;

import com.ttps.reservasya.models.businessitem.hotel.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface HotelRepository extends JpaRepository<Hotel, Long> {

    Optional<List<Hotel>> findHotelsByCity(String city);

}
