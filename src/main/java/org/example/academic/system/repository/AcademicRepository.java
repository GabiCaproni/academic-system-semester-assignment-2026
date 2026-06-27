package org.example.academic.system.repository;

import org.example.academic.system.model.AcademicData;

public interface AcademicRepository {
    void save(AcademicData data) throws Exception;
    AcademicData load() throws Exception;
    PersistenceType getType();
}