package hh.sof03.karaokeRooms;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import hh.sof03.karaokeRooms.Domain.User;
import hh.sof03.karaokeRooms.Domain.UserRepository;


@ExtendWith(SpringExtension.class)

@SpringBootTest
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test //Käyttäjän lisääminen
    public void addNewUser () {
        User user = new User("Roope", "$2a$10$87kpttlb0OK0HSZwhJGgDery7F5Y6i9/jKsBsSw.TRZp6TWIccm7W" , "USER" ,"roope@ankka.com");
        userRepository.save(user);
        assertThat(user.getUserId()).isNotNull();
    }
    
}
