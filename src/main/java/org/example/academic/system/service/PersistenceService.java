package org.example.academic.system.service;

import org.example.academic.system.repository.*;
import org.example.academic.system.model.AcademicData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.Map;
import java.util.HashMap;

public class PersistenceService {

    private static final Logger logger = LoggerFactory.getLogger(PersistenceService.class);

    private AcademicRepository currentRepository;
    private final Map<PersistenceType, AcademicRepository> repositories = new HashMap<>();

    public PersistenceService() {
        repositories.put(PersistenceType.TXT, new TxtRepository());
        repositories.put(PersistenceType.XML, new XmlRepository());
        repositories.put(PersistenceType.JSON, new JsonRepository());

        setPersistenceType(PersistenceType.JSON);
    }

    public void setPersistenceType(PersistenceType type) {
        this.currentRepository = repositories.get(type);
        logger.info("Tipo de persistência alterado para: {}", type);
    }

    public void saveData(AcademicData data) {
        try {
            logger.info("Iniciando persistência de dados via {}", currentRepository.getType());
            currentRepository.save(data);
            logger.info("Dados salvos com sucesso.");
        } catch (Exception e) {
            logger.error("Erro ao salvar dados via {}: {}", currentRepository.getType(), e.getMessage(), e);
            throw new RuntimeException("Falha na persistência", e);
        }
    }

    public AcademicData loadData() {
        try {
            logger.info("Carregando dados via {}", currentRepository.getType());
            AcademicData data = currentRepository.load();
            logger.info("Dados carregados com sucesso.");
            return data;
        } catch (Exception e) {
            logger.error("Erro ao carregar dados via {}: {}", currentRepository.getType(), e.getMessage(), e);
            throw new RuntimeException("Falha ao carregar dados", e);
        }
    }

    public String generateConfigurationReport() {
        String report = "=== Relatório de Configuração ===\n" +
                "Tipo Atual: " + currentRepository.getType() + "\n" +
                "Tipos Suportados: TXT, XML, JSON\n" +
                "Status: Operante\n" +
                "=================================";
        logger.debug("Relatório de configuração gerado.");
        return report;
    }
}