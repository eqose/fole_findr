package findr.fole.controller;


import findr.fole.dto.BuildingFloorDTO;
import findr.fole.service.BuildingService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/buildingFloors")
public class BuildingController {
    private final BuildingService buildingService;

    public BuildingController(BuildingService buildingService) {
        this.buildingService = buildingService;
    }

    @GetMapping
    public List<BuildingFloorDTO> getBuildingFloors() {
        return buildingService.findAll();
    }
}