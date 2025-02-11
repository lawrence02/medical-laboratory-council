package com.medical.lab.council.domain;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

public class ResourceTestSamples {

    private static final Random random = new Random();
    private static final AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    public static Resource getResourceSample1() {
        return new Resource().id(1L).quantity("quantity1");
    }

    public static Resource getResourceSample2() {
        return new Resource().id(2L).quantity("quantity2");
    }

    public static Resource getResourceRandomSampleGenerator() {
        return new Resource().id(longCount.incrementAndGet()).quantity(UUID.randomUUID().toString());
    }
}
