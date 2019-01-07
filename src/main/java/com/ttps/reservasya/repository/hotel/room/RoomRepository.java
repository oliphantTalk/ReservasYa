package com.ttps.reservasya.repository.hotel.room;

import com.ttps.reservasya.models.businessitem.hotel.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomRepository extends JpaRepository<Room, Long> {
}
