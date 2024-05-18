package hotel.booking.backend.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReservationDto {
    private Long id;
    private LocalDateTime time;

    private Long hotelId;
    private String hotelName;

    private Long roomId;
    private Integer roomNumber;
    private Integer type;
    private Integer price;

}
