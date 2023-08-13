package io.upschool.controller;

import io.upschool.dto.*;
import io.upschool.entity.Airway;
import io.upschool.entity.Customer;
import io.upschool.entity.Ticket;
import io.upschool.service.CustomerService;
import io.upschool.service.TicketService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customers")
@RequiredArgsConstructor
public class CustomerController {
    private final CustomerService customerService;
    @GetMapping("/")
    public ResponseEntity<BaseResponse<List<Customer>>> getCustomer() {
        List<Customer> customers = customerService.getAllCustomers();
        BaseResponse<List<Customer>> response = BaseResponse.<List<Customer>>builder()
                .status(HttpStatus.OK.value())
                .isSuccess(true)
                .data(customers)
                .build();
        return ResponseEntity.ok(response);

    }
    @GetMapping("/searchcustomer")
    public ResponseEntity<BaseResponse<List<Customer>>> searchTicket(@RequestBody CustomerSaveRequest request) {
        List<Customer> customer = customerService.findAllByIdentityNo(request.getIdentityNo());
        BaseResponse<List<Customer>> response = BaseResponse.<List<Customer>>builder()
                .status(HttpStatus.OK.value())
                .isSuccess(true)
                .data(customer)
                .build();
        return ResponseEntity.ok(response);
    }

    @PutMapping("/updatecustomer")
    public ResponseEntity<Object> updateCustomer(@RequestBody CustomerSaveRequest request) {
        var updateCustomer=customerService.update(request);
        var response = BaseResponse.<CustomerSaveResponse>builder()
                .status(HttpStatus.OK.value())
                .isSuccess(true)
                .data(updateCustomer)
                .build();
        return ResponseEntity.ok(response);
    }

    @PostMapping("/customer")
    public ResponseEntity<Object> createCustomer(@RequestBody CustomerSaveRequest request) {
        var customerSaveResponse = customerService.save(request);
        var response = BaseResponse.<CustomerSaveResponse>builder()
                .status(HttpStatus.CREATED.value())
                .isSuccess(true)
                .data(customerSaveResponse)
                .build();
        return ResponseEntity.ok(response);
    }
}
