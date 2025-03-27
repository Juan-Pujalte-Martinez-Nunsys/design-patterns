package com.kreitek.editor.commands;

import com.kreitek.editor.ReceiverHistoryCommand;
import com.kreitek.editor.Document;
import com.kreitek.editor.History;

public class AppendCommand extends ReceiverHistoryCommand<Document, Document.Memento> {
    private final String line;

    public AppendCommand(
            final Document document,
            final History<Document.Memento> mementos,
            final String line
    ) {
        super(document, mementos);
        this.line = line;
    }

    @Override
    public void execute() {
        history.push(receiver.save());
        receiver.append(line);
    }
}
