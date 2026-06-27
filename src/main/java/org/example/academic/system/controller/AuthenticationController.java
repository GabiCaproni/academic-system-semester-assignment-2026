package org.example.academic.system.controller;

import org.example.academic.system.exception.AuthenticationException;
import org.example.academic.system.logging.ApplicationLogger;
import org.example.academic.system.model.User;
import org.example.academic.system.repository.UserRepository;
import org.example.academic.system.security.SessionManager;

public class AuthenticationController {

    private final UserRepository repository;
    private final SessionManager session;

    public AuthenticationController(
            UserRepository repository,
            SessionManager session) {

        this.repository = repository;
        this.session = session;
    }

    public boolean login(
            String username,
            String password)
            throws AuthenticationException {

        User user =
                repository.findByUsername(username);

        if (user == null) {
            ApplicationLogger.warn(
                    "LOGIN_FAILED - user not found: " + username);
            throw new AuthenticationException(
                    "Usuário não encontrado.");
        }

        if (!user.getPassword().equals(password)) {
            ApplicationLogger.warn(
                    "LOGIN_FAILED - wrong password for user: " + username);
            throw new AuthenticationException(
                    "Senha inválida.");
        }

        session.login(user);
        ApplicationLogger.info(
                "LOGIN_SUCCESS - user: " + username
                + " role: " + user.getRole());

        return true;
    }

     public void logout() {
        User user = session.getLoggedUser();
        if (user != null) {
            ApplicationLogger.info(
                    "LOGOUT - user: " + user.getUsername());
        }
        session.logout();
    }

    public User getLoggedUser() {
        return session.getLoggedUser();
    }
} 
