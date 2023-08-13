package io.upschool.repository;
import io.upschool.entity.Airplane;
import io.upschool.entity.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AirplaneRepository extends JpaRepository<Airplane,Long> {
    List<Airplane> getAirplaneById(Long id);
    List<Airplane> findAllByAirplaneNameContainingIgnoreCase(String name);
}
