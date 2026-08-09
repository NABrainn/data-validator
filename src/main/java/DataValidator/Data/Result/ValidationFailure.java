package DataValidator.Data.Result;

import DataValidator.Data.Validation.ValidationErrors;

public record ValidationFailure<T>(ValidationErrors errors) implements ValidationResult<T> {
    public static <T> ValidationFailure<T> of(ValidationErrors errors) {
        return new ValidationFailure<>(errors);
    }
}
