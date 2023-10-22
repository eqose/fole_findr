package findr.fole.rest.req;

import findr.fole.model.Building;


public record BuildingFloorUpdateRequest(
    Building building,
    int floorNum,
    int roomNum
) {
}
