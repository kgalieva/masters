package controller;

import model.CV;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import service.SearchService;
import service.UserService;

@Controller
public class CVController {

    private UserService userService;
    private SearchService searchService;

    @Autowired
    public CVController(UserService userService, SearchService searchService) {
        this.userService = userService;
        this.searchService = searchService;
    }

    @RequestMapping("/cv/{id}")
    public ModelAndView getCV(@PathVariable Long id) {
        return new ModelAndView("cv_page", "cv", userService.getCVById(id));
    }

    @RequestMapping("/cv/list")
    public ModelAndView getCV() {
        ModelAndView mv = new ModelAndView("cv_list");
        mv.addObject("cvList", userService.getAllCVs());
        mv.addObject("category", 0);
        mv.addObject("allCategories", searchService.getAllCategories());
        return mv;
    }

    @RequestMapping("/cv/list/{categoryId}")
    public ModelAndView getCVByCategory(@PathVariable Long categoryId) {
        ModelAndView mv = new ModelAndView("cv_list");
        mv.addObject("cvList", userService.getCVListByCategoryId(categoryId));
        mv.addObject("category", categoryId);
        mv.addObject("allCategories", searchService.getAllCategories());
        return mv;
    }

    @RequestMapping("/cv/edit/{id}")
    public ModelAndView editCV(@PathVariable Long id) {
        ModelAndView mv = new ModelAndView("cv_edit");
        mv.addObject("cv", userService.getCVByIdWithCategories(id));
        mv.addObject("allCategories", searchService.getAllCategories());
        return mv;
    }

    @RequestMapping("/cv/create")
    public ModelAndView editCV() {
        ModelAndView mv = new ModelAndView("cv_edit");
        mv.addObject("cv", new CV());
        mv.addObject("allCategories", searchService.getAllCategories());
        return mv;
    }

}
