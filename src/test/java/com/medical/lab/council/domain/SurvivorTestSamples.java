package com.medical.lab.council.domain;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

public class SurvivorTestSamples {

    private static final Random random = new Random();
    private static final AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    public static Survivor getSurvivorSample1() {
        return new Survivor().id(1L).name("name1");
    }

    public static Survivor getSurvivorSample2() {
        return new Survivor().id(2L).name("name2");
    }

    public static Survivor getSurvivorRandomSampleGenerator() {
        return new Survivor().id(longCount.incrementAndGet()).name(UUID.randomUUID().toString());
    }
}
