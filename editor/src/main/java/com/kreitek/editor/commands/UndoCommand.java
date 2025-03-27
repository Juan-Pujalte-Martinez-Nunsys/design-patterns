package com.kreitek.editor.commands;

import com.kreitek.editor.ReceiverHistoryCommand;
import com.kreitek.editor.Document;
import com.kreitek.editor.History;

import java.util.NoSuchElementException;

public class UndoCommand extends ReceiverHistoryCommand<Document, Document.Memento> {
    public UndoCommand(
            final Document document,
            final History<Document.Memento> mementos
    ) {
        super(document, mementos);
    }

    @Override
    public void execute() {
        final Document.Memento memento;
        try {
            memento = mementos.pop();
        } catch (final NoSuchElementException e) {
            return;
        }
        receiver.restore(memento);
    }
}
