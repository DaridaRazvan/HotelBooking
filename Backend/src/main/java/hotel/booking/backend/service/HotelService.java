package hotel.booking.backend.service;

import hotel.booking.backend.model.dto.HotelDto;
import hotel.booking.backend.model.dto.PositionAndDistance;

import java.util.List;

public interface HotelService {
    List<HotelDto> getAllHotels();

    Long addHotel(HotelDto hotelDto);

    HotelDto getHotelById(Long id);

    List<HotelDto> getHotelsInRange(PositionAndDistance positionAndDistance);
}
