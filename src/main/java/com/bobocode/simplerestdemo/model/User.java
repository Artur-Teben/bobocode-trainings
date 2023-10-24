package com.bobocode.simplerestdemo.model;

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
}
