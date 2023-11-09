package com.forum.Cyberia.models;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Date;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class UserDTO {
    private String username;
    private Date dateJoined;
    private String description;

    public static UserDTO fromUser(User user) {
        return new UserDTO(user.getUsername(),
                user.getDateJoined(),
                user.getDescription());
    }
}
