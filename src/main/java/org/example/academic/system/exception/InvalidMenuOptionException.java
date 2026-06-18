/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.example.academic.system.exception;

/**
 *
 * @author Gabi Caproni
 */

public class InvalidMenuOptionException
        extends KeyboardInputException {

    public InvalidMenuOptionException(
            String message) {

        super(message);
    }
}