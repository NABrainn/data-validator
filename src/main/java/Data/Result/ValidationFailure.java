package Data.Result;

import Data.Validation.ValidationErrors;

public record ValidationFailure<T>(ValidationErrors errors) implements ValidationResult<T> {
    public static <T> ValidationFailure<T> of(ValidationErrors errors) {
        return new ValidationFailure<>(errors);
    }
}
