package io.upschool.controller;

import io.upschool.dto.*;
import io.upschool.entity.Airport;
import io.upschool.entity.Airway;
import io.upschool.service.AirwayService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/airways")
@RequiredArgsConstructor
public class AirwayController {

    private final AirwayService airwayService;

    @GetMapping("/")
    public ResponseEntity<BaseResponse<List<Airway>>> getAirways() {
        List<Airway> airways = airwayService.getAllAirways();
        BaseResponse<List<Airway>> response = BaseResponse.<List<Airway>>builder()
                .status(HttpStatus.OK.value())
                .isSuccess(true)
                .data(airways)
                .build();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/searchairways")
    public ResponseEntity<BaseResponse<List<Airway>>> searchAirways(@RequestBody AirwaySaveRequest request) {
        List<Airway> airways = airwayService.findAllByNameIsContainingIgnoreCase(request.getAirwayName());
        BaseResponse<List<Airway>> response = BaseResponse.<List<Airway>>builder()
                .status(HttpStatus.OK.value())
                .isSuccess(true)
                .data(airways)
                .build();
        return ResponseEntity.ok(response);
    }

    @PutMapping("/updateairway")
    public ResponseEntity<Object> updateCity(@RequestBody AirwaySaveRequest request) {
        var updateAirway=airwayService.update(request);
        var response = BaseResponse.<AirwaySaveResponse>builder()
                .status(HttpStatus.OK.value())
                .isSuccess(true)
                .data(updateAirway)
                .build();
        return ResponseEntity.ok(response);
    }
    @PostMapping("/airways")
    public ResponseEntity<Object> createAirport(@RequestBody AirwaySaveRequest request) {
        var airwaySaveResponse = airwayService.save(request);
        var response = BaseResponse.<AirwaySaveResponse>builder()
                .status(HttpStatus.CREATED.value())
                .isSuccess(true)
                .data(airwaySaveResponse)
                .build();
        return ResponseEntity.ok(response);
    }
}
