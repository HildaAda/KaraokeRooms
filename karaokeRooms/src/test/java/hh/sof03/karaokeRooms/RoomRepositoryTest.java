package hh.sof03.karaokeRooms;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import hh.sof03.karaokeRooms.Domain.Room;
import hh.sof03.karaokeRooms.Domain.RoomRepository;


@ExtendWith(SpringExtension.class)

@SpringBootTest
public class RoomRepositoryTest {
    
    @Autowired
    private RoomRepository roomRepository;

    @Test //Huoneen lisääminen
    public void addNewRoom() {
        Room room = new Room("Room 3", 5, 100.00);
        roomRepository.save(room);
        assertThat(room.getRoomId()).isNotNull();
    }

    @Test //Huoneen postaminen
    public void deleteRoom() {
        Room room = new Room("Room 3", 5, 100.00);
        roomRepository.save(room);
        roomRepository.deleteById(room.getRoomId());
        boolean roomExists = roomRepository.existsById(room.getRoomId());
        assertThat(roomExists).isFalse();
    }
}
