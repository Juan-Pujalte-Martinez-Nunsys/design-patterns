package com.kreitek.editor.commands;

import com.kreitek.editor.ReceiverHistoryCommand;
import com.kreitek.editor.Document;
import com.kreitek.editor.History;

public class UpdateCommand extends ReceiverHistoryCommand<Document, Document.Memento> {
    private final int lineNumber;
    private final String line;

    public UpdateCommand(
            final Document document,
            final History<Document.Memento> mementos,
            final int lineNumber,
            final String line
    ) {
        super(document, mementos);
        this.lineNumber = lineNumber;
        this.line = line;
    }

    @Override
    public void execute() {
        receiver.update(lineNumber, line);
    }
}
