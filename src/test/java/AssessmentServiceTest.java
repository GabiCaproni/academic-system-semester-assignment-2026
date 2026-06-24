import org.example.academic.system.exception.AcademicSystemException;
import org.example.academic.system.model.AcademicClass;
import org.example.academic.system.model.PracticalAssignment;
import org.example.academic.system.repository.AcademicClassRepository;
import org.example.academic.system.service.AssessmentService;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class AssessmentServiceTest {

    @Test
    void shouldRegisterAssessment() {

        AcademicClassRepository repository =
                new AcademicClassRepository();

        AssessmentService service =
                new AssessmentService(
                        repository);

        AcademicClass academicClass =
                new AcademicClass(
                        "POO001",
                        "Programação Orientada a Objetos");

        PracticalAssignment assessment =
                new PracticalAssignment(
                        "TP1",
                        30,
                        100,
                        "Java");

        service.registerAssessment(
                academicClass,
                assessment);

        assertEquals(
                1,
                academicClass.getAssessments().size());
    }

    @Test
    void shouldStoreAssessmentInClass() {

        AcademicClassRepository repository =
                new AcademicClassRepository();

        AssessmentService service =
                new AssessmentService(
                        repository);

        AcademicClass academicClass =
                new AcademicClass(
                        "POO001",
                        "Programação Orientada a Objetos");

        PracticalAssignment assessment =
                new PracticalAssignment(
                        "TP1",
                        30,
                        100,
                        "Java");

        service.registerAssessment(
                academicClass,
                assessment);

        assertTrue(
                academicClass.getAssessments()
                        .contains(assessment));
    }

    @Test
    void shouldThrowExceptionForInvalidAssessment() {

        AcademicClassRepository repository =
                new AcademicClassRepository();

        AssessmentService service =
                new AssessmentService(
                        repository);

        AcademicClass academicClass =
                new AcademicClass(
                        "POO001",
                        "Programação Orientada a Objetos");

        assertThrows(
                AcademicSystemException.class,
                () -> service.registerAssessment(
                        academicClass,
                        null));
    }

    @Test
    void shouldThrowExceptionForNonexistentClass() {

        AcademicClassRepository repository =
                new AcademicClassRepository();

        AssessmentService service =
                new AssessmentService(
                        repository);

        PracticalAssignment assessment =
                new PracticalAssignment(
                        "TP1",
                        30,
                        100,
                        "Java");

        assertThrows(
                AcademicSystemException.class,
                () -> service.registerAssessment(
                        null,
                        assessment));
    }
}