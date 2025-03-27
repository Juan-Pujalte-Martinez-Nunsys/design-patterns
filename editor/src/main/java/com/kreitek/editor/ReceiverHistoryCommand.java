package com.kreitek.editor;

public abstract class ReceiverHistoryCommand<R, H> implements Command {
    protected final R receiver;
    protected final History<H> history;

    protected ReceiverHistoryCommand(final R receiver, final History<H> history) {
        this.receiver = receiver;
        this.history = history;
    }
}
