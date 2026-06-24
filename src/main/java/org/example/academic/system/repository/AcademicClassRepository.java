package org.example.academic.system.repository;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import org.example.academic.system.model.AcademicClass;

public class AcademicClassRepository {

    private List<AcademicClass> classes =
            new ArrayList<>();

    public void save(
            AcademicClass academicClass) {

        classes.add(
                academicClass);
    }

    public List<AcademicClass> findAll() {

        return classes;
    }

    public AcademicClass findByCode(
            String code) {

        for (AcademicClass academicClass : classes) {

            if (academicClass.getCode()
                    .equals(code)) {

                return academicClass;
            }
        }

        return null;
    }

    public void saveToTxt() {

        try (PrintWriter writer =
                new PrintWriter(
                        new FileWriter(
                                "classes.txt"))) {

            for (AcademicClass academicClass
                    : classes) {

                writer.println(
                        academicClass.getCode()
                        + ";"
                        + academicClass.getName());
            }

        } catch (IOException e) {

            throw new RuntimeException(
                    "Erro ao salvar arquivo.",
                    e);
        }
    }
}