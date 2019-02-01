package com.ttps.reservasya.services.agencies;

import com.ttps.reservasya.controllers.panel.form.ABMAgencyForm;
import com.ttps.reservasya.controllers.panel.form.ABMCarForm;
import com.ttps.reservasya.error.exceptions.NoElementInDBException;
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
                                .filter(car -> passenger <= car.getCapacity())
                                .collect(Collectors.toList()
                                )
                )
                );
        return carList;
    }

    public Agency addAgency(ABMAgencyForm agencyForm){
        Agency agency = new Agency();
        agency.setName(agencyForm.getAddAgencyName());
        agency.setCity(agencyForm.getAddAgencyCity());
        return createOne(agency);
    }

    public Agency editAgency(ABMAgencyForm agencyForm){
        Agency agency = repository.findById(agencyForm.getEditAgencyId()).orElseThrow(NoElementInDBException::new);
        agency.setName(agencyForm.getEditAgencyName());
        agency.setCity(agencyForm.getEditAgencyCity());
        return updateOne(agency);
    }

    public Agency deleteAgency(ABMAgencyForm agencyForm){
        Agency agency = repository.findById(agencyForm.getDeleteAgencyId()).orElseThrow(NoElementInDBException::new);
        repository.delete(agency);
        return agency;
    }

    public Car addCar(ABMCarForm carForm) {
        Car car = new Car();
        car.setAgency(repository.findById(carForm.getAddAgencyId()).orElseThrow(NoElementInDBException::new));
        car.setPrice(carForm.getAddCarPrice());
        car.setCapacity(carForm.getAddCarCapacity());
        car.setDescription(carForm.getAddCarDescription());
        car.setModel(carForm.getAddCarModel());
        car.setPatent(carForm.getAddCarPatent());
        car.setYear(carForm.getAddCarYear());
        return createCar(car);
    }

    public Car removeCar(Long deleteCarId) {
        Car car = carRepository.findById(deleteCarId).orElseThrow(NoElementInDBException::new);
        deleteCar(deleteCarId);
        return car;
    }
}
