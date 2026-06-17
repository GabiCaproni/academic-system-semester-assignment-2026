/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package org.example.academic.system.service;

/**
 *
 * @author Gabi Caproni
 */
import org.example.academic.system.model.Role;
import org.example.academic.system.model.User;

public class AuthorizationService {

    public boolean isAdmin(User user) {

        return user.getRole() == Role.ADMIN;
    }

    public boolean isProfessor(User user) {

        return user.getRole() == Role.PROFESSOR;
    }
}
