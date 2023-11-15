package hh.sof03.karaokeRooms;

import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import hh.sof03.karaokeRooms.Domain.Reservation;
import hh.sof03.karaokeRooms.Domain.ReservationRepository;
import hh.sof03.karaokeRooms.Domain.Room;
import hh.sof03.karaokeRooms.Domain.RoomRepository;
import hh.sof03.karaokeRooms.Domain.User;
import hh.sof03.karaokeRooms.Domain.UserRepository;

@SpringBootApplication
public class KaraokeRoomsApplication {

	private static final Logger log = LoggerFactory.getLogger(KaraokeRoomsApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(KaraokeRoomsApplication.class, args);
	}

	@Bean
	public CommandLineRunner roomDemo (RoomRepository roomRepository, ReservationRepository reservationRepository, UserRepository userRepository) {
		return (args) -> {
			log.info("save a couple of rooms");
			Room room1 = roomRepository.save(new Room("Room 1", 4, 40.00));
			Room room2 = roomRepository.save(new Room("Room 2 ", 2, 20.00));

			User user1 = new User("Minni", "$2a$10$k8Q0oepTJN5tioCtGS09eOp9KlWu03KV5rZFrsJ5GCjTbQ9ye8oo2", "USER", "minni@hiiri.com");
			User user2 = new User("Aku", "$2a$10$SBDJoF5ElkHZIxKIQPMipORhMBL2lbusRV4sIFeFXZpjDLAUyxrlG", "USER", "aku@ankka.com");
			User user3 = new User("Mikki", "$2a$10$41MUt7JMZwrDtjAdCRvZKODzd.y5shnVrX5GTKYEefRg42FpojpcK", "ADMIN", "mikki2@hiiri.com");
			User user4 = new User("Hessu", "$2a$10$WQmkn8Why1PjSL1j562c0.2Xr.YCB/982AipPcfv3zGSSnQ7E66Ee", "ADMIN", "hessu@hopo.com");
			User user5 = new User("Iines", "$2a$10$oHMdG9JfhNA.KThwRrBCCuKjqINW3nqjtRWeVkdKBrKstioruY/WW", "USER", "iines@ankka.com");
			userRepository.save(user1);
			userRepository.save(user2);
			userRepository.save(user3);
			userRepository.save(user4);
			userRepository.save(user5);

			log.info("save a couple of reservations");
			SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        	Date date1 = sdf.parse("11-11-2024");
			Date date2 = sdf.parse("31-08-2024");
			Date date3 = sdf.parse("16-04-2024");

			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
        	LocalTime time1 = LocalTime.parse("16:00", formatter);
        	LocalTime time2 = LocalTime.parse("18:00", formatter);
			LocalTime time3 = LocalTime.parse("19:00", formatter);
        	LocalTime time4 = LocalTime.parse("21:00", formatter);


			Reservation reservation1 = new Reservation(date1,time1, time2, room1, user1);
			Reservation reservation2 = new Reservation(date2, time2, time4, room2, user2);
			Reservation reservation3 = new Reservation(date3, time3, time4, room2, user5);

			reservationRepository.save(reservation1);
			reservationRepository.save(reservation2);
			reservationRepository.save(reservation3);

			log.info("fetch all rooms");
			for (Room room : roomRepository.findAll()) {
				log.info(room.toString());
			}
		};
	}

}
