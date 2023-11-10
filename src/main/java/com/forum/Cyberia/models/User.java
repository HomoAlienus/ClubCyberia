package com.forum.Cyberia.models;

import com.forum.Cyberia.models.enums.Role;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.*;

@NoArgsConstructor
@Getter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "tb_user")
public class User implements Serializable {
    @Id
    @Column(length = 30, nullable = false, unique = true)
    @EqualsAndHashCode.Include
    @Setter private String username;

    @Column(nullable = false, unique = true)
    @Setter private String email;

    @Column(length = 120, nullable = false)
    @Setter private String password;

    @Setter private String description;

    @Setter private Date dateJoined;
    @Setter private Role role;

    @OneToMany(mappedBy = "author")
    private List<Post> posts = new ArrayList<>();

    @OneToMany(mappedBy = "author")
    private List<Reply> replies = new ArrayList<>();

    public User(String username, String email, String password, Date dateJoined, Role role, String description) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.dateJoined = dateJoined;
        this.role = role;
        this.description = description;
    }
}
