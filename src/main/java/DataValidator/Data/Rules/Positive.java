package DataValidator.Data.Rules;

import java.util.Objects;
import java.util.function.Predicate;

public record Positive(Predicate<Number> validator, String identifier, String message) implements Rule<Number> {
    public static Positive of() {
        return of("Field must be positive");
    }
    public static Positive of(String message) {
        return new Positive(value -> value == null || value.doubleValue() > 0, "positive", Objects.requireNonNull(message));
    }
}
