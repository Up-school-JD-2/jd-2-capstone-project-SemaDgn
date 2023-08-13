package io.upschool.controller;

import io.upschool.dto.*;
import io.upschool.entity.Flight;
import io.upschool.entity.Ticket;
import io.upschool.service.TicketService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/tickets")
@RequiredArgsConstructor
public class TicketController {
    private final TicketService ticketService;

    @GetMapping("/")
    public ResponseEntity<BaseResponse<List<Ticket>>> getTicket() {
        var tickets = ticketService.getAllTickets();
        BaseResponse<List<Ticket>> response = BaseResponse.<List<Ticket>>builder()
                .status(HttpStatus.OK.value())
                .isSuccess(true)
                .data(tickets)
                .build();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/searchticket")
    public ResponseEntity<BaseResponse<List<Ticket>>> searchTicket(@RequestBody TicketSaveRequest request) {
        List<Ticket> ticket = ticketService.findAllByTicketCodeIgnoreCase(request.getTicketCode());
        BaseResponse<List<Ticket>> response = BaseResponse.<List<Ticket>>builder()
                .status(HttpStatus.OK.value())
                .isSuccess(true)
                .data(ticket)
                .build();
        return ResponseEntity.ok(response);
    }

    @PostMapping("/ticket")
    public ResponseEntity<Object> createFlight(@RequestBody TicketSaveRequest request) {
        var tiketSaveResponse = ticketService.save(request);
        var response = BaseResponse.<TicketSaveResponse>builder()
                .status(HttpStatus.CREATED.value())
                .isSuccess(true)
                .data(tiketSaveResponse)
                .build();
        return ResponseEntity.ok(response);
    }
    @DeleteMapping("cancelticket")
    public ResponseEntity<Object> deleteTicket(@RequestBody TicketSaveRequest request) {
       var ticketDeleteResponse= ticketService.delete(request.getTicketCode());
        var response = BaseResponse.<TicketSaveResponse>builder()
                .status(HttpStatus.CREATED.value())
                .isSuccess(true)
                .data(ticketDeleteResponse)
                .build();
        return ResponseEntity.ok(response);
    }
}
