package lesson24.touragency.common.solution.utils;

import java.util.Optional;
import java.util.OptionalInt;

public final class OptionalUtils {
    private OptionalUtils() {

    }

    public static Optional<Integer> valueOf(OptionalInt optionalInt) {
        return optionalInt.isPresent() ? Optional.of(optionalInt.getAsInt()) : Optional.empty();
    }
}