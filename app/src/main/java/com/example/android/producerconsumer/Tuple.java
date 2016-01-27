package com.example.android.producerconsumer;

import android.support.annotation.NonNull;

public class Tuple implements Comparable<Tuple> {

    public final String name;

    public final int weight;

    public Tuple(String name, int weight) {
        this.name = name;
        this.weight = weight;
    }

    @Override
    public int compareTo(@NonNull Tuple another) {
        if (weight == another.weight) {
            return 0;
        }
        if (weight < another.weight) {
            return -1;
        } else {
            return 1;
        }
    }

    @Override
    public String toString() {
        return "{ " + name + ", " + weight + " }";
    }

}
