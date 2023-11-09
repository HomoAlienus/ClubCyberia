package com.forum.Cyberia.models;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Getter
@Entity
public class Board implements Serializable {
    @Id
    @EqualsAndHashCode.Include
    @Column(nullable = false, unique = true)
    @Setter private String name;

    @Setter private String description;

    @OneToMany(mappedBy = "board")
    private Map<Long, Post> posts = new HashMap<Long, Post>();

    public Board(String name, String description) {
        this.name = name;
        this.description = description;
    }
}
