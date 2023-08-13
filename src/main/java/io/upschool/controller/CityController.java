package io.upschool.controller;

import io.upschool.dto.*;
import io.upschool.entity.City;
import io.upschool.service.CityService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/citys")
@RequiredArgsConstructor
public class CityController {
    private final CityService cityService;

    @GetMapping("/citys")
    public ResponseEntity<BaseResponse<List<City>>> getCitys() {
        List<City> citys = cityService.getAllCitys();
        BaseResponse<List<City>> response = BaseResponse.<List<City>>builder()
                .status(HttpStatus.OK.value())
                .isSuccess(true)
                .data(citys)
                .build();
        return ResponseEntity.ok(response);
    }
    @GetMapping("/searchcity")
    public ResponseEntity<BaseResponse<List<City>>> searchCity(@RequestBody CitySaveRequest request) {
        List<City> citys = cityService.findAllByNameIsContainingIgnoreCase(request.getName());
        BaseResponse<List<City>> response = BaseResponse.<List<City>>builder()
                .status(HttpStatus.OK.value())
                .isSuccess(true)
                .data(citys)
                .build();
        return ResponseEntity.ok(response);
    }

    @PutMapping("/updatecity")
    public ResponseEntity<Object> updateCity(@RequestBody CitySaveRequest request) {
        var updateCity=cityService.update(request);
        var response = BaseResponse.<CitySaveResponce>builder()
                .status(HttpStatus.OK.value())
                .isSuccess(true)
                .data(updateCity)
                .build();
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<Object> createCity(@Valid @RequestBody CitySaveRequest citySaveRequest) {
        var citySaveResponce = cityService.save(citySaveRequest);
        var response = BaseResponse.<CitySaveResponce>builder()
                .status(HttpStatus.CREATED.value())
                .isSuccess(true)
                .data(citySaveResponce)
                .build();
        return ResponseEntity.ok(response);

    }
}
