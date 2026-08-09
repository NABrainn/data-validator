package DataValidator.Data.Rules;

import java.util.Objects;
import java.util.function.Predicate;
import java.util.regex.Pattern;

public record Matches(Predicate<CharSequence> validator, String identifier, String message) implements Rule<CharSequence> {
    public static Matches of(String expression) {
        return of(expression, "Field has an invalid format");
    }
    public static Matches of(String expression, String message) {
        var pattern = Pattern.compile(Objects.requireNonNull(expression));
        return new Matches(value -> value == null || pattern.matcher(value).matches(), "matches", Objects.requireNonNull(message));
    }
}
