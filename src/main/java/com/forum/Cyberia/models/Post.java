package com.forum.Cyberia.models;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.time.Instant;
import java.util.List;
import java.util.ArrayList;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Entity
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Post implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    @Setter private Long id;

    @Setter private String title;
    @Setter private Instant instantPosted;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @Setter private User user;

    @OneToMany(mappedBy = "post")
    private List<Reply> replies = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "topic_id")
    private Topic topic;

}
