package hotel.booking.backend.controller;

import hotel.booking.backend.model.dto.HotelDto;
import hotel.booking.backend.model.dto.RoomDto;
import hotel.booking.backend.service.RoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("api/rooms")
@RequiredArgsConstructor
public class RoomController {
    private final RoomService roomService;

    @GetMapping("/{hotel_id}")
    public ResponseEntity<List<RoomDto>> getAvailableHotelRooms(@PathVariable Long hotel_id) {
        return ResponseEntity.ok(roomService.getAvailableHotelRooms(hotel_id));
    }

}
