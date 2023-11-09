package com.forum.Cyberia;


import com.forum.Cyberia.models.Board;
import com.forum.Cyberia.models.User;
import com.forum.Cyberia.models.UserDTO;
import com.forum.Cyberia.services.BoardService;
import com.forum.Cyberia.services.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class IndexController {
    @Autowired
    private BoardService boardService;
    @Autowired
    private UserService userService;

    @ModelAttribute("boards")
    public List<Board> getBoards(){
        return boardService.findAll();
    }

    @RequestMapping({"/", "/index"})
    public String home(Model model, Authentication authentication){
        if (authentication != null) {
            User user = userService.findByUsername(authentication.getName());
            model.addAttribute("user", UserDTO.fromUser(user));
        }
        return "index";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/logout")
    public String logout(Authentication authentication, HttpServletRequest request, HttpServletResponse response) {
        if (authentication != null)
            new SecurityContextLogoutHandler().logout(request, response, authentication);
        return "redirect:/index";
    }
}
