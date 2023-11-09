package com.forum.Cyberia;


import com.forum.Cyberia.models.Board;
import com.forum.Cyberia.models.User;
import com.forum.Cyberia.models.UserDTO;
import com.forum.Cyberia.services.BoardService;
import com.forum.Cyberia.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
            model.addAttribute("authenticated", "true");
            User user = userService.findByUsername(authentication.getName());
            model.addAttribute("user", UserDTO.fromUser(user));
        }
        return "index";
    }

    @RequestMapping("/login")
    public String login() {
        return "login";
    }
}
