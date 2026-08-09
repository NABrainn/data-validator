package DataValidator.Data.Rules;

import java.net.URI;
import java.util.Objects;
import java.util.function.Predicate;

public record Url(Predicate<CharSequence> validator, String identifier, String message) implements Rule<CharSequence> {

    public static Url of() {
        return of("Field must be a valid HTTP(S) URL");
    }

    public static Url of(String message) {
        return new Url(Url::isValidHttpUrl, "url", Objects.requireNonNull(message));
    }

    private static boolean isValidHttpUrl(CharSequence value) {
        if (value == null) {
            return true;
        }
        try {
            var uri = URI.create(value.toString());
            return ("http".equalsIgnoreCase(uri.getScheme()) || "https".equalsIgnoreCase(uri.getScheme())) && uri.getHost() != null;
        }
        catch (IllegalArgumentException ignored) {
            return false;
        }
    }
}
