package findr.fole.repository;

import findr.fole.model.Building;
import findr.fole.model.BuildingFloor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BuildingFloorRepository extends JpaRepository<BuildingFloor, Integer>, JpaSpecificationExecutor<BuildingFloor> {
    List<BuildingFloor> findBuildingFloorByBuilding_Id(Integer id);
}
