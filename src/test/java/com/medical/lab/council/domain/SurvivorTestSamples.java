package com.medical.lab.council.domain;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

public class SurvivorTestSamples {

    private static final Random random = new Random();
    private static final AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));
    private static final AtomicInteger intCount = new AtomicInteger(random.nextInt() + (2 * Short.MAX_VALUE));

    public static Survivor getSurvivorSample1() {
        return new Survivor().id(1L).survivorId("survivorId1").name("name1").age(1).latitude("latitude1").longitude("longitude1");
    }

    public static Survivor getSurvivorSample2() {
        return new Survivor().id(2L).survivorId("survivorId2").name("name2").age(2).latitude("latitude2").longitude("longitude2");
    }

    public static Survivor getSurvivorRandomSampleGenerator() {
        return new Survivor()
            .id(longCount.incrementAndGet())
            .survivorId(UUID.randomUUID().toString())
            .name(UUID.randomUUID().toString())
            .age(intCount.incrementAndGet())
            .latitude(UUID.randomUUID().toString())
            .longitude(UUID.randomUUID().toString());
    }
}
