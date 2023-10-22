package findr.fole.repository;

import findr.fole.model.Building;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BuildingRepository extends JpaRepository<Building, Integer> {
    Optional<Building> findByName(String name);
}
