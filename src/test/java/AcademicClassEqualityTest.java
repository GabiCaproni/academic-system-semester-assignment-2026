import org.example.academic.system.model.AcademicClass;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class AcademicClassEqualityTest {

    @Test
    void classesWithSameCodeMustBeEqual() {

        AcademicClass c1 =
                new AcademicClass("POO", "Programação");

        AcademicClass c2 =
                new AcademicClass("POO", "Outra matéria");

        assertEquals(c1, c2);
    }

    @Test
    void classesWithSameCodeMustHaveSameHashCode() {

        AcademicClass c1 =
                new AcademicClass("POO", "Programação");

        AcademicClass c2 =
                new AcademicClass("POO", "Outra matéria");

        assertEquals(
                c1.hashCode(),
                c2.hashCode()
        );
    }
}
