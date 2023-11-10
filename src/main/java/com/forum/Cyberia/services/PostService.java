package com.forum.Cyberia.services;

import com.forum.Cyberia.models.Post;
import com.forum.Cyberia.repositories.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PostService {
    @Autowired
    private PostRepository repository;

    public void save(Post post) {
        repository.save(post);
    }
}
