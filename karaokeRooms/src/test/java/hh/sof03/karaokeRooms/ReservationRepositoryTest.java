package hh.sof03.karaokeRooms;

import static org.assertj.core.api.Assertions.assertThat;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import hh.sof03.karaokeRooms.Domain.Reservation;
import hh.sof03.karaokeRooms.Domain.ReservationRepository;
import hh.sof03.karaokeRooms.Domain.Room;
import hh.sof03.karaokeRooms.Domain.RoomRepository;
import hh.sof03.karaokeRooms.Domain.User;
import hh.sof03.karaokeRooms.Domain.UserRepository;


@ExtendWith(SpringExtension.class)

@SpringBootTest
public class ReservationRepositoryTest {

    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoomRepository roomRepository;

    @Test //Uuden varauksen lisääminen
    public void addNewReservation() throws ParseException {
        Room room = new Room("Room 3", 5, 100.00);

        roomRepository.save(room);
        
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        Date date = sdf.parse("11-11-2024");

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
        LocalTime time = LocalTime.parse("16:00", formatter);
        LocalTime time2 = LocalTime.parse("18:00", formatter);

        User user = new User("Roope", "$2a$10$87kpttlb0OK0HSZwhJGgDery7F5Y6i9/jKsBsSw.TRZp6TWIccm7W" , "USER" ,"roope@ankka.com");

        userRepository.save(user);

        Reservation reservation = new Reservation(date, time, time2, room, user);

        reservationRepository.save(reservation);
        assertThat(reservation.getReservationId()).isNotNull();
    }

    @Test //Varauksen poistaminen
    public void deleteReservation() throws ParseException {
        Room room = new Room("Room 3", 5, 100.00);

        roomRepository.save(room);
        
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        Date date = sdf.parse("11-11-2024");

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
        LocalTime time = LocalTime.parse("16:00", formatter);
        LocalTime time2 = LocalTime.parse("18:00", formatter);

        User user = new User("Roope", "$2a$10$87kpttlb0OK0HSZwhJGgDery7F5Y6i9/jKsBsSw.TRZp6TWIccm7W" , "USER" ,"roope@ankka.com");

        userRepository.save(user);

        Reservation reservation = new Reservation(date, time, time2, room, user);

        reservationRepository.save(reservation);
        
        reservationRepository.deleteById(reservation.getReservationId());

        boolean reservationExists = reservationRepository.existsById(reservation.getReservationId());
        assertThat(reservationExists).isFalse();

    }
    
}
