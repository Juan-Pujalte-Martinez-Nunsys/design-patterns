package com.kreitek.editor;

import java.util.List;

public class Document implements Originator<Document.Memento> {
    private final List<String> documentLines;

    public Document(final List<String> documentLines) {
        this.documentLines = documentLines;
    }

    public List<String> getDocumentLines() {
        return List.copyOf(documentLines);
    }

    public void append(final String line) {
        documentLines.add(line);
    }

    public void update(final int lineNumber, final String line) {
        if (documentLines.size() > lineNumber) documentLines.set(lineNumber, line);
        else documentLines.add(line);
    }

    public void delete(final int lineNumber) {
        if (lineNumber < documentLines.size()) documentLines.remove(lineNumber);
    }

    @Override
    public Memento save() {
        return new Memento(List.copyOf(documentLines));
    }

    @Override
    public void restore(final Memento memento) {
        documentLines.clear();
        documentLines.addAll(memento.documentLines);
    }

    public static class Memento {
        private final List<String> documentLines;

        private Memento(final List<String> documentLines) {
            this.documentLines = documentLines;
        }
    }
}
