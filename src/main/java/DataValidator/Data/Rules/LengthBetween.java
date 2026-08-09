package DataValidator.Data.Rules;

import java.util.Objects;
import java.util.function.Predicate;

public record LengthBetween(Predicate<CharSequence> validator, String identifier, String message) implements Rule<CharSequence> {
    public static LengthBetween of(int minimum, int maximum) {
        return of(minimum, maximum, "Field length must be between " + minimum + " and " + maximum + " characters");
    }
    public static LengthBetween of(int minimum, int maximum, String message) {
        if (minimum < 0 || maximum < minimum) {
            throw new IllegalArgumentException("invalid length range");
        }
        return new LengthBetween(value -> value == null || (value.length() >= minimum && value.length() <= maximum), "length_between", Objects.requireNonNull(message));
    }
}
