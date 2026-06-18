/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.example.academic.system.model;
import org.example.academic.system.exception.InvalidAssessmentException;

public abstract class Assessment {

    private String name;
    private double weight;
    private double value;

    public Assessment() {
    }

    public Assessment(
        String name,
        double weight,
        double value) {

    if (name == null || name.isBlank()) {
        throw new InvalidAssessmentException(
                "Nome da avaliação é obrigatório.");
    }

    if (weight <= 0) {
        throw new InvalidAssessmentException(
                "Peso deve ser maior que zero.");
    }

    if (value <= 0) {
        throw new InvalidAssessmentException(
                "Valor deve ser maior que zero.");
    }

    this.name = name;
    this.weight = weight;
    this.value = value;
}
    public String getName() {
        return name;
    }

    public double getWeight() {
        return weight;
    }

    public double getValue() {
        return value;
    }
}