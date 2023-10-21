package findr.fole.controller;

import findr.fole.dto.BuildingFloorDTO;
import findr.fole.dto.RoomDTO;
import findr.fole.service.BuildingFloorService;
import findr.fole.service.RoomService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/buildingFloors")
public class BuildingFloorController {
    private final BuildingFloorService buildingFloorService;
    private final RoomService roomService;

    public BuildingFloorController(BuildingFloorService buildingFloorService, RoomService roomService) {
        this.buildingFloorService = buildingFloorService;
        this.roomService = roomService;
    }

    @GetMapping("{floorId}")
    public List<RoomDTO> getFloorsById(@PathVariable Integer floorId) {
        return roomService.findAllByFloorId(floorId);
    }
}
