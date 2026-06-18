package org.example.academic.system.service;

import java.util.List;
import org.example.academic.system.model.AcademicClass;
import org.example.academic.system.repository.AcademicClassRepository;

public class AcademicClassService {

    private final AcademicClassRepository repository;

    public AcademicClassService(
            AcademicClassRepository repository) {

        this.repository = repository;
    }

    public void registerClass(
            AcademicClass academicClass) {

        repository.save(
                academicClass);
    }

    public List<AcademicClass> getClasses() {

        return repository.findAll();
    }
}