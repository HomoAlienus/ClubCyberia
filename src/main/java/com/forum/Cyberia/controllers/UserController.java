package com.forum.Cyberia.controllers;

import com.forum.Cyberia.models.User;
import com.forum.Cyberia.models.UserDTO;
import com.forum.Cyberia.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping(path = "/{username}")
    public String getUserProfile(@PathVariable String username, ModelMap model) {
        User user = userService.findByUsername(username);

        model.addAttribute("user", UserDTO.fromUser(user));
        model.addAttribute("posts", user.getPosts());

        return "userprofile";
    }
}
