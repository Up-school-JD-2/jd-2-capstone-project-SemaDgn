package io.upschool.repository;
import io.upschool.entity.Airway;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AirwayRepository extends JpaRepository<Airway,Long> {
    List<Airway> findAllByNameIsContainingIgnoreCase(String name);
    List<Airway> getAirwayById(Long id);
}
