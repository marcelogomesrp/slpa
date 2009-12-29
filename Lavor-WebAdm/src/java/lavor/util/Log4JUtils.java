/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package lavor.util;

/**
 *
 * @author marcelo
 */
public class Log4JUtils {

    static org.apache.log4j.Logger log = org.apache.log4j.Logger.getRootLogger();

    public static void LogDebug(String message) {
        log.debug(message);
    }

    public static void LogInfo(String message) {
        log.info(message);
    }

    public static void LogWarn(String message) {
        log.warn(message);
    }

    public static void LogError(String message) {
        log.error(message);
    }

    public static void LogFatal(String message) {
        log.fatal(message);
    }
}

