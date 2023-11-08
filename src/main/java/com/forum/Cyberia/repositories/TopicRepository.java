package com.forum.Cyberia.repositories;

import com.forum.Cyberia.models.Topic;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TopicRepository extends JpaRepository<Topic, Integer> { }
