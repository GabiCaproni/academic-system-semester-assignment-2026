package org.example.academic.system.security;

import org.example.academic.system.exception.AuthorizationException;
import org.example.academic.system.model.Role;
import org.example.academic.system.model.User;

public class AuthorizationService {

    public void authorize(
            User user,
            Role requiredRole)
            throws AuthorizationException {

        if (user == null) {

            throw new AuthorizationException(
                    "Usuário não autenticado.");
        }

        if (user.getRole() != requiredRole) {

            throw new AuthorizationException(
                    "Acesso negado.");
        }
    }
}