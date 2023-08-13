package io.upschool.controller;

import io.upschool.dto.*;
import io.upschool.entity.Airplane;
import io.upschool.entity.Airport;
import io.upschool.entity.Airway;
import io.upschool.service.AirplaneService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/airplanes")
@RequiredArgsConstructor
public class AirplaneController {
    private final AirplaneService airplaneService;
    @GetMapping("/")
    public ResponseEntity<BaseResponse<List<Airplane>>> etAirways() {
        List<Airplane> airplanes = airplaneService.getAllAirplanes();
        BaseResponse<List<Airplane>> response = BaseResponse.<List<Airplane>>builder()
                .status(HttpStatus.OK.value())
                .isSuccess(true)
                .data(airplanes)
                .build();

        return ResponseEntity.ok(response);
    }
    @GetMapping("/searchairplane")
    public ResponseEntity<BaseResponse<List<Airplane>>> searchAirports(@RequestBody AirplaneSaveRequest request) {
        List<Airplane> airplanes = airplaneService.findAllByAirplaneNameContainingIgnoreCase(request.getAirplaneName());
        BaseResponse<List<Airplane>> response = BaseResponse.<List<Airplane>>builder()
                .status(HttpStatus.OK.value())
                .isSuccess(true)
                .data(airplanes)
                .build();
        return ResponseEntity.ok(response);

    }

    @PutMapping("/updateairplane")
    public ResponseEntity<Object> updateAirplane(@RequestBody AirplaneSaveRequest request) {
        var updateAirplane= airplaneService.update(request);
        var response = BaseResponse.<AirplaneSaveResponse>builder()
                .status(HttpStatus.OK.value())
                .isSuccess(true)
                .data(updateAirplane)
                .build();
        return ResponseEntity.ok(response);
    }

    @PostMapping("/airplane")
    public ResponseEntity<Object> createAirport(@RequestBody AirplaneSaveRequest request) {
        var airplaneSaveResponse = airplaneService.save(request);
        var response = BaseResponse.<AirplaneSaveResponse>builder()
                .status(HttpStatus.CREATED.value())
                .isSuccess(true)
                .data(airplaneSaveResponse)
                .build();
        return ResponseEntity.ok(response);
    }
}
