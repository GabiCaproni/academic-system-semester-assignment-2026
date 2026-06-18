/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */


/**
 *
 * @author Gabi Caproni
 */

import org.example.academic.system.controller.AuthenticationController;
import org.example.academic.system.exception.AuthenticationException;
import org.example.academic.system.repository.UserRepository;
import org.example.academic.system.security.SessionManager;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class AuthenticationControllerTest {

    @Test
    void shouldAuthenticateValidUser()
            throws AuthenticationException {

        UserRepository repository =
                new UserRepository();

        SessionManager session =
                new SessionManager();

        AuthenticationController controller =
                new AuthenticationController(
                        repository,
                        session);

        boolean result =
                controller.login(
                        "admin",
                        "123");

        assertTrue(result);
    }

    @Test
    void shouldThrowExceptionForInvalidUser() {

        UserRepository repository =
                new UserRepository();

        SessionManager session =
                new SessionManager();

        AuthenticationController controller =
                new AuthenticationController(
                        repository,
                        session);

        assertThrows(
                AuthenticationException.class,
                () -> controller.login(
                        "naoExiste",
                        "123"));
    }

    @Test
    void shouldThrowExceptionForInvalidPassword() {

        UserRepository repository =
                new UserRepository();

        SessionManager session =
                new SessionManager();

        AuthenticationController controller =
                new AuthenticationController(
                        repository,
                        session);

        assertThrows(
                AuthenticationException.class,
                () -> controller.login(
                        "admin",
                        "senhaErrada"));
    }
}
