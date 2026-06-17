/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.example.academic.system.controller;

/**
 *
 * @author Gabi Caproni
 */

import org.example.academic.system.exception.AuthenticationException;
import org.example.academic.system.model.User;
import org.example.academic.system.repository.UserRepository;
import org.example.academic.system.security.SessionManager;

public class AuthenticationController {

    private final UserRepository repository;
    private final SessionManager session;

    public AuthenticationController(UserRepository repository,SessionManager session) {

        this.repository = repository;
        this.session = session;
    }

    public boolean login(String username, String password) throws AuthenticationException {

    User user = repository.findByUsername(username);

    if (user == null) {
        throw new AuthenticationException(
                "Usuário não encontrado.");
    }

    if (!user.getPassword().equals(password)) {
        throw new AuthenticationException(
                "Senha inválida.");
    }

    session.login(user);

    return true;
}

    public void logout() {
        session.logout();
    }

    public User getLoggedUser() {
        return session.getLoggedUser();
    }
}