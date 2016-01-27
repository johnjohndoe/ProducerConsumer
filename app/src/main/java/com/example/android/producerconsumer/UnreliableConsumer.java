package com.example.android.producerconsumer;

import android.support.annotation.IntRange;
import android.support.annotation.NonNull;

import java.util.AbstractQueue;

public class UnreliableConsumer implements Consumer {

    protected final AbstractQueue<Tuple> queue;

    protected final int failureProbability;

    protected Tuple tuple;

    public UnreliableConsumer(@NonNull AbstractQueue<Tuple> queue,
                              @IntRange(from = 1, to = 100) int failureProbability) {
        this.queue = queue;
        this.failureProbability = failureProbability;
    }

    @Override
    public boolean consume() throws RuntimeException {
        if (!queue.isEmpty()) {
            tuple = queue.poll();
            if (failsByProbability()) {
                throw new RuntimeException("Consumer simulates unreliable network.");
            }
            return true;
        }
        return false;
    }

    @Override
    public
    @NonNull
    Tuple getTuple() {
        return tuple;
    }

    private boolean failsByProbability() {
        return Randoms.getInteger(0, 100 / failureProbability) == 0;
    }

}
