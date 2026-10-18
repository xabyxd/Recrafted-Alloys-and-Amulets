package net.xabyxd.recrafted.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import net.xabyxd.recrafted.Recrafted;

/**
 * Logging utility for the Recrafted mod.
 * <p>
 * Wraps a Log4j {@link Logger} named after the mod id, so every message
 * shows up in the console and log files under the {@code recrafted} tag.
 * Messages use Log4j's {@code {}} placeholder syntax:
 *
 * <pre>{@code
 * LogHelper.info("Registered {} with id {}", name, id);
 * }</pre>
 */
public final class LogHelper {

    private static final Logger LOGGER = LogManager.getLogger(Recrafted.MODID);

    /** Prevents instantiation of this utility class. */
    private LogHelper() {}

    /**
     * Logs a message at {@code INFO} level.
     *
     * @param message the message to log
     */
    public static void info(String message) {
        LOGGER.info(message);
    }

    /**
     * Logs a formatted message at {@code INFO} level.
     *
     * @param message the message, with optional {@code {}} placeholders
     * @param params  the values that replace the placeholders, in order
     */
    public static void info(String message, Object... params) {
        LOGGER.info(message, params);
    }

    /**
     * Logs a message at {@code WARN} level.
     *
     * @param message the message to log
     */
    public static void warn(String message) {
        LOGGER.warn(message);
    }

    /**
     * Logs a message at {@code WARN} level together with an exception.
     *
     * @param message   the message to log
     * @param throwable the exception whose stack trace is logged
     */
    public static void warn(String message, Throwable throwable) {
        LOGGER.warn(message, throwable);
    }

    /**
     * Logs a formatted message at {@code WARN} level.
     *
     * @param message the message, with optional {@code {}} placeholders
     * @param params  the values that replace the placeholders, in order
     */
    public static void warn(String message, Object... params) {
        LOGGER.warn(message, params);
    }

    /**
     * Logs a message at {@code ERROR} level.
     *
     * @param message the message to log
     */
    public static void error(String message) {
        LOGGER.error(message);
    }

    /**
     * Logs a message at {@code ERROR} level together with an exception.
     *
     * @param message   the message to log
     * @param throwable the exception whose stack trace is logged
     */
    public static void error(String message, Throwable throwable) {
        LOGGER.error(message, throwable);
    }

    /**
     * Logs a formatted message at {@code ERROR} level.
     *
     * @param message the message, with optional {@code {}} placeholders
     * @param params  the values that replace the placeholders, in order
     */
    public static void error(String message, Object... params) {
        LOGGER.error(message, params);
    }

    /**
     * Logs a message at {@code DEBUG} level.
     * <p>
     * In Minecraft 1.7.10 these messages only appear in
     * {@code logs/debug.log}, not in the console.
     *
     * @param message the message to log
     */
    public static void debug(String message) {
        LOGGER.debug(message);
    }

    /**
     * Logs a formatted message at {@code DEBUG} level.
     * <p>
     * In Minecraft 1.7.10 these messages only appear in
     * {@code logs/debug.log}, not in the console.
     *
     * @param message the message, with optional {@code {}} placeholders
     * @param params  the values that replace the placeholders, in order
     */
    public static void debug(String message, Object... params) {
        LOGGER.debug(message, params);
    }
}