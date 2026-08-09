package DataValidator.Data.Rules;

import java.util.Objects;
import java.util.function.Predicate;

public record Min<T extends Comparable<? super T>>(Predicate<T> validator, String identifier, String message) implements Rule<T> {

    public static <T extends Comparable<? super T>> Min<T> of(T minimum) {
        return of(minimum, "Field must be at least " + minimum);
    }

    public static <T extends Comparable<? super T>> Min<T> of(T minimum, String message) {
        var bound = Objects.requireNonNull(minimum);
        return new Min<>(value -> value == null || value.compareTo(bound) >= 0, "min", Objects.requireNonNull(message));
    }
}
