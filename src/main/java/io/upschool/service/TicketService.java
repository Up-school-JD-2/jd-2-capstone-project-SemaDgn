package io.upschool.service;

import io.upschool.dto.TicketSaveRequest;
import io.upschool.dto.TicketSaveResponse;
import io.upschool.entity.*;
import io.upschool.exception.AirplaneCheckCapacityException;
import io.upschool.exception.TicketfindAllByTicketCodeException;
import io.upschool.repository.TicketRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class TicketService {
    private final TicketRepository ticketRepository;
    private final CustomerService customerService;
    private final FlightService flightService;

    @Transactional
    public TicketSaveResponse save(TicketSaveRequest request) {
        List<Customer> customerByReference = customerService.getCustomersById(request.getCustomerId());
        List<Flight> flightByReference = flightService.getFlightById(request.getFlightId());
        int fullOfCapacity = checkCapacityForAirplane(request.getFlightId());
        int emptyOfCapacity=flightByReference.get(0).getAirplane().getCapacity()-fullOfCapacity;

        if (emptyOfCapacity==0)
            throw new AirplaneCheckCapacityException("Belirtilen uçuşa ait boş koltuk bulunmamaktadır.");

        var newTicket = Ticket.builder()
                .ticketCode(generateTicketCode())
                .creditCardNo(maskCardNumber(request.getCreditCardNo()))
                .price(request.getPrice())
                .customer(customerByReference.get(0))
                .flight(flightByReference.get(0))
                .build();
        var ticketResponse = ticketRepository.save(newTicket);

        return TicketSaveResponse.builder()
                .ticketCode(ticketResponse.getTicketCode())
                .movementCity(ticketResponse.getFlight().getCityOfMovement())
                .destinationCity(ticketResponse.getFlight().getDestinationCity())
                .flightDate(ticketResponse.getFlight().getFlightDate())
                .customerName(ticketResponse.getCustomer().getName())
                .customerSurname(ticketResponse.getCustomer().getSurname())
                .creditCardNo(ticketResponse.getCreditCardNo())
                .price(ticketResponse.getPrice())
                .build();
    }

    public int checkCapacityForAirplane(long flightId) {
        List<Ticket> ticketCount = ticketRepository.findAllByFlightIdAndAndCanceled(flightId,false);
        return ticketCount.size();
    }

    public List<Ticket> findAllByTicketCodeIgnoreCase(String code) {
        List<Ticket> tickets = ticketRepository.findAllByTicketCodeIgnoreCase(code);
        if (tickets.size() == 0)
            throw new TicketfindAllByTicketCodeException("Girilen koda ait bilet bulunamadı.");
        return tickets;

    }

    public List<Ticket> getAllTickets() {
        return ticketRepository.findAll();
    }

    private String generateTicketCode() {
        UUID uniqueKey = UUID.randomUUID();
        String[] codes = uniqueKey.toString().split("-");
        return codes[0];
    }

    public String maskCardNumber(String cardNumber) {
        StringBuilder maskedNumber = new StringBuilder();
        if (cardNumber.contains("-"))
            cardNumber = cardNumber.replace("-", "");
        else if (cardNumber.contains(" ")) {
            cardNumber = cardNumber.replaceAll("\\s+", "");

        }
        if (cardNumber.length() == 16) {
            String mask = "######******####";
            int index = 0;

            for (int i = 0; i < mask.length(); i++) {
                char c = mask.charAt(i);
                if (c == '#') {
                    maskedNumber.append(cardNumber.charAt(index));
                    index++;
                } else if (c == '*') {
                    maskedNumber.append(c);
                    index++;
                } else {
                    maskedNumber.append(c);
                }
            }
            return maskedNumber.toString();
        }
        return maskedNumber.toString();
    }
    public TicketSaveResponse delete(String ticketCode) {
        List<Ticket> ticket = ticketRepository.findAllByTicketCodeIgnoreCase(ticketCode);
        ticket.get(0).setCanceled(true);
        ticketRepository.save(ticket.get(0));
        //Cutomer balance update
        customerService.updateBalanceofCustomer(ticket.get(0).getCustomer(),ticket.get(0).getPrice());
        return TicketSaveResponse.builder()
                .ticketCode(ticket.get(0).getTicketCode())
                .movementCity(ticket.get(0).getFlight().getCityOfMovement())
                .destinationCity(ticket.get(0).getFlight().getDestinationCity())
                .flightDate(ticket.get(0).getFlight().getFlightDate())
                .customerName(ticket.get(0).getCustomer().getName())
                .customerSurname(ticket.get(0).getCustomer().getSurname())
                .creditCardNo(ticket.get(0).getCreditCardNo())
                .price(ticket.get(0).getPrice())
                .cancelled(ticket.get(0).getCanceled())
                .build();
    }

}
