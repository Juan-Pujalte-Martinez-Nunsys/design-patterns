package com.kreitek.editor;

import java.io.InputStream;
import java.io.OutputStream;

public abstract class Editor implements Runnable {
    protected final InputStream inputStream;
    protected final OutputStream outputStream;

    public Editor(final InputStream inputStream, final OutputStream outputStream) {
        this.inputStream = inputStream;
        this.outputStream = outputStream;
    }
}
