package hotel.booking.backend.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PositionAndDistance {
    private Double latitude;
    private Double longitude;
    private Integer distanceInKm;
}
