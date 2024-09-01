package org.osmanacademy.common;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.encoder.PatternLayoutEncoder;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.ConsoleAppender;
import ch.qos.logback.core.FileAppender;
import org.slf4j.LoggerFactory;

public class LogBackConfig {
    public void createLogger(String sourceDestinationAlongWithName) {
        // Create Logger Context
        LoggerContext loggerContext = (LoggerContext) LoggerFactory.getILoggerFactory();
        loggerContext.reset();

        // Create Console Appender
        ConsoleAppender<ILoggingEvent> consoleAppender = new ConsoleAppender<>();
        consoleAppender.setContext(loggerContext);
        consoleAppender.setName("CONSOLE");

        // Create Console Encoder
        PatternLayoutEncoder consoleEncoder = new PatternLayoutEncoder();
        consoleEncoder.setContext(loggerContext);
        consoleEncoder.setPattern("%d{HH:mm:ss.SSS} [%thread] %-5level %class{0} - %msg%n");
        consoleEncoder.start();

        // Attach the encoder to the appender
        consoleAppender.setEncoder(consoleEncoder);
        consoleAppender.start();

        // Create File Appender
        FileAppender<ILoggingEvent> fileAppender = new FileAppender<>();
        fileAppender.setContext(loggerContext);
        fileAppender.setName("FILE");
        fileAppender.setFile(sourceDestinationAlongWithName);

        // Create File Encoder
        PatternLayoutEncoder fileEncoder = new PatternLayoutEncoder();
        fileEncoder.setContext(loggerContext);
        fileEncoder.setPattern("%d{HH:mm:ss.SSS} [%thread] %-5level %class{0} - %msg%n");
        fileEncoder.start();

        // Attach the encoder to the appender
        fileAppender.setEncoder(fileEncoder);
        fileAppender.start();

        // Get root logger
        Logger rootLogger = loggerContext.getLogger(Logger.ROOT_LOGGER_NAME);
        rootLogger.setLevel(Level.INFO);
        rootLogger.addAppender(consoleAppender);
        rootLogger.addAppender(fileAppender);

    }
}
