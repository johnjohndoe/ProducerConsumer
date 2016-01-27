package com.example.android.producerconsumer;

import android.support.annotation.NonNull;

public abstract class Producer {

    public static
    @NonNull
    Tuple getTuple() {
        final String name = Randoms.getString(4);
        final int weight = Randoms.getInteger(1, 10);
        return new Tuple(name, weight);
    }

}
