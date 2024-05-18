package hotel.booking.backend.model.mapper;

import hotel.booking.backend.model.Hotel;
import hotel.booking.backend.model.Room;
import hotel.booking.backend.model.dto.RoomDto;

public class RoomMapper {
    public RoomDto convertModelToDto(Room room) {
        return new RoomDto(
                room.getId(),
                room.getRoomNumber(),
                room.getType(),
                room.getPrice(),
                room.getIsAvailable(),
                room.getHotel().getId()
        );
    }

    public Room convertDtoToModel(RoomDto roomDto, Hotel hotel) {
        return new Room(
                roomDto.getId(),
                roomDto.getRoomNumber(),
                roomDto.getType(),
                roomDto.getPrice(),
                roomDto.getIsAvailable(),
                hotel
        );
    }
}
