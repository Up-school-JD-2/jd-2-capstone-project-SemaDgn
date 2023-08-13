package io.upschool.service;

import io.upschool.dto.AirplaneSaveRequest;
import io.upschool.dto.AirplaneSaveResponse;
import io.upschool.dto.CitySaveRequest;
import io.upschool.dto.CitySaveResponce;
import io.upschool.entity.Airplane;
import io.upschool.entity.City;
import io.upschool.exception.AirplanefindAllByNameException;
import io.upschool.exception.CityReferenceByIdException;
import io.upschool.exception.CustomerCheckException;
import io.upschool.repository.CityRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class CityService {
    private final CityRepository citysRepository;

    public CitySaveResponce save(CitySaveRequest citySaveRequest) {
        var newCity = City
                .builder()
                .name(citySaveRequest.getName())
                .build();
        City savedCity = citysRepository.save(newCity);
        return CitySaveResponce
                .builder()
                .id(savedCity.getId())
                .cityName(savedCity.getName()).build();

    }
    public CitySaveResponce update(CitySaveRequest request) {
        var city = citysRepository.getCityById(request.getId());
        if (city.size() > 0) {
            City updateCity = city.get(0);
            updateCity.setName(request.getName());
            updateCity = citysRepository.save(updateCity);
            return CitySaveResponce.builder()
                    .id(updateCity.getId())
                    .cityName(updateCity.getName())
                    .build();
        } else
            throw new CityReferenceByIdException("Girilen Id'ye ait şehir bulunamadı.");
    }

    public List<City> getAllCitys() {
        return citysRepository.findAll();
    }

    @Transactional()
    public List<City> getCityById(Long id) {
        List<City> cityByReference = citysRepository.getCityById(id);
        if (cityByReference.size() == 0)
            throw new CityReferenceByIdException("Girilen Id'ye ait şehir bulunmadı.");
        return cityByReference;
    }

    public List<City> findAllByNameIsContainingIgnoreCase(String name) {
        List<City> citys = citysRepository.findAllByNameIsContainingIgnoreCase(name);
        if (citys.size() == 0)
            throw new CityReferenceByIdException("Girilen isimde şehir bulunamadı.");
        return citys;
    }


}
