package com.springapp.mvc;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Controller
public class ContactController {

    List<Contact> contacts = new ArrayList<>();

   @RequestMapping(value = "/", method = RequestMethod.GET)
   public ModelAndView student() {
      return new ModelAndView("index", "command", new Contact());
   }
   
   @RequestMapping(value = "/addContact", method = RequestMethod.POST)
   public String addStudent(@ModelAttribute Contact contact,
   ModelMap model) {
      if(contact != null && contact.getName() != null) {
          contacts.add(contact);
      }
      model.addAttribute("contacts", contacts);
      return "result";
   }
}