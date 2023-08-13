package io.upschool.controller;

import io.upschool.dto.*;
import io.upschool.entity.Customer;
import io.upschool.entity.Flight;
import io.upschool.service.FlightService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/flights")
@RequiredArgsConstructor
public class FlightController {
    private final FlightService flightService;

    @GetMapping("/")
    public ResponseEntity<BaseResponse<List<Flight>>> getFlights() {
        List<Flight> flights = flightService.getAllFlights();
        BaseResponse<List<Flight>> response = BaseResponse.<List<Flight>>builder()
                .status(HttpStatus.OK.value())
                .isSuccess(true)
                .data(flights)
                .build();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/searchflights")
    public ResponseEntity<BaseResponse<List<Flight>>> searchAirways(@RequestBody FlightSaveRequest request) {
        List<Flight> flights = flightService.findAllByCityOfMovementAndDestinationCity(request.getMovementCity(),request.getDestinationCity());
        BaseResponse<List<Flight>> response = BaseResponse.<List<Flight>>builder()
                .status(HttpStatus.OK.value())
                .isSuccess(true)
                .data(flights)
                .build();
        return ResponseEntity.ok(response);
    }
    @PutMapping("/updateflight")
    public ResponseEntity<Object> updateFlight(@RequestBody FlightSaveRequest request) {
        var updateFlight=flightService.update(request);
        var response = BaseResponse.<FlightSaveResponse>builder()
                .status(HttpStatus.OK.value())
                .isSuccess(true)
                .data(updateFlight)
                .build();
        return ResponseEntity.ok(response);
    }
    @PostMapping("/flights")
    public ResponseEntity<Object> createFlight(@RequestBody FlightSaveRequest request) {
        var flightSaveResponse = flightService.save(request);
        var response = BaseResponse.<FlightSaveResponse>builder()
                .status(HttpStatus.CREATED.value())
                .isSuccess(true)
                .data(flightSaveResponse)
                .build();
        return ResponseEntity.ok(response);
    }
}
