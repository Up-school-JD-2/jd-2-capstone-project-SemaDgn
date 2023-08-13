package io.upschool.repository;
import io.upschool.entity.Airway;
import io.upschool.entity.Flight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FlightRepository extends JpaRepository<Flight,Long> {
    @Query(value = "select * from flight f " +
            "where f.movement_city like CONCAT('%',:movementCity ,'%')" +
            "and f.destination_city like CONCAT('%',:destinationCity ,'%')\n  ",
            nativeQuery = true)
    List<Flight> findAllByCityOfMovementAndDestinationCity(@Param("movementCity") String movementCity,@Param("destinationCity") String destinationCity);
    List<Flight>getFlightById(Long id);
}

