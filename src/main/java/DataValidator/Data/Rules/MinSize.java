package DataValidator.Data.Rules;

import java.util.Collection;
import java.util.Objects;
import java.util.function.Predicate;

public record MinSize(Predicate<Collection<?>> validator, String identifier, String message) implements Rule<Collection<?>> {
    public static MinSize of(int minimum) {
        return of(minimum, "Collection must contain at least " + minimum + " elements");
    }
    public static MinSize of(int minimum, String message) {
        if (minimum < 0) {
            throw new IllegalArgumentException("minimum cannot be negative");
        }
        return new MinSize(value -> value == null || value.size() >= minimum, "min_size", Objects.requireNonNull(message));
    }
}
