package com.ttps.reservasya.services.modelcrud;

import com.ttps.reservasya.models.businessentity.Agency;
import com.ttps.reservasya.models.businessitem.Car;
import com.ttps.reservasya.repository.AgencyRepository;
import com.ttps.reservasya.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
}
