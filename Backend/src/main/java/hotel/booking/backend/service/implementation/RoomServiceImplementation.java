package hotel.booking.backend.service.implementation;

import hotel.booking.backend.exceptions.CustomException;
import hotel.booking.backend.model.Hotel;
import hotel.booking.backend.model.Room;
import hotel.booking.backend.model.dto.RoomDto;
import hotel.booking.backend.model.mapper.RoomMapper;
import hotel.booking.backend.repository.HotelRepository;
import hotel.booking.backend.repository.RoomRepository;
import hotel.booking.backend.service.RoomService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@RequiredArgsConstructor
@Service
@Transactional
@Slf4j
public class RoomServiceImplementation implements RoomService {

    private final RoomRepository roomRepository;
    private final HotelRepository hotelRepository;

    @Override
    public List<RoomDto> getAvailableHotelRooms(Long hotelId) {
        if (hotelRepository.findById(hotelId).isEmpty()) {
            throw new CustomException("A hotel with that id doesn't exist");
        }

        Hotel hotel = hotelRepository.findById(hotelId).get();

        List<Room> rooms = hotel.getRooms();
        List<RoomDto> roomsDto = new ArrayList<>();
        RoomMapper roomMapper = new RoomMapper();

        rooms.forEach(room -> {
            if (room.getIsAvailable()) {
                roomsDto.add(roomMapper.convertModelToDto(room));
            }
        });

        return roomsDto;
    }

    @Override
    public Long addRoomToHotel(RoomDto roomDto) {
        log.info("Adding room for hotel " + roomDto.getHotelId());

        RoomMapper roomMapper = new RoomMapper();

        if (roomRepository.getByRoomNumber(roomDto.getRoomNumber()).isPresent()) {
            throw new CustomException("Room already exists for hotel " + roomDto.getHotelId());
        }

        if (hotelRepository.findById(roomDto.getHotelId()).isEmpty()) {
            throw new CustomException("No Hotel with id: " + roomDto.getHotelId());
        }
        Hotel hotel = hotelRepository.findById(roomDto.getHotelId()).get();

        Room room = roomMapper.convertDtoToModel(roomDto, hotel);
        roomRepository.save(room);

        return room.getId();
    }
}
