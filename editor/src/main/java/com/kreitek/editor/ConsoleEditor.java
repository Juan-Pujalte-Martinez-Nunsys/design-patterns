package com.kreitek.editor;

import com.kreitek.editor.commands.CommandFactory;
import com.kreitek.editor.exceptions.BadCommandException;
import com.kreitek.editor.exceptions.ExitException;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ConsoleEditor implements Runnable {
    private final Document document = new Document(new ArrayList<>());
    private final CommandFactory commandFactory = new CommandFactory(document, new History<>(new ArrayDeque<>()));

    @Override
    public void run() {
        boolean exit = false;
        while (!exit) {
            final var commandLine = waitForNewCommand();
            try {
                Command command = commandFactory.getCommand(commandLine);
                command.execute();
            } catch (final BadCommandException e) {
                printErrorToConsole("Bad command");
            } catch (final ExitException e) {
                exit = true;
            }
            showDocumentLines(document.getDocumentLines());
            showHelp();
        }
    }

    private void showDocumentLines(final List<String> document) {
        if (document.size() > 0) {
            setTextColor(ANSIColors.YELLOW.get());
            printLnToConsole("START DOCUMENT ==>");
            for (int index = 0; index < document.size(); index++) {
                final var stringBuilder = "[" +
                        index +
                        "] " +
                        document.get(index);
                printLnToConsole(stringBuilder);
            }
            printLnToConsole("<== END DOCUMENT");
            setTextColor(ANSIColors.RESET.get());
        }
    }

    private String waitForNewCommand() {
        printToConsole("Enter a command : ");
        final var scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    private void showHelp() {
        printLnToConsole("To add new line -> a \"your text\"");
        printLnToConsole("To update line  -> u [line number] \"your text\"");
        printLnToConsole("To delete line  -> d [line number]");
    }

    private void printErrorToConsole(final String message) {
        setTextColor(ANSIColors.RED.get());
        printToConsole(message);
        setTextColor(ANSIColors.RESET.get());
    }

    private void setTextColor(final String color) {
        System.out.println(color);
    }

    private void printLnToConsole(final String message) {
        System.out.println(message);
    }

    private void printToConsole(final String message) {
        System.out.print(message);
    }
}
