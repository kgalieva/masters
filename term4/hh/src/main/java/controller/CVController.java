package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import service.UserService;

@Controller
public class CVController {

    private UserService userService;

    @Autowired
    public CVController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping("/cv/{id}")
    public ModelAndView getCV(@PathVariable Long id) {
        return new ModelAndView("cv_page", "cv", userService.getCVById(id));
    }

}
