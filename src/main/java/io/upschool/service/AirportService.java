package io.upschool.service;

import io.upschool.dto.*;
import io.upschool.entity.Airport;
import io.upschool.entity.City;
import io.upschool.exception.AirportReferenceByIdException;
import io.upschool.exception.AirportfindAllByNameException;
import io.upschool.exception.CityReferenceByIdException;
import io.upschool.repository.AirportRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class AirportService {
    private final AirportRepository airportRepository;
    private final CityService cityService;

    @Transactional
    public AirportSaveResponse save(AirportSaveRequest request) {
      List< City> cityByReference = cityService.getCityById(request.getCityId());
        var newAirport = Airport.builder()
                .name(request.getAirportname())
                .city(cityByReference.get(0))
                .build();
        var airportResponse = airportRepository.save(newAirport);

        return AirportSaveResponse.builder()
                .id(airportResponse.getId())
                .airportName(airportResponse.getName())
                .cityName(airportResponse.getCity().getName())
                .build();
    }
    public AirportSaveResponse update(AirportSaveRequest request) {
        var airport = airportRepository.getAirportById(request.getId());
        if (airport.size() > 0) {
            Airport updateAirport = airport.get(0);
            updateAirport.setName(request.getAirportname());
            updateAirport.setCity(cityService.getCityById(request.getCityId()).get(0));
            updateAirport = airportRepository.save(updateAirport);
            return AirportSaveResponse.builder()
                    .id(updateAirport.getId())
                    .airportName(updateAirport.getName())
                    .cityName(updateAirport.getCity().getName())
                    .build();
        } else
            throw new AirportReferenceByIdException("Girilen Id'ye ait havaalanı bulunamadı.");
    }


    public List<Airport> getAllAirports() {
        return airportRepository.findAll();
    }

    public List<Airport> findAllByNameIsContainingIgnoreCase(String name) {
        List<Airport> airports=airportRepository.findAllByNameIsContainingIgnoreCase(name);
        if (airports.size()==0)
            throw  new AirportfindAllByNameException("Girilen isimde havaalanı bulunamadı.");
        return airports;
    }

    public List<Airport> getAirportById(Long id) {
        List<Airport> airportByReference=airportRepository.getAirportById(id);
        if (airportByReference.size()==0)
            throw new AirportReferenceByIdException("Girilen Id'ye ait havaalanı bulunamadı.");
        return airportByReference;
    }
}

