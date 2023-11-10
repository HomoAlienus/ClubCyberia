package com.forum.Cyberia.models;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.time.Instant;
import java.util.List;
import java.util.ArrayList;

@NoArgsConstructor
@Getter
@Entity
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Post implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    @Setter private Long id;

    @Setter private String title;
    @Setter private String content;
    @Setter private Instant instantPosted;

    @ManyToOne
    @JoinColumn(name = "author_id")
    @Setter private User author;

    @OneToMany(mappedBy = "post")
    private List<Reply> replies = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "board_name")
    @Setter private Board board;

    public Post(Long id, String title, String content, Instant instantPosted, User author, Board board) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.instantPosted = instantPosted;
        this.author = author;
        this.board = board;
    }
}
