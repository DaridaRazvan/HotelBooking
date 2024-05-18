package hotel.booking.backend.service;

import hotel.booking.backend.model.dto.RoomDto;

import java.util.List;

public interface RoomService {
    List<RoomDto> getAvailableHotelRooms(Long hotelId);

    Long addRoomToHotel(RoomDto roomDto);
}
