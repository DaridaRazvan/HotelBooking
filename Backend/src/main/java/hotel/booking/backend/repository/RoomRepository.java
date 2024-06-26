package hotel.booking.backend.repository;

import hotel.booking.backend.model.Room;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoomRepository extends JpaRepository<Room, Long> {
    Optional<Room> getByRoomNumber(Integer roomNumber);
}
