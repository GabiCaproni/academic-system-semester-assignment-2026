/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.example.academic.system.model;

public class Seminar extends Assessment {

    private String topic;

    public Seminar(String name, double weight, double value, String topic) {
        super(name, weight, value);
        this.topic = topic;
    }
}
