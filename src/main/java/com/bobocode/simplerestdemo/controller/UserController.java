package com.bobocode.simplerestdemo.controller;

import com.bobocode.simplerestdemo.model.Note;
import com.bobocode.simplerestdemo.model.User;
import com.bobocode.simplerestdemo.model.response.NoteResponse;
import com.bobocode.simplerestdemo.model.response.UserResponse;
import com.bobocode.simplerestdemo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;


@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping(produces = "application/hal+json")
    public ResponseEntity<UserResponse> addUser(@RequestBody User user) {
        User savedUser = userService.addUser(user);
        return ResponseEntity.created(getUserByIdLink(savedUser.getId()).toUri())
                .body(savedUser.toUserResponse()
                        .add(getUserByIdLink(savedUser.getId()).withSelfRel())
                        .add(getAllUsersLink().withRel("allUsers")));
    }

    @GetMapping(value = "/{id}", produces = "application/hal+json")
    public ResponseEntity<UserResponse> getUserById(@PathVariable Long id) {
        User user = userService.getUserById(id);
        UserResponse userResponse = user.toUserResponse();
        userResponse.add(getUserByIdLink(user.getId()).withSelfRel()).add(getAllUsersLink().withRel("allUsers"));

        if (!user.getNotes().isEmpty()) {
            userResponse.add(getNotesLink(user.getId()).withRel("notes"));
        }
        return ResponseEntity.ok(userResponse);
    }

    @GetMapping(produces = "application/hal+json")
    public ResponseEntity<CollectionModel<UserResponse>> getAllUsers() {
        List<UserResponse> allUsers = userService.getAllUsers().stream()
                .map(user -> user.toUserResponse().add(getUserByIdLink(user.getId()).withSelfRel()))
                .toList();

        return ResponseEntity.ok(CollectionModel.of(allUsers, getAllUsersLink().withSelfRel()));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removeUser(@PathVariable Long id) {
        userService.removeUser(id);
    }

    @PostMapping(value = "/{userId}/notes", produces = "application/hal+json")
    public ResponseEntity<NoteResponse> addNote(@PathVariable Long userId, @RequestBody Note note) {
        Note savedNote = userService.addNote(userId, note);
        return ResponseEntity.created(getNotesLink(userId).withSelfRel().toUri())
                .body(savedNote.toNoteResponse().add(getNotesLink(userId).withSelfRel()));
    }

    @GetMapping(value = "/{userId}/notes", produces = "application/hal+json")
    public ResponseEntity<CollectionModel<NoteResponse>> getUserNotes(@PathVariable Long userId) {
        List<NoteResponse> userNotes = userService.getUserNotes(userId).stream()
                .map(Note::toNoteResponse)
                .toList();
        return ResponseEntity.ok(CollectionModel.of(userNotes, getNotesLink(userId).withSelfRel()));
    }

    private static WebMvcLinkBuilder getAllUsersLink() {
        return linkTo(methodOn(UserController.class).getAllUsers());
    }

    private static WebMvcLinkBuilder getUserByIdLink(Long userId) {
        return linkTo(methodOn(UserController.class).getUserById(userId));
    }

    private static WebMvcLinkBuilder getNotesLink(Long userId) {
        return linkTo(methodOn(UserController.class).getUserNotes(userId));
    }
}
