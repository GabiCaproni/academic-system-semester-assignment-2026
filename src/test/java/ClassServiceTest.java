import static org.junit.jupiter.api.Assertions.*;

import org.example.academic.system.exception.AcademicSystemException;
import org.example.academic.system.model.AcademicClass;
import org.example.academic.system.repository.AcademicClassRepository;
import org.example.academic.system.service.ClassService;
import org.example.academic.system.service.ClassServiceImpl;
import org.junit.jupiter.api.Test;

public class ClassServiceTest {

    @Test
    void shouldRegisterAcademicClass() {

        AcademicClassRepository repository =
                new AcademicClassRepository();

        ClassService service =
                new ClassServiceImpl(
                        repository);

        AcademicClass academicClass =
                new AcademicClass(
                        "POO001",
                        "Programação Orientada a Objetos");

        service.registerClass(
                academicClass);

        assertEquals(
                1,
                service.getAllClasses().size());
    }

    @Test
    void shouldStoreClassInRepository() {

        AcademicClassRepository repository =
                new AcademicClassRepository();

        ClassService service =
                new ClassServiceImpl(
                        repository);

        AcademicClass academicClass =
                new AcademicClass(
                        "POO001",
                        "Programação Orientada a Objetos");

        service.registerClass(
                academicClass);

        assertTrue(
                repository.findAll()
                        .contains(
                                academicClass));
    }

    @Test
    void shouldThrowExceptionForInvalidClass() {

        assertThrows(
                AcademicSystemException.class,
                () -> new AcademicClass(
                        "",
                        "Programação Orientada a Objetos"));
    }
}