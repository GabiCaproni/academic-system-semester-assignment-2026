package org.example.academic.system.repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.academic.system.model.AcademicData;
import java.io.File;

public class JsonRepository implements AcademicRepository {
    private final ObjectMapper mapper = new ObjectMapper();
    private final File file = new File("academic_data.json");

    @Override
    public void save(AcademicData data) throws Exception {
        mapper.writeValue(file, data);
    }

    @Override
    public AcademicData load() throws Exception {
        if (!file.exists()) return new AcademicData();
        return mapper.readValue(file, AcademicData.class);
    }

    @Override
    public PersistenceType getType() {
        return PersistenceType.JSON;
    }
}