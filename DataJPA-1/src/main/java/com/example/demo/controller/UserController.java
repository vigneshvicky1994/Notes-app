package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import com.example.demo.dao.notesDAO;
import com.example.demo.entity.User;
import com.example.demo.serviceinterface.SecurityService;
import com.example.demo.serviceinterface.UserService;
import com.example.demo.validator.UserValidator;

@Controller
public class UserController {

  @Autowired
  private notesDAO noteDAO;

  @Autowired
  private UserService userService;

  @Autowired
  private SecurityService securityService;

  @Autowired
  private UserValidator userValidator;

  @GetMapping("/registration")
  public String registration(Model model) {
    model.addAttribute("userForm", new User());

    return "registration";
  }

  @PostMapping("/registration")
  public String registration(@ModelAttribute("userForm") User userForm, BindingResult bindingResult,
      Model model) {
    userValidator.validate(userForm, bindingResult);

    if (bindingResult.hasErrors()) {
      return "registration";
    }

    userService.save(userForm);

    securityService.autoLogin(userForm.getUsername(), userForm.getPasswordConfirm());

    model.addAttribute("Notes", noteDAO.findAll());
    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    String username = auth.getName(); // get logged in username
    model.addAttribute("username", username);
    return "redirect:/";
  }

  @GetMapping("/login")
  public String login(Model model, String error, String logout) {
    if (error != null) {
      model.addAttribute("error", "Your username and password is invalid.");
    }

    if (logout != null) {
      model.addAttribute("message", "You have been logged out successfully.");
    }

    return "login";
  }

  @GetMapping("/logout")
  public String logout() {
    return "redirect:/login?logout";
  }

}
