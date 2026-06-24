package org.example.academic.system.service;

import org.example.academic.system.exception.AcademicSystemException;
import org.example.academic.system.model.AcademicClass;
import org.example.academic.system.model.Assessment;
import org.example.academic.system.repository.AcademicClassRepository;

public class AssessmentService {

    private final AcademicClassRepository classRepository;

    public AssessmentService(
            AcademicClassRepository classRepository) {

        this.classRepository = classRepository;
    }

    public void registerAssessment(
            AcademicClass academicClass,
            Assessment assessment) {

        if (academicClass == null) {
            throw new AcademicSystemException(
                    "Turma não encontrada.");
        }

        if (assessment == null) {
            throw new AcademicSystemException(
                    "Avaliação inválida.");
        }

        academicClass.addAssessment(
                assessment);
    }
}