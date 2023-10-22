package findr.fole.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BuildingFloorDTO {
    private Integer id;
    private BuildingDTO building;
    private int floorNum;
    private int roomNum;
}
