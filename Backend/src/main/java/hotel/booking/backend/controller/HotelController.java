package hotel.booking.backend.controller;

import hotel.booking.backend.model.dto.HotelDto;
import hotel.booking.backend.model.dto.PositionAndDistance;
import hotel.booking.backend.service.HotelService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("api/hotel")
@RequiredArgsConstructor
public class HotelController {
    private final HotelService hotelService;

    @GetMapping("/all")
    public ResponseEntity<List<HotelDto>> getAllHotels() {
        return ResponseEntity.ok(hotelService.getAllHotels());
    }

    @GetMapping("/{id}")
    public ResponseEntity<HotelDto> getHotelById(@PathVariable Long id) {
        return ResponseEntity.ok(hotelService.getHotelById(id));
    }

    @PostMapping("/inRange")
    public ResponseEntity<List<HotelDto>> getHotelsInRange(@RequestBody PositionAndDistance positionAndDistance) {
        return ResponseEntity.ok(hotelService.getHotelsInRange(positionAndDistance));
    }
}
