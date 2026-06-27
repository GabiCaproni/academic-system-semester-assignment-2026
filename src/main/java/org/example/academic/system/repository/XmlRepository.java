package org.example.academic.system.repository;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.example.academic.system.model.AcademicData;
import java.io.File;

public class XmlRepository implements AcademicRepository {
    private final XmlMapper xmlMapper = new XmlMapper();
    private final File file = new File("academic_data.xml");

    @Override
    public void save(AcademicData data) throws Exception {
        xmlMapper.writeValue(file, data);
    }

    @Override
    public AcademicData load() throws Exception {
        if (!file.exists()) return new AcademicData();
        return xmlMapper.readValue(file, AcademicData.class);
    }

    @Override
    public PersistenceType getType() {
        return PersistenceType.XML;
    }
}