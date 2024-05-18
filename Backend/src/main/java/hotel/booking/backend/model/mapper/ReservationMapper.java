package hotel.booking.backend.model.mapper;

import hotel.booking.backend.model.Reservation;
import hotel.booking.backend.model.dto.ReservationDto;

public class ReservationMapper {
    public ReservationDto convertModelToDto(Reservation reservation) {
        return new ReservationDto(
                reservation.getId(),
                reservation.getDate(),
                reservation.getHotel().getId(),
                reservation.getHotel().getName(),
                reservation.getRoom().getId(),
                reservation.getRoom().getRoomNumber(),
                reservation.getRoom().getType(),
                reservation.getRoom().getPrice()
        );
    }
}
