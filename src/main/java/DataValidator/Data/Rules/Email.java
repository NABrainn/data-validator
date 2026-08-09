package DataValidator.Data.Rules;

import java.util.Objects;
import java.util.function.Predicate;
import java.util.regex.Pattern;

public record Email(Predicate<CharSequence> validator, String identifier, String message) implements Rule<CharSequence> {
    private static final Pattern EMAIL = Pattern.compile("^[^\\s@]+@[^\\s@]+\\.[^\\s@]+$");
    public static Email of() {
        return of("Field must be a valid email address");
    }
    public static Email of(String message) {
        return new Email(value -> value == null || EMAIL.matcher(value).matches(), "email", Objects.requireNonNull(message));
    }
}
