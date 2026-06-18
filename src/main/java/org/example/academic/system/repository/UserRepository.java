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
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class UserRepository {

    private final List<User> users = new ArrayList<>();

    public UserRepository() {

        loadUsers();
    }

    public User findByUsername(String username) {

        for (User user : users) {

            if (user.getUsername().equals(username)) {
                return user;
            }
        }

        return null;
    }

    private void loadUsers() {

        File file = new File("users.txt");

        System.out.println("Procurando arquivo em:");
        System.out.println(file.getAbsolutePath());
        System.out.println("Existe? " + file.exists());

        try (BufferedReader reader
                = new BufferedReader(
                        new FileReader("user.txt"))) {

            String line;

            while ((line = reader.readLine()) != null) {

                String[] data = line.split(";");

                String username = data[0];
                String password = data[1];
                Role role = Role.valueOf(data[2]);

                users.add(
                        new User(
                                username,
                                password,
                                role));
            }

        } catch (IOException e) {

            System.out.println(
                    "Erro ao carregar usuários.");
        }
    }
}
