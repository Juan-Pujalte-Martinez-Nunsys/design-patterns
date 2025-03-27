package com.kreitek.editor;

public abstract class Command<R, M> {
    protected final R receiver;
    protected final History<M> mementos;

    protected Command(final R receiver, final History<M> mementos) {
        this.receiver = receiver;
        this.mementos = mementos;
    }

    public abstract void execute();
}
