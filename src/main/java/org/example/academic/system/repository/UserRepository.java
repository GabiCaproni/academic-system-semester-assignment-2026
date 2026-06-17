/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.example.academic.system.repository;

/**
 *
 * @author Gabi Caproni
 */

import java.util.ArrayList;
import java.util.List;

import org.example.academic.system.model.Role;
import org.example.academic.system.model.User;

public class UserRepository {

    private final List<User> users = new ArrayList<>();

    public UserRepository() {

        users.add(new User("admin", "123", Role.ADMIN));
        users.add(new User("professor", "123", Role.PROFESSOR));
    }

    public User findByUsername(String username) {

        for(User user : users) {

            if(user.getUsername().equals(username)) {
                return user;
            }
        }

        return null;
    }
}
