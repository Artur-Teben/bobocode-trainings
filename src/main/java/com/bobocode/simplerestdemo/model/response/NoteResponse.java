package com.bobocode.simplerestdemo.model.response;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.hateoas.RepresentationModel;

@Data
@EqualsAndHashCode(callSuper = true)
public class NoteResponse extends RepresentationModel<NoteResponse> {

    private String title;
    private String text;
}
