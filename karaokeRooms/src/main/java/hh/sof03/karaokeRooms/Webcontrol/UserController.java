package hh.sof03.karaokeRooms.Webcontrol;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import hh.sof03.karaokeRooms.Domain.User;
import hh.sof03.karaokeRooms.Domain.UserRepository;

@Controller
public class UserController {

    @Autowired
    private UserRepository userRepository;
    
    //Palauttaa listan käyttäjistä
    @RequestMapping(value = "/userlist", method = RequestMethod.GET)
    @PreAuthorize("hasAuthority('ADMIN')")
	public String getUsers(Model model) {
		model.addAttribute("users", userRepository.findAll());
		return "userlist"; //userlist.html
	}

    //Palauttaa käyttäjän lismäämis lomakkeen
    @RequestMapping(value = "/adduser")
	public String addUser(Model model) {
		model.addAttribute("user", new User());
		return "adduser"; //adduser.html
	}

    //Tallaettaa lomakkeelta tulleet tiedot tietokantaan
	@RequestMapping(value = "/saveuser")
	public String saveUser(User user) {
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    	String hashedPassword = passwordEncoder.encode(user.getPasswordHash());
   		user.setPasswordHash(hashedPassword);
		user.setRole("USER");
		userRepository.save(user);
		return "redirect:/roomlist";
	}
}
