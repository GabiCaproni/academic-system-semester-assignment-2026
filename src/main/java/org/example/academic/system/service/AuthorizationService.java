package org.example.academic.system.service;

import org.example.academic.system.exception.AuthorizationException;
import org.example.academic.system.logging.ApplicationLogger;
import org.example.academic.system.model.Role;
import org.example.academic.system.model.User;

/**
 * TUS-2392 - Log authorization failures
 */
public class AuthorizationService {

    public boolean isAdmin(User user) {

        return user != null
                && user.getRole() == Role.ADMIN;
    }

    public boolean isProfessor(User user) {

        return user != null
                && user.getRole() == Role.PROFESSOR;
    }

    public void authorize(
            User user,
            Role requiredRole)
            throws AuthorizationException {

        if (user == null) {

            ApplicationLogger.warn(
                    "AUTHORIZATION_FAILED - acesso nao autenticado"
                    + " tentou operacao que requer: "
                    + requiredRole);

            throw new AuthorizationException(
                    "Usuário não autenticado.");
        }

        if (user.getRole() != requiredRole) {

            ApplicationLogger.warn(
                    "AUTHORIZATION_FAILED - usuario: "
                    + user.getUsername()
                    + " | role atual: " + user.getRole()
                    + " | role exigida: " + requiredRole);

            throw new AuthorizationException(
                    "Acesso negado.");
        }
    }
}