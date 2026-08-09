package DataValidator.Data.Rules;

import java.util.Collection;
import java.util.Objects;
import java.util.function.Predicate;

public record NotEmpty(Predicate<Collection<?>> validator, String identifier, String message) implements Rule<Collection<?>> {
    public static NotEmpty of() {
        return of("Collection cannot be empty");
    }
    public static NotEmpty of(String message) {
        return new NotEmpty(value -> value == null || !value.isEmpty(), "not_empty", Objects.requireNonNull(message));
    }
}
