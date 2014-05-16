package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import service.UserService;

import java.math.BigInteger;

@Controller
public class UserProfileController {

    private UserService userService;

    @Autowired
    public UserProfileController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping("/user/{id}")
    public ModelAndView getUser(@PathVariable BigInteger id) {
        return new ModelAndView("user_profile", "user", userService.getUserById(id));
    }
}
