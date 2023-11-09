package com.forum.Cyberia.controllers;

import com.forum.Cyberia.models.Board;
import com.forum.Cyberia.services.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/board")
public class BoardController {
    @Autowired
    private BoardService service;

    @ModelAttribute("board")
    public Board getBoard(@PathVariable String name) {
        return service.findByName(name);
    }

    @GetMapping(path = "/{name}")
    public String getPosts() {
        return "board";
    }
}
