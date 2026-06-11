/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.example.academic.system.repository;

/**
 *
 * @author Gabi Caproni
 */
import java.util.ArrayList;
import java.util.List;
import org.example.academic.system.model.AcademicClass;

public class AcademicClassRepository {

    private List<AcademicClass> classes = new ArrayList<>();

    public void save(AcademicClass academicClass) {
        classes.add(academicClass);
    }

    public List<AcademicClass> findAll() {
        return classes;
    }
}
