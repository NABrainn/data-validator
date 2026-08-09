package DataValidator.Data.Rules;

import java.util.Objects;
import java.util.function.Predicate;

public record Negative(Predicate<Number> validator, String identifier, String message) implements Rule<Number> {
    public static Negative of() {
        return of("Field must be negative");
    }
    public static Negative of(String message) {
        return new Negative(value -> value == null || value.doubleValue() < 0, "negative", Objects.requireNonNull(message));
    }
}
