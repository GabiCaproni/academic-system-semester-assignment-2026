package org.example.academic.system.validation;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.example.academic.system.exception.InvalidAcademicClassException;
import org.example.academic.system.model.AcademicClass;

import java.util.Set;
import java.util.stream.Collectors;

/**
 * TUS-2371 - Validate academic domain objects using Jakarta Bean Validation
 * AC5: Regras declaradas com anotações Jakarta Bean Validation
 * AC6: Lógica centralizada em componente reutilizável
 * AC7: Erros convertidos em exceções de domínio
 * AC8: Separado de Main e da camada de interface
 */
public class AcademicClassValidator {

    private final Validator validator;

    public AcademicClassValidator() {
        ValidatorFactory factory =
                Validation.buildDefaultValidatorFactory();
        this.validator = factory.getValidator();
    }

    public void validate(AcademicClass academicClass) {

        Set<ConstraintViolation<AcademicClass>> violations =
                validator.validate(academicClass);

        if (!violations.isEmpty()) {

            String messages = violations.stream()
                    .map(ConstraintViolation::getMessage)
                    .collect(Collectors.joining("; "));

            throw new InvalidAcademicClassException(messages);
        }
    }
}