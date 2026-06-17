package org.example.academic.system.security;

import org.example.academic.system.model.User;

public class SessionManager {

    private User loggedUser;

    public void login(User user) {
        this.loggedUser = user;
    }

    public void logout() {
        this.loggedUser = null;
    }

    public User getLoggedUser() {
        return loggedUser;
    }

    public boolean isAuthenticated() {
        return loggedUser != null;
    }
}