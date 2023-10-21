package findr.fole.repository;

import findr.fole.model.Building;
import findr.fole.model.BuildingFloor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BuildingFloorRepository extends JpaRepository<BuildingFloor, Integer> {
}
