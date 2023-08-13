package io.upschool.controller;

import io.upschool.dto.*;
import io.upschool.entity.Airplane;
import io.upschool.entity.Airport;
import io.upschool.service.AirportService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/airports")
@RequiredArgsConstructor
public class AirportController {
    private final AirportService airportService;
    @GetMapping("/")
    public ResponseEntity<BaseResponse<List<Airport>>> getAirports() {
        List<Airport> airports = airportService.getAllAirports();
        BaseResponse<List<Airport>> response = BaseResponse.<List<Airport>>builder()
                .status(HttpStatus.OK.value())
                .isSuccess(true)
                .data(airports)
                .build();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/searchairports")
    public ResponseEntity<BaseResponse<List<Airport>>> searchAirports(@RequestBody AirportSaveRequest request) {
        List<Airport> airports = airportService.findAllByNameIsContainingIgnoreCase(request.getAirportname());
        BaseResponse<List<Airport>> response = BaseResponse.<List<Airport>>builder()
                .status(HttpStatus.OK.value())
                .isSuccess(true)
                .data(airports)
                .build();
        return ResponseEntity.ok(response);

    }

    @PutMapping("/updateairport")
    public ResponseEntity<Object> updateAirport(@RequestBody AirportSaveRequest request) {
        var updateAirport=airportService.update(request);
        var response = BaseResponse.<AirportSaveResponse>builder()
                .status(HttpStatus.OK.value())
                .isSuccess(true)
                .data(updateAirport)
                .build();
        return ResponseEntity.ok(response);

    }
    @PostMapping("/airports")
    public ResponseEntity<Object> createAirport(@RequestBody AirportSaveRequest request) {
        var airportleSaveResponse = airportService.save(request);
        var response = BaseResponse.<AirportSaveResponse>builder()
                .status(HttpStatus.CREATED.value())
                .isSuccess(true)
                .data(airportleSaveResponse)
                .build();
        return ResponseEntity.ok(response);
    }


}
