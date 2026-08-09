package DataValidator.Data.Rules;

import java.util.Objects;
import java.util.function.Predicate;

public record Alphabetic(Predicate<CharSequence> validator, String identifier, String message) implements Rule<CharSequence> {
    public static Alphabetic of() {
        return of("Field may contain letters only");
    }
    public static Alphabetic of(String message) {
        return new Alphabetic(value -> value == null || value.toString().matches("\\p{L}+"), "alphabetic", Objects.requireNonNull(message));
    }
}
