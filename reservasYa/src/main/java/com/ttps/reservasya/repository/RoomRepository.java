package com.ttps.reservasya.repository;

import com.ttps.reservasya.models.businessitem.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomRepository extends JpaRepository<Room, Long> {
}
