package com.ttps.reservasya.repository;

import com.ttps.reservasya.models.businessItem.Room;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomRepository extends JpaRepository<Room, Long> {
}
