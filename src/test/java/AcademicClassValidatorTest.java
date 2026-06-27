import org.example.academic.system.exception.InvalidAcademicClassException;
import org.example.academic.system.exception.InvalidAssessmentException;
import org.example.academic.system.model.AcademicClass;
import org.example.academic.system.model.Exam;
import org.example.academic.system.validation.AcademicClassValidator;
import org.example.academic.system.validation.AssessmentValidator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * TUS-2371 - Validate academic domain objects using Jakarta Bean Validation
 * TUS-2385 - Test academic domain validation
 */
public class AcademicClassValidatorTest {

    private AcademicClassValidator classValidator;
    private AssessmentValidator assessmentValidator;

    @BeforeEach
    void setUp() {
        classValidator = new AcademicClassValidator();
        assessmentValidator = new AssessmentValidator();
    }

    // --- AcademicClass ---

    @Test
    void validClassMustPassValidation() {
        AcademicClass ac = new AcademicClass("POO001", "Programação OO");
        assertDoesNotThrow(() -> classValidator.validate(ac));
    }

    @Test
    void classWithBlankCodeMustFailValidation() {
        assertThrows(
            InvalidAcademicClassException.class,
            () -> new AcademicClass("", "Programação"));
    }

    @Test
    void classWithBlankTitleMustFailValidation() {
        assertThrows(
            InvalidAcademicClassException.class,
            () -> new AcademicClass("POO001", ""));
    }

    // --- Assessment ---

    @Test
    void validAssessmentMustPassValidation() {
        Exam exam = new Exam("Prova 1", 30.0, 100.0);
        assertDoesNotThrow(() -> assessmentValidator.validate(exam));
    }

    @Test
    void assessmentWithInvalidWeightMustFailValidation() {
        assertThrows(
            InvalidAssessmentException.class,
            () -> new Exam("Prova 1", 0, 100));
    }

    @Test
    void assessmentWithInvalidValueMustFailValidation() {
        assertThrows(
            InvalidAssessmentException.class,
            () -> new Exam("Prova 1", 30, -1));
    }
}