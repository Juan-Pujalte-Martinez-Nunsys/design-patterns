package com.kreitek.editor.commands;

import com.kreitek.editor.ReceiverHistoryCommand;
import com.kreitek.editor.Document;
import com.kreitek.editor.History;

public class AppendCommand extends ReceiverHistoryCommand<Document, Document.Memento> {
    private final String text;

    public AppendCommand(
            final Document document,
            final History<Document.Memento> mementos,
            final String text
    ) {
        super(document, mementos);
        this.text = text;
    }

    @Override
    public void execute() {
        receiver.append(text);
    }
}
