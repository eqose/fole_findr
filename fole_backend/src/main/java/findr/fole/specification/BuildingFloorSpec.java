package findr.fole.specification;

import findr.fole.dto.BuildingDTO;
import findr.fole.model.BuildingFloor;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

public class BuildingFloorSpec {
    public static Specification<BuildingFloor> getByBuilding(BuildingDTO buildingDTO) {
        return (root, query, criteriaBuilder) -> {
            Predicate equalPredicate = null;
            equalPredicate = criteriaBuilder.equal(root.get("building"), buildingDTO.getId());
            return equalPredicate;
        };
    }
}
