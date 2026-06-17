/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.example.academic.system.exception;

/**
 *
 * @author Gabi Caproni
 */
import org.example.academic.system.security.AuthorizationService;

public class AuthorizationException extends Exception{
    
    public AuthorizationException(String message) {
        super(message);
    }

}
