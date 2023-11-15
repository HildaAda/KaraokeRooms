package hh.sof03.karaokeRooms;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import hh.sof03.karaokeRooms.Domain.ReservationRepository;
import hh.sof03.karaokeRooms.Domain.RoomRepository;
import hh.sof03.karaokeRooms.Domain.UserRepository;

@ExtendWith(SpringExtension.class)

@SpringBootTest
class KaraokeRoomsApplicationTests {

	@Autowired
    private RoomRepository roomRepository;

	@Autowired
    private ReservationRepository reservationRepository;

	@Autowired
    private UserRepository userRepository;

	@Test
	void contextLoads() {

		assertThat(roomRepository).isNotNull();
		assertThat(reservationRepository).isNotNull();
		assertThat(userRepository).isNotNull();
	}

}
