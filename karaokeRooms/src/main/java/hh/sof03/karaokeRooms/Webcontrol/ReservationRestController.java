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

import hh.sof03.karaokeRooms.Domain.Reservation;
import hh.sof03.karaokeRooms.Domain.ReservationRepository;

@CrossOrigin
@Controller
public class ReservationRestController {
    
    @Autowired
    private ReservationRepository reservationRepository;

    // Palauttaa kaikki varaukset REST-rajapinnan kautta
    @RequestMapping (value = "/reservations", method = RequestMethod.GET)
    public @ResponseBody List<Reservation>reservationListRest() {
        return (List<Reservation>)reservationRepository.findAll();
    }

    // Palauttaa tietyn varauksen REST-rajapinnan kautta, tunnistettuna sen id:llä
    @RequestMapping (value = "/reservations/{id}", method = RequestMethod.GET)
    public @ResponseBody Optional<Reservation>findReservationRest(@PathVariable("id") Long reservationId) {
        return reservationRepository.findById(reservationId);
    }
}
