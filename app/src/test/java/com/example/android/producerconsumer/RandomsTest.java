package com.example.android.producerconsumer;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(JUnit4.class)
public class RandomsTest {

    @Test
    public void getRandomString() {
        assertThat(Randoms.getString(4).length()).isEqualTo(4);
    }

    @Test
    public void getRandomInteger() {
        assertThat(Randoms.getInteger(1, 10)).isBetween(1, 10);
    }

}