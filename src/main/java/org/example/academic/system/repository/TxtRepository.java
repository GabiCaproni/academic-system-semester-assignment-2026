package org.example.academic.system.repository;

import org.example.academic.system.model.AcademicData;
import org.example.academic.system.model.AcademicClass;
import org.example.academic.system.model.Assessment;
import java.io.PrintWriter;
import java.io.FileWriter;

public class TxtRepository implements AcademicRepository {

    @Override
    public void save(AcademicData data) throws Exception {
        try (PrintWriter out = new PrintWriter(new FileWriter("assessments.txt"))) {
            for (AcademicClass turma : data.getClasses()) {
                out.println("TURMA;" + turma.getCode() + ";" + turma.getName());
                // Atenção: Certifique-se de que a classe AcademicClass tem o método getAssessments()
                for (Assessment av : turma.getAssessments()) {
                    out.println("AVALIACAO;" + av.getName() + ";" + av.getWeight() + ";" + av.getValue());
                }
            }
        }
    }

    @Override
    public AcademicData load() throws Exception {
        // TXT neste caso é Write-Only (apenas exportação de relatório)
        return new AcademicData();
    }

    @Override
    public PersistenceType getType() {
        return PersistenceType.TXT;
    }
}