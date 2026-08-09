package DataValidator.Data.Result;

public sealed interface ValidationResult<T> permits ValidationSuccess, ValidationFailure {
}
