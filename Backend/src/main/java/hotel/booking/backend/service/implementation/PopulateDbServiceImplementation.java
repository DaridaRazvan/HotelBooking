package hotel.booking.backend.service.implementation;

import hotel.booking.backend.model.dto.HotelDto;
import hotel.booking.backend.model.dto.RoomDto;
import hotel.booking.backend.service.HotelService;
import hotel.booking.backend.service.PopulateDbService;
import hotel.booking.backend.service.RoomService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;


@RequiredArgsConstructor
@Service
@Transactional
@Slf4j
public class PopulateDbServiceImplementation implements PopulateDbService {
    private final HotelService hotelService;
    private final RoomService roomService;

    @Override
    public void populateDatabase(String content) {

        JSONArray json = new JSONArray(content);

        for (Object obj : json) {
            JSONObject hotel = (JSONObject) obj;

            Long hotelId = ((Number) hotel.get("id")).longValue();
            HotelDto hotelDto = new HotelDto(
                    hotelId,
                    (String) hotel.get("name"),
                    ((Number) hotel.get("latitude")).doubleValue(),
                    ((Number) hotel.get("longitude")).doubleValue()
            );
            hotelService.addHotel(hotelDto);

            JSONArray hotelRooms = (JSONArray) hotel.get("rooms");
            for (Object roomObj : hotelRooms) {
                JSONObject room = (JSONObject) roomObj;

                RoomDto roomDto = new RoomDto(
                        null,
                        ((Number) room.get("roomNumber")).intValue(),
                        ((Number) room.get("type")).intValue(),
                        ((Number) room.get("price")).intValue(),
                        ((Boolean) room.get("isAvailable")),
                        hotelId
                );

                roomService.addRoomToHotel(roomDto);
            }
        }

    }
}
