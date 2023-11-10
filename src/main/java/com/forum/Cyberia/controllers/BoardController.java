package com.forum.Cyberia.controllers;

import com.forum.Cyberia.models.Board;
import com.forum.Cyberia.models.Post;
import com.forum.Cyberia.services.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/board")
public class BoardController {
    @Autowired
    private BoardService service;

    @GetMapping(path = "/{board}")
    public String getPosts(Model model, @PathVariable String board) {
        model.addAttribute("board", service.findByName(board));
        return "board";
    }

    @GetMapping(path = "/add")
    public String addBoard(Board board) {
        return "addBoard";
    }

    @PostMapping(path = "/add", params = {"save"})
    public String saveBoard(Board board, BindingResult bindingResult, ModelMap model) {
        if (bindingResult.hasErrors())
            return "addBoard";

        service.insert(board);
        model.clear();

        return "redirect:/index";
    }

    @GetMapping(path = "/{board}/{id}")
    public String getPost(@PathVariable("board") String board, @PathVariable("id") Long id, Model model) {
        Board boardObj = service.findByName(board);
        model.addAttribute("board", boardObj);
        Post post = boardObj.getPosts().stream().filter(b -> id.equals(b.getId())).findFirst().orElse(null);

        model.addAttribute("post", post);
        return "post";
    }
}
