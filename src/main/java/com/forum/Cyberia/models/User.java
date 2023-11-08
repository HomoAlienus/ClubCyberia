package com.forum.Cyberia.models;

import com.forum.Cyberia.models.enums.Role;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.*;

@NoArgsConstructor
@AllArgsConstructor
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

    @Column(length = 40, nullable = false)
    @Setter private String password;

    @Setter private Date dateJoined;
    @Setter private Role role;

    @OneToMany(mappedBy = "user")
    private List<Post> posts = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private List<Reply> replies = new ArrayList<>();
}
