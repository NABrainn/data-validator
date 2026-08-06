package Service;

import Data.Core.Data;
import Data.Exception.DataConfigurationException;
import Data.Exception.ValueMissingException;
import Data.Exception.ValidationException;
import Data.Result.ValidationFailure;
import Data.Result.ValidationResult;
import Data.Result.ValidationSuccess;
import Data.Rules.Rule;
import Data.Validation.ValidationError;
import Data.Validation.ValidationErrors;

import java.util.ArrayList;

public final class Validator {
    public static <T extends Data> ValidationResult<T> validate(T data) throws ValidationException {
        if (data == null) {
            throw new ValueMissingException("data is null");
        }

        var validations = new ArrayList<ValidationError>();
        var fields = data.getClass().getDeclaredFields();
        var rules = data.rules();

        for (var field : fields) {
            var fieldName = field.getName();
            var rule = rules.get(fieldName);

            if (rule == null) {
                throw new DataConfigurationException("Rule for field " + fieldName + " was not found");
            }

            try {
                if (!field.trySetAccessible()) {
                    throw new DataConfigurationException("Field " + fieldName + " is not accessible");
                }

                var value = field.get(data);
                var valid = executeRule(rule, value);

                if(!valid) {
                    validations.add(ValidationError.of(fieldName, rule.message()));
                }
            } catch (IllegalAccessException exception) {
                throw new DataConfigurationException("Could not read field " + fieldName);
            }
        }

        var validationErrors = ValidationErrors.of(validations);

        if (validationErrors.hasErrors()) {
            return ValidationFailure.of(validationErrors);
        }

        return ValidationSuccess.of(data);
    }

    @SuppressWarnings("unchecked")
    private static <V> boolean executeRule(Rule<V> rule, Object value) {
        return rule.validator().test((V) value);
    }
}
