package io.upschool.repository;

import io.upschool.entity.Airway;
import io.upschool.entity.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TicketRepository extends JpaRepository<Ticket,Long> {
    List<Ticket> findAllByFlightIdAndAndCanceled(Long id,boolean cancelled);
    List<Ticket> findAllByTicketCodeIgnoreCase(String code);

}
