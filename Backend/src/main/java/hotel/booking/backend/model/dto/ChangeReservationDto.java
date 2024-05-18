package hotel.booking.backend.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChangeReservationDto {
    private Long hotelId;
    private Long roomId;
    private Long reservationId;
}
