package DataValidator.Data.Rules;

import java.util.Objects;
import java.util.function.Predicate;

public record Numeric(Predicate<CharSequence> validator, String identifier, String message) implements Rule<CharSequence> {
    public static Numeric of() {
        return of("Field may contain digits only");
    }
    public static Numeric of(String message) {
        return new Numeric(value -> value == null || value.toString().matches("\\p{N}+"), "numeric", Objects.requireNonNull(message));
    }
}
