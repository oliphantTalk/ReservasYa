package com.ttps.reservasya.services.modelcrud;

import com.ttps.reservasya.models.businessentity.Hotel;
import com.ttps.reservasya.models.businessitem.Room;
import com.ttps.reservasya.repository.HotelRepository;
import com.ttps.reservasya.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HotelService extends BasicCrudService<Hotel, HotelRepository> {

    private RoomRepository roomRepository;

    public HotelService(){};
    @Autowired
    public HotelService(HotelRepository hotelRepository, RoomRepository roomRepository){
        super(hotelRepository);
        this.roomRepository = roomRepository;
    }

    public Optional<Room> findRoom(Long id){
        return roomRepository.findById(id);
    }

    public List<Room> findRooms(){
        return roomRepository.findAll();
    }

    public Room createRoom(Room room){
        return roomRepository.save(room);
    }

    public List<Room> createRooms(List<Room> rooms){
        return roomRepository.saveAll(rooms);
    }

    public Room updateRoom(Room room){
        return roomRepository.save(room);
    }

    public void deleteRoom(Long id){
        roomRepository.deleteById(id);
    }
}
