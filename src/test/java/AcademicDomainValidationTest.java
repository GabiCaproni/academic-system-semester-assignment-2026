import org.example.academic.system.exception.InvalidAcademicClassException;
import org.example.academic.system.exception.InvalidAssessmentException;
import org.example.academic.system.model.AcademicClass;
import org.example.academic.system.model.Exam;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * TUS-2385 - Test academic domain validation
 */
public class AcademicDomainValidationTest {

    @Test
    void validClassMustPassValidation() {
        assertDoesNotThrow(() ->
            new AcademicClass(
                "POO001",
                "Programação Orientada a Objetos"));
    }

    @Test
    void classWithBlankCodeMustFailValidation() {
        assertThrows(
            InvalidAcademicClassException.class,
            () -> new AcademicClass("", "Programação"));
    }

    @Test
    void classWithNullCodeMustFailValidation() {
        assertThrows(
            InvalidAcademicClassException.class,
            () -> new AcademicClass(null, "Programação"));
    }

    @Test
    void classWithBlankTitleMustFailValidation() {
        assertThrows(
            InvalidAcademicClassException.class,
            () -> new AcademicClass("POO001", ""));
    }

    @Test
    void validAssessmentMustPassValidation() {
        assertDoesNotThrow(() ->
            new Exam("Prova 1", 30, 100));
    }

    @Test
    void assessmentWithInvalidValueMustFailValidation() {
        assertThrows(
            InvalidAssessmentException.class,
            () -> new Exam("Prova 1", 30, -5));
    }

    @Test
    void assessmentWithInvalidWeightMustFailValidation() {
        assertThrows(
            InvalidAssessmentException.class,
            () -> new Exam("Prova 1", 0, 100));
    }

    @Test
    void assessmentWithBlankNameMustFailValidation() {
        assertThrows(
            InvalidAssessmentException.class,
            () -> new Exam("", 30, 100));
    }
}