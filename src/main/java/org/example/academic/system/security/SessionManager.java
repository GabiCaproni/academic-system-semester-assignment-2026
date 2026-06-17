package org.example.academic.system.security;

import org.example.academic.system.model.Role;
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

    public boolean hasRole(Role role) {

        if (!isAuthenticated()) {
            return false;
        }

        return loggedUser.getRole() == role;
    }

    public boolean isAdmin() {
        return hasRole(Role.ADMIN);
    }

    public boolean isProfessor() {
        return hasRole(Role.PROFESSOR);
    }
}