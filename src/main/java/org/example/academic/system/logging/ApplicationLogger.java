package org.example.academic.system.logging;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * TUS-2390 - Configure application logging infrastructure
 * Logging centralizado usando SLF4J + Logback.
 */
public class ApplicationLogger {

    private static final Logger logger =
            LoggerFactory.getLogger(ApplicationLogger.class);

    private ApplicationLogger() {
        // utility class - nao instanciar
    }

    public static void info(String message) {
        logger.info(message);
    }

    public static void warn(String message) {
        logger.warn(message);
    }

    public static void error(String message) {
        logger.error(message);
    }

    public static void debug(String message) {
        logger.debug(message);
    }
}