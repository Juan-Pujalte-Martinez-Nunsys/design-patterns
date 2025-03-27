package com.kreitek.editor;

import com.kreitek.editor.commands.CommandFactory;
import com.kreitek.editor.exceptions.BadCommandException;
import com.kreitek.editor.exceptions.ExitException;

import java.io.PrintStream;
import java.util.Scanner;

public class ConsoleEditor extends Editor {
    private final DocumentPrinter documentPrinter;
    private final CommandFactory commandFactory;
    private final Scanner scanner;
    private final PrintStream printStream;

    public ConsoleEditor(final DocumentPrinter documentPrinter, final CommandFactory commandFactory) {
        super(System.in, System.out);
        this.documentPrinter = documentPrinter;
        this.commandFactory = commandFactory;
        this.scanner = new Scanner(inputStream);
        this.printStream = new PrintStream(outputStream);
    }

    @Override
    public void run() {
        do {
            documentPrinter.printDocument();
            showHelp();

            final var commandLine = waitForNewCommand();

            try {
                Command command = commandFactory.getCommand(commandLine);
                command.execute();
            } catch (final BadCommandException e) {
                printErrorToConsole("Bad command");
            } catch (final ExitException e) {
                System.out.println("Exiting...");
                break;
            }
        } while (true);
    }

    private String waitForNewCommand() {
        printToConsole("Enter a command : ");
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
        printStream.println(color);
    }

    private void printLnToConsole(final String message) {
        printStream.println(message);
    }

    private void printToConsole(final String message) {
        printStream.print(message);
    }
}
