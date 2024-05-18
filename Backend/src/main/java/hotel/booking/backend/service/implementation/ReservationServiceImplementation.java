package hotel.booking.backend.service.implementation;

import hotel.booking.backend.exceptions.CustomException;
import hotel.booking.backend.model.Hotel;
import hotel.booking.backend.model.Reservation;
import hotel.booking.backend.model.Room;
import hotel.booking.backend.model.dto.*;
import hotel.booking.backend.model.mapper.ReservationMapper;
import hotel.booking.backend.repository.HotelRepository;
import hotel.booking.backend.repository.ReservationRepository;
import hotel.booking.backend.repository.RoomRepository;
import hotel.booking.backend.service.ReservationService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
@Transactional
@Slf4j
public class ReservationServiceImplementation implements ReservationService {

    private final ReservationRepository reservationRepository;
    private final HotelRepository hotelRepository;
    private final RoomRepository roomRepository;

    @Override
    public List<ReservationDto> getReservations() {
        List<Reservation> reservations = reservationRepository.findAll();

        ReservationMapper reservationMapper = new ReservationMapper();
        List<ReservationDto> reservationsDto = new ArrayList<>();

        reservations.forEach(reservation -> {
            reservationsDto.add(reservationMapper.convertModelToDto(reservation));
        });

        return reservationsDto;
    }

    @Override
    public Long addReservation(CreateReservationDto createReservationDto) {
        if (hotelRepository.findById(createReservationDto.getHotelId()).isEmpty()) {
            throw new CustomException("No hotel with the given Id");
        }

        if (roomRepository.findById(createReservationDto.getRoomId()).isEmpty()) {
            throw new CustomException("No room with the given id");
        }

        Hotel hotel = hotelRepository.findById(createReservationDto.getHotelId()).get();
        Room room = roomRepository.findById(createReservationDto.getRoomId()).get();

        ZoneId romaniaZoneId = ZoneId.of("Europe/Bucharest");
        ZonedDateTime romaniaZonedDateTime = ZonedDateTime.now(romaniaZoneId);
        LocalDateTime reservationDate = romaniaZonedDateTime.toLocalDateTime();

        if (reservationDate.getHour() > 11) {
            reservationDate = reservationDate.plusDays(1);
        }
        reservationDate = reservationDate.withHour(12);
        reservationDate = reservationDate.withMinute(0);
        reservationDate = reservationDate.withSecond(0);
        reservationDate = reservationDate.withNano(0);

        Reservation reservation = reservationRepository.save(new Reservation(null, hotel, room, reservationDate));

        room.setIsAvailable(false);
        roomRepository.save(room);
        log.info("Reservation saved");

        return reservation.getId();
    }

    @Override
    public MessageDto cancelReservation(DeleteReservationDto deleteReservationDto) {
        if (reservationRepository.findById(deleteReservationDto.getId()).isEmpty()) {
            throw new CustomException("No reservation with that id");
        }
        Reservation reservation = reservationRepository.findById(deleteReservationDto.getId()).get();

        ZoneId romaniaZoneId = ZoneId.of("Europe/Bucharest");
        ZonedDateTime romaniaZonedDateTime = ZonedDateTime.now(romaniaZoneId);
        LocalDateTime reservationCancelDate = romaniaZonedDateTime.toLocalDateTime();

        LocalDateTime newDate = reservationCancelDate.plusHours(2);

        log.info(String.valueOf(reservationCancelDate));
        log.info(String.valueOf(newDate));
        log.info(String.valueOf(reservation.getDate()));

        if (newDate.isBefore(reservation.getDate())) {
            reservationRepository.delete(reservation);

            Room room = reservation.getRoom();
            room.setIsAvailable(true);
            roomRepository.save(room);

            log.info("Reservation Deleted");
            return new MessageDto("Reservation Deleted");
        }

        return new MessageDto("Reservation couldn't be deleted");
    }

    @Override
    public MessageDto changeReservation(ChangeReservationDto changeReservationDto) {

        MessageDto message = new MessageDto();
        try {
            message = cancelReservation(new DeleteReservationDto(changeReservationDto.getReservationId()));
        } catch (CustomException e) {
            return new MessageDto("Reservation couldn't be changed");
        }

        if (message.getMessage().equals("Reservation Deleted")) {
            try {
                addReservation(new CreateReservationDto(changeReservationDto.getHotelId(), changeReservationDto.getRoomId()));
            } catch (CustomException c) {
                return new MessageDto("Reservation couldn't be changed");
            }
        } else {
            return new MessageDto("Reservation couldn't be changed");
        }

        return new MessageDto("Reservation Changed");
    }

}
