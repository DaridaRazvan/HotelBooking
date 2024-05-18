package hotel.booking.backend.service;

import hotel.booking.backend.model.dto.*;

import java.util.List;

public interface ReservationService {
    List<ReservationDto> getReservations();

    Long addReservation(CreateReservationDto createReservationDto);

    MessageDto cancelReservation(DeleteReservationDto deleteReservationDto);

    MessageDto changeReservation(ChangeReservationDto changeReservationDto);
}
