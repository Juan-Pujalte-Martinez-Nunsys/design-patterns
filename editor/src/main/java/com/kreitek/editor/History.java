package com.kreitek.editor;

import java.util.Deque;
import java.util.NoSuchElementException;

public class History<H> {
    private final Deque<H> queue;

    public History(final Deque<H> queue) {
        this.queue = queue;
    }

    public void push(final H e) {
        queue.push(e);
    }

    public H pop() throws NoSuchElementException {
        return queue.pop();
    }
}
