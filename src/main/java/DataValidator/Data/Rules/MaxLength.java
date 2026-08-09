package DataValidator.Data.Rules;

import java.util.Objects;
import java.util.function.Predicate;

public record MaxLength(Predicate<CharSequence> validator, String identifier, String message) implements Rule<CharSequence> {
    public static MaxLength of(int maximum) {
        return of(maximum, "Field must contain at most " + maximum + " characters");
    }
    public static MaxLength of(int maximum, String message) {
        if (maximum < 0) {
            throw new IllegalArgumentException("maximum cannot be negative");
        }
        return new MaxLength(value -> value == null || value.length() <= maximum, "max_length", Objects.requireNonNull(message));
    }
}
