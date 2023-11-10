package com.forum.Cyberia.models.forms;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserForm {
    private String username;
    private String email;
    private String description;
    private String password;
}
