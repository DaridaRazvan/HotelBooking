package hotel.booking.backend.model.mapper;

import hotel.booking.backend.model.Hotel;
import hotel.booking.backend.model.dto.HotelDto;

public class HotelMapper {
    public HotelDto convertModelToDto(Hotel hotel) {
        return new HotelDto(
                hotel.getId(),
                hotel.getName(),
                hotel.getLatitude(),
                hotel.getLongitude()
        );
    }

    public Hotel convertDtoToModel(HotelDto hotelDto) {
        return new Hotel(
                hotelDto.getId(),
                hotelDto.getName(),
                hotelDto.getLatitude(),
                hotelDto.getLongitude(),
                null
        );
    }
}
