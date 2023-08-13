package io.upschool.service;

import io.upschool.dto.*;
import io.upschool.entity.*;
import io.upschool.exception.AirplaneReferenceException;
import io.upschool.exception.AirplanefindAllByNameException;
import io.upschool.exception.CustomerCheckException;
import io.upschool.repository.AirplaneRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class AirplaneService {
    private final AirplaneRepository airplaneRepository;

    public AirplaneSaveResponse save(AirplaneSaveRequest airplaneSaveRequest) {
        var newAirplane = Airplane.builder()
                .airplaneName(airplaneSaveRequest.getAirplaneName())
                .capacity(airplaneSaveRequest.getCapacity())
                .build();
        Airplane savedAirplane = airplaneRepository.save(newAirplane);
        return AirplaneSaveResponse.builder()
                .id(savedAirplane.getId())
                .airplaneName(savedAirplane.getAirplaneName())
                .capacity(savedAirplane.getCapacity())
                .build();
    }
    public AirplaneSaveResponse update(AirplaneSaveRequest request) {
        var airplane = airplaneRepository.getAirplaneById(request.getId());
        if (airplane.size() > 0) {
            Airplane updateAirplane = airplane.get(0);
            updateAirplane.setAirplaneName(request.getAirplaneName());
            updateAirplane.setCapacity(request.getCapacity());
            updateAirplane = airplaneRepository.save(updateAirplane);
            return AirplaneSaveResponse.builder()
                    .id(updateAirplane.getId())
                    .airplaneName(updateAirplane.getAirplaneName())
                    .capacity(updateAirplane.getCapacity())
                    .build();
        } else
            throw new CustomerCheckException("Girilen Id'ye ait uçak bulunamadı.");
    }

    public List<Airplane> getAllAirplanes() {
        return airplaneRepository.findAll();
    }


    public List<Airplane> getAirplaneById(Long id) {
        List<Airplane> airplaneByReference = airplaneRepository.getAirplaneById(id);
        if (airplaneByReference.size() == 0)
            throw new AirplaneReferenceException("Belirtilen Id'ye ait uçak bulunamadı.");
        return airplaneByReference;
    }
    public List<Airplane> findAllByAirplaneNameContainingIgnoreCase(String name) {
        List<Airplane> airplanes=airplaneRepository.findAllByAirplaneNameContainingIgnoreCase(name);
        if (airplanes.size()==0)
            throw  new AirplanefindAllByNameException("Girilen isimde uçak bulunamadı.");
        return airplanes;
    }
}
