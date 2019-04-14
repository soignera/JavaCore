package lesson24.touragency.storage;

import java.util.concurrent.atomic.AtomicLong;

public   class AtomicSequenceGenerator {
    private static AtomicLong value = new AtomicLong(1);


    public static long getNextValue() {
        return value.getAndIncrement();
    }
}
