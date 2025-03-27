package com.kreitek.editor;

import com.kreitek.editor.exceptions.BadCommandException;

public interface Parser {
    String[] parse(final String commandLine) throws BadCommandException;
}
