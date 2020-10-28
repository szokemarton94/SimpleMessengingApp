package application.controller;

import application.DTO.RegistrationDTO;
import application.entity.User;
import application.service.UserDetailsManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class UserController {
    @Autowired
    UserDetailsManagerService userDetailsManagerService;

    /**
     * Return custom login page
     **/
    @RequestMapping(method = RequestMethod.GET, value = "/login")
    public String loginPage() {
        return "login";
    }

    /**
     * Return Registration page
     **/
    @RequestMapping(method = RequestMethod.GET, value = "/registration")
    public String registrationPage(
            @ModelAttribute("registrationDTO") RegistrationDTO registrationDTO
    ) {
        return "registration";
    }

    /**
     * Create new User
     **/
    @RequestMapping(method = RequestMethod.POST, value = "/createNewUser")
    public String createNewUser(
            @ModelAttribute RegistrationDTO registrationDTO
    ) {
        userDetailsManagerService.createUser(new User(registrationDTO));
        return "redirect:/login";
    }
}
