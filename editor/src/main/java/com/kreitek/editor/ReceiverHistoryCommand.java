package com.kreitek.editor;

public abstract class ReceiverHistoryCommand<R, M> implements Command {
    protected final R receiver;
    protected final History<M> mementos;

    protected ReceiverHistoryCommand(final R receiver, final History<M> mementos) {
        this.receiver = receiver;
        this.mementos = mementos;
    }
}
