package com.ttps.reservasya.services.agencies;

import com.ttps.reservasya.models.businessitem.agency.Agency;
import com.ttps.reservasya.models.businessitem.agency.cars.Car;
import com.ttps.reservasya.repository.agency.AgencyRepository;
import com.ttps.reservasya.repository.agency.CarRepository;
import com.ttps.reservasya.services.BasicCrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AgencyService extends BasicCrudService<Agency, AgencyRepository> {

    private CarRepository carRepository;

    public AgencyService(){};

    @Autowired
    public AgencyService(AgencyRepository agencyRepository, CarRepository carRepository) {
        super(agencyRepository);
        this.carRepository = carRepository;
    }

    public Optional<Car> findCar(Long id){
        return this.carRepository.findById(id);
    }

    public List<Car> findCars(){ return carRepository.findAll();}

    public Car createCar(Car car){
        return this.carRepository.save(car);
    }

    public List<Car> createCars(List<Car>  cars) {
        return carRepository.saveAll(cars);
    }

    public Car updateCar(Car car){
        return this.carRepository.save(car);
    }

    public void deleteCar(Long id){
        this.carRepository.deleteById(id);
    }

    public List<Car> searchCarForDestination(String city, int passenger){
        List<Car> carList = new ArrayList<>();
        repository.findAgenciesByCity(city)
                .orElse(new ArrayList<>())
                .forEach( cars ->  carList.addAll(
                        cars.getCars()
                                .stream()
                                .filter(car -> passenger >= car.getCapacity())
                                .collect(Collectors.toList()
                                )
                )
                );
        return carList;

    }
}
