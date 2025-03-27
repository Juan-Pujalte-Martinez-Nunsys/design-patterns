package com.kreitek.editor.editors;

import com.kreitek.editor.Document;
import com.kreitek.editor.printers.DocumentPrinter;
import com.kreitek.editor.Editor;
import com.kreitek.editor.History;
import com.kreitek.editor.commands.CommandFactory;

import java.util.ArrayDeque;
import java.util.ArrayList;

public class EditorFactory {
    public Editor getConsoleEditor() {
        final var document = new Document(new ArrayList<>());
        final var documentPrinter = new DocumentPrinter(document, System.out);
        final var commandFactory = new CommandFactory(document, new History<>(new ArrayDeque<>()));

        return new ConsoleEditor(documentPrinter, commandFactory);
    }
}
