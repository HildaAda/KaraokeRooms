package hh.sof03.karaokeRooms.Webcontrol;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.ui.Model;

import hh.sof03.karaokeRooms.Domain.Reservation;
import hh.sof03.karaokeRooms.Domain.ReservationRepository;
import hh.sof03.karaokeRooms.Domain.RoomRepository;
import hh.sof03.karaokeRooms.Domain.User;
import hh.sof03.karaokeRooms.Domain.UserRepository;

@Controller
public class ReservationController {
    
    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private RoomRepository roomRepository;
    
    @Autowired
    private UserRepository userRepository;

    //Palauttaa yhden huoneen kaikki varaukset
    @RequestMapping(value = "/reservationlist/{roomId}", method = RequestMethod.GET)
    @PreAuthorize("hasAuthority('ADMIN')")
    public String getReservations(@PathVariable ("roomId") Long roomId, Model model) {
        List<Reservation> reservations = reservationRepository.findByRoomRoomId(roomId);
        model.addAttribute("reservations", reservations);
        return "reservations"; //reservations.html
    }

    //Plauttaa kirjautuneen käyttäjän kaikki varaukset
    @RequestMapping(value = "/userreservations", method = RequestMethod.GET)
    @PreAuthorize("hasAuthority('USER')")
    public String getUsersReservations(Model model, Principal principal) {
        String username = principal.getName();
        User user = userRepository.findByUsername(username);
        model.addAttribute("reservations", reservationRepository.findByUserUserId(user.getUserId()));
        return "userreservations"; //userreservations.html
    }

    //Palauttaa yhden käyttäjän kaikki varaukset
    @RequestMapping(value = "/userreservationlist/{userId}", method = RequestMethod.GET)
    @PreAuthorize("hasAuthority('ADMIN')")
    public String getUsersReservationlist(@PathVariable ("userId") Long userId, Model model) {
        List<Reservation> reservations = reservationRepository.findByUserUserId(userId);
        model.addAttribute("reservations", reservations);
        return "userreservationlist"; //userreservationlist.html
    }

    //Paluttaa varauksen luonti lomakkeen
    @RequestMapping(value = "/addreservation", method = RequestMethod.GET)
	public String addReservation(Model model, Principal principal) {
        String username = principal.getName();
        User user = userRepository.findByUsername(username);   
		model.addAttribute("reservation", new Reservation());
        model.addAttribute("user", user); 
        model.addAttribute("rooms", roomRepository.findAll());
		return "addreservation"; //addreservation.html
	}

    //Tallettaa lomakkeelta tulleet varaustiedot tietokantaan
    @RequestMapping(value = "/savereservation", method = RequestMethod.POST)
	public String saveReservation(@ModelAttribute("reservation") Reservation reservation, Model model, Principal principal) {
        String username = principal.getName();
        User user = userRepository.findByUsername(username);
        reservation.setUser(user);
		reservationRepository.save(reservation);
		return "redirect:/roomlist";
	}

     //Poistaa valitun varauksen
   @RequestMapping(value = "/deletereservation/{id}", method = RequestMethod.GET)
   @PreAuthorize("hasAuthority('ADMIN')")
    public String deleteReservation(@PathVariable("id") Long reservationId, Model model) {
        reservationRepository.deleteById(reservationId);
        return "redirect:/roomlist";
    }
    
}
