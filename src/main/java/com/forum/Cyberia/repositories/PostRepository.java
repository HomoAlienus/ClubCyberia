package com.forum.Cyberia.repositories;

import com.forum.Cyberia.models.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> { }
