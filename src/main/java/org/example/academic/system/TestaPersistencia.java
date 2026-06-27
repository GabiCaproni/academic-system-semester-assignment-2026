package org.example.academic.system;

import org.example.academic.system.model.*;
import org.example.academic.system.repository.PersistenceType;
import org.example.academic.system.service.PersistenceService;

public class TestaPersistencia {
    public static void main(String[] args) {
        PersistenceService service = new PersistenceService();

        System.out.println("--- 1. CRIANDO DADOS DE TESTE ---");
        AcademicData data = new AcademicData();
        
       // Substitua as linhas 14 a 18 por esta linha única:
        AcademicClass turma = new AcademicClass("P001", "Programacao Avancada");
        data.getClasses().add(turma);

        // --- TESTANDO JSON ---
        System.out.println("\n--- 2. TESTANDO JSON (SALVAR E CARREGAR) ---");
        service.setPersistenceType(PersistenceType.JSON);
        service.saveData(data); // Cria o ficheiro academic_data.json
        
        AcademicData dadosJson = service.loadData();
        System.out.println("JSON carregado com sucesso! Turmas encontradas: " + dadosJson.getClasses().size());

        // --- TESTANDO XML ---
        System.out.println("\n--- 3. TESTANDO XML (SALVAR E CARREGAR) ---");
        service.setPersistenceType(PersistenceType.XML);
        service.saveData(data); // Cria o ficheiro academic_data.xml
        
        AcademicData dadosXml = service.loadData();
        System.out.println("XML carregado com sucesso! Turmas encontradas: " + dadosXml.getClasses().size());
        
        System.out.println("\n--- TESTE FINALIZADO COM SUCESSO! ---");
    }
}