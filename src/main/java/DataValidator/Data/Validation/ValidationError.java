package DataValidator.Data.Validation;

import DataValidator.Data.Exception.ValueMissingException;

public record ValidationError(String fieldName, String message) {
    public ValidationError {
        if(fieldName == null) {
            throw new ValueMissingException("fieldName or message is null");
        }
        if(message == null) {
            throw new ValueMissingException("message is null");
        }
    }
    public static ValidationError of(String fieldName, String message) {
        return new ValidationError(fieldName, message);
    }
}
