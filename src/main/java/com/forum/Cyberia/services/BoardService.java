package com.forum.Cyberia.services;

import com.forum.Cyberia.models.Board;
import com.forum.Cyberia.models.Post;
import com.forum.Cyberia.repositories.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;

@Service
public class BoardService {
    @Autowired
    private BoardRepository repository;

    private Pattern illegalChars = Pattern.compile("[<>{}()_\'~/$º!#@*+%\\[\\]|\"\\_^]");

    public List<Board> findAll() {
        return repository.findAll();
    }

    public Board findByName(String name) {
        return repository.findByName(name);
    }

    public void insert(Board board) {
        validateBoard(board);
        repository.save(board);
    }

    private void validateBoard(Board board) {
        if (board != null && board.getName() != null)
            if (!board.getName().isEmpty() && !board.getName().isBlank())
                if (!illegalChars.matcher(board.getName()).find())
                    return;

        throw new IllegalArgumentException("Board é nula ou contém nome inválido.");
    }
}
