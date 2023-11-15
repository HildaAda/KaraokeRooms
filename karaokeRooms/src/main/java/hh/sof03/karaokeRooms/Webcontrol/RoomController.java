package hh.sof03.karaokeRooms.Webcontrol;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.ui.Model;

import hh.sof03.karaokeRooms.Domain.Room;
import hh.sof03.karaokeRooms.Domain.RoomRepository;

@Controller
public class RoomController {
    
    @Autowired
    private RoomRepository roomRepository;

    //Palauttaa listausken huoneista
    @RequestMapping(value = "/roomlist", method = RequestMethod.GET)
    public String getRooms(Model model) {
        model.addAttribute("rooms", roomRepository.findAll());
        return "roomlist"; //roomlist.html
    }

    //Avaa huoneen lisäykseen tarkoitetun lomakkeen
    @RequestMapping(value = "/addroom")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String addRoom(Model model) {
        model.addAttribute("room", new Room());
        return "addroom"; //addroom.html
    }

    //Poistaa valitun huoneen
   @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
   @PreAuthorize("hasAuthority('ADMIN')")
    public String deleteRoom(@PathVariable("id") Long roomId, Model model) {
        roomRepository.deleteById(roomId);
        return "redirect:/roomlist";
    }

    //Tallettaa lomakkeelta tulleet huoneen tiedot tietokantaan
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String save (Room room) {
        roomRepository.save(room);
        return "redirect:roomlist";
    }

}
