package com.ttps.reservasya.services.hotel;

import com.ttps.reservasya.models.businessitem.agency.cars.Car;
import com.ttps.reservasya.repository.hotel.HotelRepository;
import com.ttps.reservasya.models.businessitem.hotel.Hotel;
import com.ttps.reservasya.models.businessitem.hotel.Room;
import com.ttps.reservasya.repository.hotel.room.RoomRepository;
import com.ttps.reservasya.services.BasicCrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

    public List<Room> searchHotelForDestination(String city, int passenger){
        List<Room> roomList = new ArrayList<>();
        repository.findHotelsByCity(city)
                .orElse(new ArrayList<>())
                .forEach(hotel -> roomList.addAll(
                        hotel.getRooms()
                                .stream()
                                .filter(room -> passenger >= room.getBeds())
                                .collect(Collectors.toList())
                )
                );
        return roomList;
    }
}
