package com.forum.Cyberia.models;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Getter
@Entity
public class Topic implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    @Setter private Integer id;

    @Column(nullable = false, unique = true)
    @Setter private String name;

    @Setter private String description;

    @OneToMany(mappedBy = "topic")
    private Set<Post> posts = new HashSet<>();
}
