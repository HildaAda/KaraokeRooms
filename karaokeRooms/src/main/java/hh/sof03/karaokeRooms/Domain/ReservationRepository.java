package hh.sof03.karaokeRooms.Domain;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface ReservationRepository extends CrudRepository <Reservation, Long> {
    List<Reservation> findByRoomRoomId(Long roomId);
    List<Reservation> findByUserUserId(Long userId);
}
