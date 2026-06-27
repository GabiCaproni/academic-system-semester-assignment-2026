package org.example.academic.system.validation;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.example.academic.system.exception.InvalidAssessmentException;
import org.example.academic.system.model.Assessment;

import java.util.Set;
import java.util.stream.Collectors;

/**
 * TUS-2371 - Validate academic domain objects using Jakarta Bean Validation
 * AC5: Regras declaradas com anotações Jakarta Bean Validation
 * AC6: Lógica centralizada em componente reutilizável
 * AC7: Erros convertidos em exceções de domínio
 * AC8: Separado de Main e da camada de interface
 */
public class AssessmentValidator {

    private final Validator validator;

    public AssessmentValidator() {
        ValidatorFactory factory =
                Validation.buildDefaultValidatorFactory();
        this.validator = factory.getValidator();
    }

    public void validate(Assessment assessment) {

        Set<ConstraintViolation<Assessment>> violations =
                validator.validate(assessment);

        if (!violations.isEmpty()) {

            String messages = violations.stream()
                    .map(ConstraintViolation::getMessage)
                    .collect(Collectors.joining("; "));

            throw new InvalidAssessmentException(messages);
        }
    }
}