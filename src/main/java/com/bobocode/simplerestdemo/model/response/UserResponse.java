package com.bobocode.simplerestdemo.model.response;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.hateoas.RepresentationModel;

@Data
@EqualsAndHashCode(callSuper = true)
public class UserResponse extends RepresentationModel<UserResponse> {

    private String firstName;
    private String lastName;
}
