package com.forum.Cyberia.repositories;

import com.forum.Cyberia.models.Board;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardRepository extends JpaRepository<Board, Integer> {
    Board findByName(String name);
}
