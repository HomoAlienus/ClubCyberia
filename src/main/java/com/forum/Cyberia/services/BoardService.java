package com.forum.Cyberia.services;

import com.forum.Cyberia.models.Board;
import com.forum.Cyberia.models.Post;
import com.forum.Cyberia.repositories.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BoardService {
    @Autowired
    private BoardRepository repository;

    public List<Board> findAll() {
        return repository.findAll();
    }

    public Board findByName(String name) {
        return repository.findByName(name);
    }

    public void insert(Board board) {
        repository.save(board);
    }
}
