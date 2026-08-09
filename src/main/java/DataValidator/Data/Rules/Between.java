package DataValidator.Data.Rules;

import java.util.Objects;
import java.util.function.Predicate;

public record Between<T extends Comparable<? super T>>(Predicate<T> validator, String identifier, String message) implements Rule<T> {
    public static <T extends Comparable<? super T>> Between<T> of(T minimum, T maximum) {
        return of(minimum, maximum, "Field must be between " + minimum + " and " + maximum);
    }
    public static <T extends Comparable<? super T>> Between<T> of(T minimum, T maximum, String message) {
        var lower = Objects.requireNonNull(minimum); var upper = Objects.requireNonNull(maximum);
        if (lower.compareTo(upper) > 0) {
            throw new IllegalArgumentException("minimum cannot exceed maximum");
        }
        return new Between<>(value -> value == null || (value.compareTo(lower) >= 0 && value.compareTo(upper) <= 0), "between", Objects.requireNonNull(message));
    }
}
