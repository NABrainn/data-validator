package DataValidator.Data.Rules;

import java.util.Objects;
import java.util.function.Predicate;

public record Alphanumeric(Predicate<CharSequence> validator, String identifier, String message) implements Rule<CharSequence> {
    public static Alphanumeric of() {
        return of("Field may contain letters and digits only");
    }
    public static Alphanumeric of(String message) {
        return new Alphanumeric(value -> value == null || value.toString().matches("[\\p{L}\\p{N}]+"), "alphanumeric", Objects.requireNonNull(message));
    }
}
