package com.forum.Cyberia.services;

import com.forum.Cyberia.models.Reply;
import com.forum.Cyberia.repositories.ReplyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReplyService {
    @Autowired
    private ReplyRepository repository;

    public void save(Reply reply) {
        repository.save(reply);
    }
}
