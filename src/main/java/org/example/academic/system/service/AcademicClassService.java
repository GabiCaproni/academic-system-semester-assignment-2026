/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.example.academic.system.service;

/**
 *
 * @author Gabi Caproni
 */

import java.util.ArrayList;
import java.util.List;
import org.example.academic.system.model.AcademicClass;
import org.example.academic.system.repository.AcademicClassRepository;


public class AcademicClassService {

    private final AcademicClassRepository repository;

    public AcademicClassService(AcademicClassRepository repository) {
        this.repository = repository;
    }

    public void registerClass(AcademicClass academicClass) {
        repository.save(academicClass);
    }

    public List<AcademicClass> getClasses() {
        return repository.findAll();
    }
}