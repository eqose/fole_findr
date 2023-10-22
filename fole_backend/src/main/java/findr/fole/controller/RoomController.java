package findr.fole.controller;

import findr.fole.dto.ContractDTO;
import findr.fole.dto.RoomDTO;
import findr.fole.rest.req.ContractFilterRequest;
import findr.fole.rest.req.RoomFilterRequest;
import findr.fole.service.RoomService;
import org.springframework.security.access.method.P;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/v1/availableRooms")
public class RoomController {
    private final RoomService roomService;

    public RoomController(RoomService roomService) {
        this.roomService = roomService;
    }

    @PostMapping("/{idFloor}")
    public List<RoomDTO> getAvailableRooms(@RequestBody RoomFilterRequest request, @PathVariable("idFloor") Integer idFloor) {
        LocalDate start = request.start()==null?LocalDate.MIN:request.start();
        LocalDate end = request.end()==null?LocalDate.MAX:request.end();

        return roomService.findAllFloorsBetweenDates(start, end, idFloor);
    }
}
