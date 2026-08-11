package DataValidator.Data.Rules;

import java.util.function.Predicate;

public record Required<T>(Predicate<T> validator, String identifier, String message) implements Rule<T> {
    public static <T> Required<T> of(){
        return of("Field value is required");
    }

    public static <T> Required<T> of(String message){
        Predicate<T> isPresent = input -> {
            if (input == null) {
                return false;
            }

            boolean isBlankText = input instanceof CharSequence text && text.toString().trim().isBlank();
            return !isBlankText;
        };

        return new Required<>(isPresent, "required", message);
    }
}
