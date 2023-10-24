package com.bobocode.simplerestdemo.model;

import com.bobocode.simplerestdemo.model.response.UserResponse;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class User {

    private static Long ID_SEQUENCE = 1L;

    private Long id;

    private String firstName;

    private String lastName;

    private List<Note> notes = new ArrayList<>();

    public User() {
        this.id = ID_SEQUENCE;
        ID_SEQUENCE++;
    }

    public UserResponse toUserResponse() {
        UserResponse userResponse = new UserResponse();
        userResponse.setFirstName(this.firstName);
        userResponse.setLastName(this.lastName);
        return userResponse;
    }
}
