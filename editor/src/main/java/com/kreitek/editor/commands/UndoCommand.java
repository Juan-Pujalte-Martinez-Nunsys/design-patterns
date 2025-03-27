package com.kreitek.editor.commands;

import com.kreitek.editor.Command;
import com.kreitek.editor.ConsoleEditor;
import com.kreitek.editor.History;

import java.util.ArrayList;
import java.util.NoSuchElementException;

public class UndoCommand implements Command {
    private final History<ConsoleEditor.Memento> mementos;

    public UndoCommand(final History<ConsoleEditor.Memento> mementos) {
        this.mementos = mementos;
    }

    @Override
    public void execute(final ArrayList<String> documentLines) {
        final ConsoleEditor.Memento memento;
        try {
            memento = mementos.pop();
        } catch (final NoSuchElementException e) {
            return;
        }

        documentLines.clear();
        documentLines.addAll(memento.getDocumentLines());
    }
}
