/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */


/**
 *
 * @author Gabi Caproni
 */
import org.example.academic.system.exception.AuthorizationException;
import org.example.academic.system.model.Role;
import org.example.academic.system.model.User;
import org.example.academic.system.service.AuthorizationService;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class AuthorizationServiceTest {

    @Test
    void shouldAuthorizeAdmin()
            throws AuthorizationException {

        AuthorizationService service =
                new AuthorizationService();

        User admin =
                new User(
                        "admin",
                        "123",
                        Role.ADMIN);

        service.authorize(
                admin,
                Role.ADMIN);
    }

    @Test
    void shouldDenyProfessorAccess() {

        AuthorizationService service =
                new AuthorizationService();

        User professor =
                new User(
                        "professor",
                        "123",
                        Role.PROFESSOR);

        assertThrows(
                AuthorizationException.class,
                () -> service.authorize(
                        professor,
                        Role.ADMIN));
    }

    @Test
    void shouldAuthorizeProfessorForProfessorRole()
            throws AuthorizationException {

        AuthorizationService service =
                new AuthorizationService();

        User professor =
                new User(
                        "professor",
                        "123",
                        Role.PROFESSOR);

        service.authorize(
                professor,
                Role.PROFESSOR);
    }
}