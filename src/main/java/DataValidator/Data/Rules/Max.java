package DataValidator.Data.Rules;

import java.util.Objects;
import java.util.function.Predicate;

public record Max<T extends Comparable<? super T>>(Predicate<T> validator, String identifier, String message) implements Rule<T> {
    public static <T extends Comparable<? super T>> Max<T> of(T maximum) {
        return of(maximum, "Field must be at most " + maximum);
    }
    public static <T extends Comparable<? super T>> Max<T> of(T maximum, String message) {
        var bound = Objects.requireNonNull(maximum);
        return new Max<>(value -> value == null || value.compareTo(bound) <= 0, "max", Objects.requireNonNull(message));
    }
}
