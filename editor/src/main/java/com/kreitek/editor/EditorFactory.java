package com.kreitek.editor;

public class EditorFactory {
    public Runnable getConsoleEditor() {
        return new ConsoleEditor();
    }
}
