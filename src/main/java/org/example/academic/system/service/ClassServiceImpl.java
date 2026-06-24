package org.example.academic.system.service;

import java.util.List;
import org.example.academic.system.model.AcademicClass;
import org.example.academic.system.repository.AcademicClassRepository;

public class ClassServiceImpl
        implements ClassService {

    private final AcademicClassRepository repository;

    public ClassServiceImpl(
            AcademicClassRepository repository) {

        this.repository = repository;
    }

    @Override
    public void registerClass(
            AcademicClass academicClass) {

        repository.save(
                academicClass);
    }

    @Override
    public List<AcademicClass> getAllClasses() {

        return repository.findAll();
    }
}