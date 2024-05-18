package hotel.booking.backend.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoomDto {
    private Long id;
    private Integer roomNumber;
    private Integer type;
    private Integer price;
    private Boolean isAvailable;
    private Long hotelId;
}
