package com.bobocode.simplerestdemo.model;

import com.bobocode.simplerestdemo.model.response.NoteResponse;
import lombok.Data;

@Data
public class Note {

    private String title;
    private String text;

    public NoteResponse toNoteResponse() {
        NoteResponse noteResponse = new NoteResponse();
        noteResponse.setTitle(this.getTitle());
        noteResponse.setText(this.getText());
        return noteResponse;
    }
}
