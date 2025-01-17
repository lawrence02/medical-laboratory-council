package com.medical.lab.council.domain;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

public class SurvivorTestSamples {

    private static final Random random = new Random();
    private static final AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    public static Survivor getSurvivorSample1() {
        return new Survivor()
            .id(1L)
            .registrationNumber("registrationNumber1")
            .surname("surname1")
            .forenames("forenames1")
            .previousSurname("previousSurname1");
    }

    public static Survivor getSurvivorSample2() {
        return new Survivor()
            .id(2L)
            .registrationNumber("registrationNumber2")
            .surname("surname2")
            .forenames("forenames2")
            .previousSurname("previousSurname2");
    }

    public static Survivor getSurvivorRandomSampleGenerator() {
        return new Survivor()
            .id(longCount.incrementAndGet())
            .registrationNumber(UUID.randomUUID().toString())
            .surname(UUID.randomUUID().toString())
            .forenames(UUID.randomUUID().toString())
            .previousSurname(UUID.randomUUID().toString());
    }
}
