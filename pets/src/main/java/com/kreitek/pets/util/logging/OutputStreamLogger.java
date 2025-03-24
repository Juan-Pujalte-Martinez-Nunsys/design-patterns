package com.kreitek.pets.util.logging;

import java.io.IOException;
import java.io.OutputStream;

public class OutputStreamLogger implements Logger {
    private static int counter = 0;
    private final OutputStream outputStream;

    public OutputStreamLogger(final OutputStream outputStream) {
        this.outputStream = outputStream;
    }

    @Override
    public void debug(String message) {
        final var level = "debug";
        message = String.format("[%s][%d] - %s%n", level, counter++, message);
        try {
            outputStream.write(message.getBytes());
        } catch (final IOException e) {
            System.out.println("Couldn't write the following message into the output stream: " + message);
        }
    }
}
