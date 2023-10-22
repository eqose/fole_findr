package findr.fole.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RoomDTO {
    private Integer id;
    private String name;
    private RoomTypeDTO roomType;
    private BuildingFloorDTO buildingFloorDTO;

}
