package DataValidator.Data.Rules;

import java.util.Collection;
import java.util.Objects;
import java.util.function.Predicate;

public record MaxSize(Predicate<Collection<?>> validator, String identifier, String message) implements Rule<Collection<?>> {
    public static MaxSize of(int maximum) {
        return of(maximum, "Collection must contain at most " + maximum + " elements");
    }
    public static MaxSize of(int maximum, String message) {
        if (maximum < 0) {
            throw new IllegalArgumentException("maximum cannot be negative");
        }
        return new MaxSize(value -> value == null || value.size() <= maximum, "max_size", Objects.requireNonNull(message));
    }
}
