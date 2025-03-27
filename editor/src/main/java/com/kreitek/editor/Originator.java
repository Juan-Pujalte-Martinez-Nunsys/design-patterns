package com.kreitek.editor;

public interface Originator<M> {
    M save();

    void restore(final M memento);
}
