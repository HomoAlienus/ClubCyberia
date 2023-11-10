package com.forum.Cyberia.controllers;

import com.forum.Cyberia.models.User;
import com.forum.Cyberia.models.enums.Role;
import com.forum.Cyberia.models.exceptions.UserCreationException;
import com.forum.Cyberia.models.forms.UserForm;
import com.forum.Cyberia.services.UserService;
import org.postgresql.util.PSQLException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Calendar;

@Controller
@RequestMapping("/register")
public class RegisterController {
    @Autowired
    private UserService service;

    @GetMapping
    public String register(UserForm userForm, Authentication authentication) {
        if (authentication != null)
            return "redirect:/index";

        return "register";
    }

    @PostMapping(params = {"save"})
    public String newUser(UserForm userForm, BindingResult bindingResult, Authentication authentication, ModelMap model) {
        if (bindingResult.hasErrors())
            return "register";

        if (authentication != null)
            return "redirect:/index";

        User user = new User(userForm.getUsername(), userForm.getEmail(),
                userForm.getPassword(), Calendar.getInstance().getTime(),
                Role.USER, userForm.getDescription());

        try{
            service.insert(user);
            model.clear();
            return "redirect:/login";
        } catch (UserCreationException e) {
            model.addAttribute("error", e.getMessage());
            return "register";
        } catch (Exception e) {
            model.addAttribute("error", "E-mail já cadastrado.");
            return "register";
        }
    }
}
