package findr.fole.controller;


import findr.fole.dto.BuildingDTO;
import findr.fole.dto.BuildingFloorDTO;
import findr.fole.service.BuildingFloorService;
import findr.fole.service.BuildingService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/building")
public class BuildingController {
    private final BuildingService buildingService;
    private final BuildingFloorService buildingFloorService;


    public BuildingController(BuildingService buildingService, BuildingFloorService buildingFloorService) {
        this.buildingService = buildingService;
        this.buildingFloorService = buildingFloorService;
    }

    @GetMapping("/{buildingId}")
    public List<BuildingFloorDTO> getBuildingFloors(@PathVariable Integer buildingId) {
        return buildingFloorService.findAll(buildingId);
    }
}