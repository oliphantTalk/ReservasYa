package com.ttps.reservasya.services.hotel;

import com.ttps.reservasya.controllers.panel.form.ABMHotelForm;
import com.ttps.reservasya.controllers.panel.form.ABMRoomForm;
import com.ttps.reservasya.error.exceptions.NoElementInDBException;
import com.ttps.reservasya.models.businessitem.hotel.Hotel;
import com.ttps.reservasya.models.businessitem.hotel.Room;
import com.ttps.reservasya.repository.hotel.HotelRepository;
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

    public Room findRoom(Long id){
        return roomRepository.findById(id).orElseThrow(NoElementInDBException::new);
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

    public List<Room> searchHotelForDestination(String city, int passenger, int stars){
        List<Room> roomList = new ArrayList<>();
        repository.findHotelsByCityAndStarsGreaterThanEqualOrderByStarsAsc(city, stars)
                .orElse(new ArrayList<>())
                .forEach(hotel -> roomList.addAll(
                        hotel.getRooms()
                                .stream()
                                .filter(room -> passenger <= room.getBeds())
                                .collect(Collectors.toList())
                )
                );
        return roomList;
    }

    public Hotel addHotel(ABMHotelForm hotelForm){
        Hotel hotel = new Hotel();
        hotel.setName(hotelForm.getAddHotelName());
        hotel.setCity(hotelForm.getAddHotelCity());
        hotel.setStars(hotelForm.getAddHotelStars());
        return createOne(hotel);
    }
    public Hotel editHotel(ABMHotelForm hotelForm){
        Hotel hotel = repository.findById(hotelForm.getEditHotelId()).orElseThrow(NoElementInDBException::new);
        hotel.setName(hotelForm.getEditHotelName());
        hotel.setCity(hotelForm.getEditHotelCity());
        hotel.setStars(hotelForm.getEditHotelStars());
        return updateOne(hotel);
    }
    public Hotel deleteHotel(ABMHotelForm hotelForm){
        Hotel hotel = repository.findById(hotelForm.getDeleteHotelId()).orElseThrow(NoElementInDBException::new);
        repository.delete(hotel);
        return hotel;

    }

    public Room addRoom(ABMRoomForm roomForm) {
        Room room = new Room();
        room.setHotel(repository.findById(roomForm.getAddHotelId()).orElseThrow(NoElementInDBException::new));
        room.setBeds(roomForm.getAddRoomBeds());
        room.setRoomId(roomForm.getAddRoomId());
        room.setPrice(roomForm.getAddRoomPrice());
        return createRoom(room);
    }

    public Room removeRoom(Long deleteRoomId) {
        Room room = roomRepository.findById(deleteRoomId).orElseThrow(NoElementInDBException::new);
        deleteRoom(deleteRoomId);
        return room;
    }
}
