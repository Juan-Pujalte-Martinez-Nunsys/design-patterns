package com.kreitek.editor.commands;

import com.kreitek.editor.Command;
import com.kreitek.editor.Document;
import com.kreitek.editor.History;
import com.kreitek.editor.exceptions.BadCommandException;
import com.kreitek.editor.exceptions.ExitException;

public class CommandFactory {
    private static final CommandParser commandParser = new CommandParser();
    private final Document document;
    private final History<Document.Memento> mementos;

    public CommandFactory(
            final Document document,
            final History<Document.Memento> mementos
    ) {
        this.document = document;
        this.mementos = mementos;
    }

    public Command getCommand(final String commandLine) throws BadCommandException, ExitException {
        final var args = commandParser.parse(commandLine);
        return switch (args[0]) {
            case "a" -> createAppendCommand(args[1]);
            case "u" -> createUpdateCommand(args[1], args[2]);
            case "d" -> createDeleteCommand(args[1]);
            case "undo" -> createUndoCommand();
            default -> throw new ExitException();
        };
    }

    private Command createUndoCommand() {
        return new UndoCommand(document, mementos);
    }

    private Command createDeleteCommand(final String lineNumber) {
        final var number = Integer.parseInt(lineNumber);
        return new DeleteCommand(document, mementos, number);
    }

    private Command createUpdateCommand(final String lineNumber, final String line) {
        final var number = Integer.parseInt(lineNumber);
        return new UpdateCommand(document, mementos, number, line);
    }

    private Command createAppendCommand(String line) {
        return new AppendCommand(document, mementos, line);
    }
}
