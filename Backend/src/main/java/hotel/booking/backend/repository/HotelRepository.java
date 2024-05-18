package hotel.booking.backend.repository;

import hotel.booking.backend.model.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface HotelRepository extends JpaRepository<Hotel, Long> {
    Optional<Hotel> getByName(String name);
}
