package org.example.academic.system.service;

import java.util.List;
import org.example.academic.system.model.AcademicClass;

public interface ClassService {

    void registerClass(
            AcademicClass academicClass);

    List<AcademicClass> getAllClasses();
}