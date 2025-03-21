package com.kreitek.pets.util.logging;

public class ConsoleLogger implements Logger {
    private static int counter = 0;

    @Override
    public void debug(final String message) {
        final var level = "debug";
        System.out.printf("[%s][%d] - %s%n", level, counter++, message);
    }
}
