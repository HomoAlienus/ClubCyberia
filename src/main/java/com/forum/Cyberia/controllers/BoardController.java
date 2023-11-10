package com.forum.Cyberia.controllers;

import com.forum.Cyberia.models.Board;
import com.forum.Cyberia.models.Post;
import com.forum.Cyberia.models.PostForm;
import com.forum.Cyberia.models.User;
import com.forum.Cyberia.services.BoardService;
import com.forum.Cyberia.services.PostService;
import com.forum.Cyberia.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Calendar;

@Controller
@RequestMapping("/board")
public class BoardController {
    @Autowired
    private BoardService boardService;
    @Autowired
    private UserService userService;
    @Autowired
    private PostService postService;

    @GetMapping(path = "/{board}")
    public String getPosts(PostForm postForm, Model model, @PathVariable String board) {
        model.addAttribute("board", boardService.findByName(board));
        return "board";
    }

    @PostMapping(path = "/{board}", params = {"save"})
    public String addPost(PostForm postForm, BindingResult bindingResult,
                          @PathVariable String board, Authentication authentication, ModelMap model) {
        if (bindingResult.hasErrors())
            return "board";

        Board boardObj = boardService.findByName(board);

        User user = userService.findByUsername(authentication.getName());

        Post post = new Post(null, postForm.getTitle(), postForm.getContent().replaceAll("\n", "<br/>"),
                Calendar.getInstance().toInstant(), user, boardObj);

        postService.save(post);
        model.clear();

        return String.format("redirect:/board/%s/%d", board, post.getId());
    }

    @GetMapping(path = "/add")
    public String addBoard(Board board) {
        return "addBoard";
    }

    @PostMapping(path = "/add", params = {"save"})
    public String saveBoard(Board board, BindingResult bindingResult, ModelMap model) {
        if (bindingResult.hasErrors())
            return "addBoard";

        boardService.insert(board);
        model.clear();

        return "redirect:/index";
    }

    @GetMapping(path = "/{board}/{id}")
    public String getPost(@PathVariable("board") String board, @PathVariable("id") Long id, Model model) {
        Board boardObj = boardService.findByName(board);
        model.addAttribute("board", boardObj);
        Post post = boardObj.getPosts().stream().filter(b -> id.equals(b.getId())).findFirst().orElse(null);

        model.addAttribute("post", post);
        return "post";
    }
}
