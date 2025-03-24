package com.kreitek.pets.util.logging;

public class LoggerFactory {
    private LoggerFactory() {}

    public static Logger getConsoleLogger() {
        return new OutputStreamLogger(System.out);
    }
}
