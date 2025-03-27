package com.kreitek.editor.printers;

import com.kreitek.editor.ANSIColors;
import com.kreitek.editor.Document;
import com.kreitek.editor.Printer;

import java.io.OutputStream;
import java.io.PrintStream;

public class DocumentPrinter implements Printer {
    private final Document document;
    private final PrintStream printStream;

    public DocumentPrinter(final Document document, final OutputStream outputStream) {
        this.document = document;
        this.printStream = new PrintStream(outputStream);
    }

    @Override
    public void print() {
        final var lines = document.getDocumentLines();
        setColor(ANSIColors.YELLOW);
        printStream.println("START DOCUMENT ==>");
        for (int i = 0; i < lines.size(); i++) {
            printStream.printf("[%d] %s", i, lines.get(i));
        }
        printStream.println("<== END DOCUMENT");
        setColor(ANSIColors.RESET);
    }

    private void setColor(final ANSIColors color) {
        printStream.print(color.get());
    }
}
