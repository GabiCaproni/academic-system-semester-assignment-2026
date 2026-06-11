/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.example.academic.system.controller;

/**
 *
 * @author Gabi Caproni
 */
import java.util.ArrayList;
import java.util.List;
import org.example.academic.system.model.AcademicClass;
import org.example.academic.system.model.Assessment;

public class AcademicSystemController {

    private List<AcademicClass> classes;

    public AcademicSystemController() {
        classes = new ArrayList<>();
    }

    public void registerClass(AcademicClass academicClass) {
        classes.add(academicClass);
    }

    public void registerAssessment(
            AcademicClass academicClass,
            Assessment assessment) {

        academicClass.addAssessment(assessment);
    }

    public List<AcademicClass> getClasses() {
        return classes;
    }
}