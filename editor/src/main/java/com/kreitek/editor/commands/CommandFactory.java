package com.kreitek.editor.commands;

import com.kreitek.editor.*;

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

    public Command getCommand(String commandLine) throws BadCommandException, ExitException {
        String[] args = commandParser.parse(commandLine);
        return switch (args[0]) {
            case "a" -> createAppendCommand(args[1]);
            case "u" -> createUpdateCommand(args[1], args[2]);
            case "d" -> createDeleteCommand(args[1]);
            case "undo" -> createUndoCommand();
            default -> throw new ExitException();
        };
    }

    private Command createUndoCommand() {
        // TODO create undo command
        return new UndoCommand(document, mementos);
    }

    private Command createDeleteCommand(String lineNumber) {
        int number = Integer.parseInt(lineNumber);
        return new DeleteCommand(document, mementos, number);
    }

    private Command createUpdateCommand(String lineNumber, String line) {
        int number = Integer.parseInt(lineNumber);
        return new UpdateCommand(document, mementos, number, line);
    }

    private Command createAppendCommand(String line) {
        return new AppendCommand(document, mementos, line);
    }

}
