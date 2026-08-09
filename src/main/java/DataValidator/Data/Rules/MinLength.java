package DataValidator.Data.Rules;

import java.util.Objects;
import java.util.function.Predicate;

public record MinLength(Predicate<CharSequence> validator, String identifier, String message) implements Rule<CharSequence> {
    public static MinLength of(int minimum) {
        return of(minimum, "Field must contain at least " + minimum + " characters");
    }
    public static MinLength of(int minimum, String message) {
        if (minimum < 0) {
            throw new IllegalArgumentException("minimum cannot be negative");
        }
        return new MinLength(value -> value == null || value.length() >= minimum, "min_length", Objects.requireNonNull(message));
    }
}
