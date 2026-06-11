/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.example.academic.system.model;

public abstract class Assessment {

    private String name;
    private double weight;
    private double value;

    public Assessment() {
    }

    public Assessment(String name, double weight, double value) {
        this.name = name;
        this.weight = weight;
        this.value = value;
    }
}