package io.upschool.service;

import io.upschool.dto.CustomerSaveRequest;
import io.upschool.dto.CustomerSaveResponse;
import io.upschool.dto.FlightSaveRequest;
import io.upschool.dto.FlightSaveResponse;
import io.upschool.entity.*;
import io.upschool.exception.AirplaneReferenceException;
import io.upschool.exception.AirwayReferenceByIdException;
import io.upschool.exception.CityReferenceByIdException;
import io.upschool.exception.CustomerCheckException;
import io.upschool.repository.FlightRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class FlightService {
    private final FlightRepository flightRepository;
    private final AirwayService airwayService;
    private final AirplaneService airplaneService;

    @Transactional
    public FlightSaveResponse save(FlightSaveRequest request) {
        List<Airway> airwayByReference = airwayService.getAirwayById(request.getAirwayId());
        List<Airplane> airplaneByReference = airplaneService.getAirplaneById(request.getAirplaneId());
        var newFlight = Flight.builder()
                .cityOfMovement(request.getMovementCity())
                .destinationCity(request.getDestinationCity())
                .flightDate(request.getFlightDate())
                .airway(airwayByReference.get(0))
                .airplane(airplaneByReference.get(0))
                .build();
        var FlightResponse = flightRepository.save(newFlight);

        return FlightSaveResponse.builder().build().builder()
                .id(FlightResponse.getId())
                .movementCity(FlightResponse.getCityOfMovement())
                .destinationCity(FlightResponse.getDestinationCity())
                .flightDate(FlightResponse.getFlightDate())
                .airplaneName(FlightResponse.getAirplane().getAirplaneName())
                .airwayName(FlightResponse.getAirway().getName())
                .build();
    }

    public FlightSaveResponse update(FlightSaveRequest request) {
        var flight = flightRepository.getFlightById(request.getId());
        if (flight.size() > 0) {
            Flight updateflight = flight.get(0);
            updateflight.setCityOfMovement(request.getMovementCity());
            updateflight.setDestinationCity(request.getDestinationCity());
            updateflight.setFlightDate(request.getFlightDate());
            updateflight.setAirway(airwayService.getAirwayById(request.getId()).get(0));
            updateflight.setAirplane(airplaneService.getAirplaneById(request.getId()).get(0));
            updateflight = flightRepository.save(updateflight);
            return FlightSaveResponse.builder().build().builder()
                    .id(updateflight.getId())
                    .movementCity(updateflight.getCityOfMovement())
                    .destinationCity(updateflight.getDestinationCity())
                    .flightDate(updateflight.getFlightDate())
                    .airplaneName(updateflight.getAirplane().getAirplaneName())
                    .airwayName(updateflight.getAirway().getName())
                    .build();
        } else
            throw new CustomerCheckException("Girilen Id'ye ait uçuş bilgisi bulunamadı.");
    }

    public List<Flight> getAllFlights() {
        return flightRepository.findAll();
    }

    public List<Flight> findAllByCityOfMovementAndDestinationCity(String city1, String city2) {
        return flightRepository.findAllByCityOfMovementAndDestinationCity(city1, city2);
    }

    @Transactional()
    public List<Flight> getFlightById(Long id) {
        List<Flight> flightByReference = flightRepository.getFlightById(id);
        if (flightByReference.size() == 0)
            throw new CityReferenceByIdException("Girilen Id'ye ait uçuş bilgisi bulunmadı.");
        return flightByReference;

    }
}
