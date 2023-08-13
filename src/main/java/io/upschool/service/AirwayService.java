package io.upschool.service;

import io.upschool.dto.AirportSaveRequest;
import io.upschool.dto.AirportSaveResponse;
import io.upschool.dto.AirwaySaveRequest;
import io.upschool.dto.AirwaySaveResponse;
import io.upschool.entity.Airport;
import io.upschool.entity.Airway;
import io.upschool.exception.AirportReferenceByIdException;
import io.upschool.exception.AirwayReferenceByIdException;
import io.upschool.exception.AirwayfindAllByNameException;
import io.upschool.repository.AirwayRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class AirwayService {
    private final AirwayRepository airwayRepository;
    private final AirportService airportService;

    public AirwaySaveResponse save(AirwaySaveRequest request) {
        List<Airport> airportByReference = airportService.getAirportById(request.getAirportId());
        var newAirway = Airway.builder()
                .name(request.getAirwayName())
                .airpot(airportByReference.get(0))
                .build();
        var airwayResponse = airwayRepository.save(newAirway);

        return AirwaySaveResponse.builder().build().builder()
                .id(airwayResponse.getId())
                .airwayName(airwayResponse.getName())
                .airportName(airwayResponse.getAirpot().getName())
                .build();
    }

    public AirwaySaveResponse update(AirwaySaveRequest request) {
        List<Airway> airway = airwayRepository.getAirwayById(request.getId());
        if (airway.size() > 0) {
            Airway updateAirway = airway.get(0);
            updateAirway.setName(request.getAirwayName());
            updateAirway.setAirpot(airportService.getAirportById(request.getAirportId()).get(0));

            updateAirway = airwayRepository.save(updateAirway);
            return AirwaySaveResponse.builder()
                    .id(updateAirway.getId())
                    .airwayName(updateAirway.getName())
                    .airportName(updateAirway.getAirpot().getName())
                    .build();
        } else
            throw new AirwayReferenceByIdException("Girilen Id'ye ait havayolu bulunamadı.");
    }


    public List<Airway> getAllAirways() {
        return airwayRepository.findAll();
    }

    public List<Airway> findAllByNameIsContainingIgnoreCase(String name) {
        List<Airway> airways = airwayRepository.findAllByNameIsContainingIgnoreCase(name);
        if (airways.size() == 0)
            throw new AirwayfindAllByNameException("Girilen isme ait havayolu bulunamadı.");
        return airways;
    }

    @Transactional()
    public List<Airway> getAirwayById(Long id) {
        List<Airway> airwayByReference = airwayRepository.getAirwayById(id);
        if (airwayByReference.size() == 0)
            throw new AirwayReferenceByIdException("Belirtilen Id'ye ait havayolu bulunamadı.");
        return airwayByReference;
    }

}
