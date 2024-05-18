package hotel.booking.backend.controller;

import hotel.booking.backend.model.dto.*;
import hotel.booking.backend.service.ReservationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("api/reservations")
@RequiredArgsConstructor
public class ReservationController {
    private final ReservationService reservationService;

    @GetMapping("/all")
    public ResponseEntity<List<ReservationDto>> getAllReservations() {
        return ResponseEntity.ok(reservationService.getReservations());
    }

    @PostMapping("/save")
    public ResponseEntity<Long> saveReservation(@RequestBody CreateReservationDto createReservationDto) {
        return ResponseEntity.ok(reservationService.addReservation(createReservationDto));
    }

    @PostMapping("/cancel")
    public ResponseEntity<MessageDto> cancelReservation(@RequestBody DeleteReservationDto deleteReservationDto) {
        return ResponseEntity.ok(reservationService.cancelReservation(deleteReservationDto));
    }

    @PostMapping("/change")
    public ResponseEntity<MessageDto> changeReservation(@RequestBody ChangeReservationDto changeReservationDto) {
        return ResponseEntity.ok(reservationService.changeReservation(changeReservationDto));
    }

}
