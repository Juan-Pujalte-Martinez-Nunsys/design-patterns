package com.kreitek.editor.commands;

import com.kreitek.editor.Command;
import com.kreitek.editor.Document;
import com.kreitek.editor.History;

public class DeleteCommand extends Command<Document, Document.Memento> {
    private final int lineNumber;

    public DeleteCommand(
            final Document document,
            final History<Document.Memento> mementos,
            final int lineNumber
    ) {
        super(document, mementos);
        this.lineNumber = lineNumber;
    }

    @Override
    public void execute() {
        receiver.delete(lineNumber);
    }
}
