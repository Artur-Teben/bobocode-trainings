package com.bobocode.simplerestdemo.repository;

import com.bobocode.simplerestdemo.model.Note;
import com.bobocode.simplerestdemo.model.User;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class UserRepository {

    private final Map<Long, User> users = new HashMap<>();

    public void addUser(User user) {
        users.put(user.getId(), user);
    }

    public User getUserById(Long id) {
        return users.get(id);
    }

    public Collection<User> getAllUsers() {
        return users.values();
    }

    public void removeUser(Long id) {
        users.remove(id);
    }

    public void addNote(Long userId, Note note) {
        users.get(userId).getNotes().add(note);
    }

    public List<Note> getUserNotes(Long userId) {
        return users.get(userId).getNotes();
    }
}
