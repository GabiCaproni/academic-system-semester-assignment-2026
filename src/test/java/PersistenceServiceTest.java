import org.example.academic.system.model.AcademicData;
import org.example.academic.system.repository.PersistenceType;
import org.example.academic.system.service.PersistenceService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class PersistenceServiceTest {

    private PersistenceService service;

    @BeforeEach
    void setUp() {
        service = new PersistenceService();
    }

    @Test
    void deveTrocarTipoDePersistencia() {
        service.setPersistenceType(PersistenceType.TXT);
        String report = service.generateConfigurationReport();
        assertTrue(report.contains("TXT"));
    }

    @Test
    void deveGerarRelatorioDeConfiguracao() {
        String report = service.generateConfigurationReport();
        assertNotNull(report);
        assertTrue(report.contains("Tipo Atual"));
    }

    @Test
    void deveSalvarDadosComRepositorioAtual() {
        AcademicData data = new AcademicData();
        // Não deve lançar exceção ao salvar dados vazios
        assertDoesNotThrow(() -> service.saveData(data));
    }

    @Test
    void deveCarregarDadosComSucesso() {
        // Ao instanciar, o JSON deve carregar sem erros (mesmo que arquivo não exista, retorna AcademicData novo)
        assertDoesNotThrow(() -> {
            AcademicData loadedData = service.loadData();
            assertNotNull(loadedData);
        });
    }
}
