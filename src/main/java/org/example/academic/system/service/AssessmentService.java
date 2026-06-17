/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package org.example.academic.system.service;

import org.example.academic.system.model.AcademicClass;
import org.example.academic.system.model.Assessment;
import org.example.academic.system.repository.AcademicClassRepository;

/**
 *
 * @author Gabi Caproni
 */
public class AssessmentService {

    private final AcademicClassRepository classRepository;

    public AssessmentService(AcademicClassRepository classRepository) {
        this.classRepository = classRepository;
    }

    public void registerAssessment(AcademicClass academicClass,Assessment assessment) {
        academicClass.addAssessment(assessment);
        classRepository.save(academicClass);
    }
}
