package findr.fole.controller;

import findr.fole.dto.BuildingDTO;
import findr.fole.dto.BuildingFloorDTO;
import findr.fole.service.BuildingFloorService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/buildingFloors")
public class BuildingFloorController {
    private final BuildingFloorService buildingFloorService;

    public BuildingFloorController(BuildingFloorService buildingFloorService) {
        this.buildingFloorService = buildingFloorService;
    }

    @GetMapping
    public List<BuildingFloorDTO> getBuildingFloors(@RequestBody Integer buildingId) {
        return buildingFloorService.findAll(buildingId);
    }
}
