package com.example.android.producerconsumer;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(JUnit4.class)
public class TupleTest {

    @Test
    public void compareTuplesWithDifferentWeights() {
        Tuple tuple1 = new Tuple("AAAA", 1);
        Tuple tuple2 = new Tuple("BBBB", 10);
        assertThat(tuple1.compareTo(tuple2)).isEqualTo(-1);
        assertThat(tuple2.compareTo(tuple1)).isEqualTo(1);
    }

    @Test
    public void compareTuplesWithSameWeight() {
        Tuple tuple1 = new Tuple("AAAA", 5);
        Tuple tuple2 = new Tuple("BBBB", 5);
        assertThat(tuple1.compareTo(tuple2)).isEqualTo(0);
    }

}