package application.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserController {

    /**
     * Return custom login page
     **/
    @GetMapping(value = "/login")
    public String loginPage() {
        return "login";
    }
}
