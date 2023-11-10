package com.forum.Cyberia.controllers;

import com.forum.Cyberia.models.User;
import com.forum.Cyberia.models.UserDTO;
import com.forum.Cyberia.models.forms.ReplyForm;
import com.forum.Cyberia.models.forms.UserForm;
import com.forum.Cyberia.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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

    @GetMapping(path = "/desc")
    public String desc(ReplyForm replyForm, Authentication authentication, ModelMap model) {
        User user = userService.findByUsername(authentication.getName());

        model.addAttribute("user", UserDTO.fromUser(user));
        return "changeDesc";
    }

    @PostMapping(path = "/desc", params = {"save"})
    public String saveDesc(ReplyForm replyForm, Authentication authentication, ModelMap model) {
        User user = userService.findByUsername(authentication.getName());

        if (replyForm.getContent().isBlank() || replyForm.getContent().isEmpty()) {
            model.addAttribute("error", "A descrição não pode ser vazia.");
            model.addAttribute("user", UserDTO.fromUser(user));
            return "changeDesc";
        }

        user.setDescription(replyForm.getContent());
        userService.save(user);

        return String.format("redirect:/users/%s", user.getUsername());
    }
}
