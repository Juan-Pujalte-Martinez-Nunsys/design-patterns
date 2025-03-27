package com.kreitek.editor;

import com.kreitek.editor.exceptions.BadCommandException;

public interface Parser<I, O> {
    O parse(final I input) throws BadCommandException;
}
