package DataValidator.Data.Result;

public record ValidationSuccess<T>(T value) implements ValidationResult<T> {
    public static <T> ValidationSuccess<T> of(T value) {
        return new ValidationSuccess<>(value);
    }
}