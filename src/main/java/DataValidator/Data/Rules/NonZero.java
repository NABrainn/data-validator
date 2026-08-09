package DataValidator.Data.Rules;

import java.util.Objects;
import java.util.function.Predicate;

public record NonZero(Predicate<Number> validator, String identifier, String message) implements Rule<Number> {
    public static NonZero of() {
        return of("Field cannot be zero");
    }
    public static NonZero of(String message) {
        return new NonZero(value -> value == null || value.doubleValue() != 0, "non_zero", Objects.requireNonNull(message));
    }
}
