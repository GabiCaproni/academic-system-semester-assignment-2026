import org.example.academic.system.logging.ApplicationLogger;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * TUS-2395 - Verify logging infrastructure behavior
 */
public class LoggingInfrastructureTest {

    @Test
    void loggerInstanceCanBeCreatedSuccessfully() {
        assertDoesNotThrow(() -> {
            Class.forName(
                "org.example.academic.system.logging.ApplicationLogger");
        });
    }

    @Test
    void infoLogCanBeWrittenWithoutException() {
        assertDoesNotThrow(() ->
            ApplicationLogger.info("Test info message"));
    }

    @Test
    void warnLogCanBeWrittenWithoutException() {
        assertDoesNotThrow(() ->
            ApplicationLogger.warn("Test warn message"));
    }

    @Test
    void errorLogCanBeWrittenWithoutException() {
        assertDoesNotThrow(() ->
            ApplicationLogger.error("Test error message"));
    }

    @Test
    void debugLogCanBeWrittenWithoutException() {
        assertDoesNotThrow(() ->
            ApplicationLogger.debug("Test debug message"));
    }
}