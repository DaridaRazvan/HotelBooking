package hotel.booking.backend.service.implementation;

import hotel.booking.backend.exceptions.CustomException;
import hotel.booking.backend.model.Hotel;
import hotel.booking.backend.model.dto.HotelDto;
import hotel.booking.backend.model.dto.PositionAndDistance;
import hotel.booking.backend.model.mapper.HotelMapper;
import hotel.booking.backend.repository.HotelRepository;
import hotel.booking.backend.service.HotelService;
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
public class HotelServiceImplementation implements HotelService {
    private final HotelRepository hotelRepository;

    @Override
    public List<HotelDto> getAllHotels() {
        log.info("Getting all hotels");
        HotelMapper hotelMapper = new HotelMapper();
        List<Hotel> hotels = hotelRepository.findAll();

        List<HotelDto> hotelsDto = new ArrayList<>();
        hotels.forEach(
                hotel -> hotelsDto.add(hotelMapper.convertModelToDto(hotel))
        );

        return hotelsDto;
    }

    @Override
    public Long addHotel(HotelDto hotelDto) {
        log.info("Adding hotel");

        if (hotelRepository.getByName(hotelDto.getName()).isPresent()) {
            throw new CustomException("Hotel already in database!");
        }

        HotelMapper hotelMapper = new HotelMapper();
        Hotel hotel = hotelMapper.convertDtoToModel(hotelDto);

        hotelRepository.save(hotel);
        return hotel.getId();
    }

    @Override
    public HotelDto getHotelById(Long id) {
        if (hotelRepository.findById(id).isEmpty()) {
            throw new CustomException("A hotel with that id doesn't exist");
        }

        Hotel hotel = hotelRepository.findById(id).get();

        HotelMapper hotelMapper = new HotelMapper();

        return hotelMapper.convertModelToDto(hotel);
    }

    private double rangeBetweenPoints(Double lat1, Double lon1, Double lat2, Double lon2) {
        double RADIUS = 6371;

        lat1 = Math.toRadians(lat1);
        lat2 = Math.toRadians(lat2);
        lon1 = Math.toRadians(lon1);
        lon2 = Math.toRadians(lon2);

        double distanceLat = lat2 - lat1;
        double distanceLon = lon2 - lon1;

        double a = Math.pow(Math.sin(distanceLat / 2), 2)
                + Math.cos(lat1) * Math.cos(lat2)
                * Math.pow(Math.sin(distanceLon / 2), 2);

        double c = 2 * Math.asin(Math.sqrt(a));

        return c * RADIUS;
    }

    @Override
    public List<HotelDto> getHotelsInRange(PositionAndDistance positionAndDistance) {
        List<HotelDto> hotelsDto = getAllHotels();
        List<HotelDto> hotelsInRange = new ArrayList<>();

        Double lat1 = positionAndDistance.getLatitude();
        Double lon1 = positionAndDistance.getLongitude();

        hotelsDto.forEach(hotel -> {
            if (rangeBetweenPoints(lat1, lon1, hotel.getLatitude(), hotel.getLongitude()) < positionAndDistance.getDistanceInKm()) {
                hotelsInRange.add(hotel);
            }
        });

        return hotelsInRange;
    }

}
