package hh.sof03.karaokeRooms.Webcontrol;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import hh.sof03.karaokeRooms.Domain.Room;
import hh.sof03.karaokeRooms.Domain.RoomRepository;

@CrossOrigin
@Controller
public class RoomRestController {
    
    @Autowired
    private RoomRepository roomRepository;
    
    // Palauttaa kaikki huoneet REST-rajapinnan kautta
    @RequestMapping (value = "/rooms", method = RequestMethod.GET)
    public @ResponseBody List<Room>roomListRest() {
        return (List<Room>)roomRepository.findAll();
    }

    // Palauttaa tietyn huoneen REST-rajapinnan kautta, tunnistettuna sen id:llä
    @RequestMapping (value = "/rooms/{id}", method = RequestMethod.GET)
    public @ResponseBody Optional<Room>findRoomRest(@PathVariable("id") Long roomId) {
        return roomRepository.findById(roomId);
    }
}
