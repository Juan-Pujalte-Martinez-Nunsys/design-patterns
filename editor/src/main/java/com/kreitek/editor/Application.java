package com.kreitek.editor;

public class Application {

    public static void main(String[] args) {
        final var editorFactory = new EditorFactory();
        final var editor = editorFactory.getConsoleEditor();
        editor.run();
    }

}
