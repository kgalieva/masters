package controller;

import controller.editor.CategoryEditor;
import model.CV;
import model.Category;
import model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import service.SearchService;
import service.UserService;
import viewobject.CVVO;

import java.util.ArrayList;
import java.util.List;

@Controller
public class CVController {

    private UserService userService;
    private SearchService searchService;

    @Autowired
    public CVController(UserService userService, SearchService searchService) {
        this.userService = userService;
        this.searchService = searchService;
    }

    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(Category.class, new CategoryEditor());
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

    @RequestMapping("/cv/save")
    public String saveCV(CV cv) {
        cv.setOwner(new User(1L));
        userService.saveCV(cv);
        return "redirect:/cv/list";
    }

    @RequestMapping("/cv/search")
    public @ResponseBody
    List<CVVO> getCVs(@RequestParam String term) {
        Iterable<CV> cvs = userService.getCVsByNamePart(term);
        List<CVVO> result = new ArrayList<CVVO>();
        for (CV cv:cvs) {
            result.add(new CVVO(cv.getId(), cv.getTitle()));
        }
        return result;
    }

}
