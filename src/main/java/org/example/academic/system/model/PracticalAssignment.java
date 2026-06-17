/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.example.academic.system.model;

public class PracticalAssignment extends Assessment {

    private String technology;

    public PracticalAssignment(String name, double weight, double value, String technology) {
        super(name, weight, value);
        this.technology = technology;
    }
}
