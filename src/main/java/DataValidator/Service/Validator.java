package DataValidator.Service;

import DataValidator.Data.Core.Data;
import DataValidator.Data.Exception.DataConfigurationException;
import DataValidator.Data.Exception.ValueMissingException;
import DataValidator.Data.Exception.ValidationException;
import DataValidator.Data.Result.ValidationFailure;
import DataValidator.Data.Result.ValidationResult;
import DataValidator.Data.Result.ValidationSuccess;
import DataValidator.Data.Rules.Rule;
import DataValidator.Data.Validation.ValidationErrors;

import java.util.HashMap;
import java.util.Map;
import java.lang.reflect.InvocationTargetException;

public final class Validator {
    public static <T extends Record & Data> ValidationResult<T> validate(T data) throws ValidationException {
        if (data == null) {
            throw new ValueMissingException("data is null");
        }

        var errorMap = new HashMap<String, Map<String, String>>();
        var fields = data.getClass().getRecordComponents();
        var ruleMap = data.rules();

        for (var field : fields) {
            var fieldName = field.getName();
            var ruleList = ruleMap.get(fieldName);

            if (ruleList == null) {
                throw new DataConfigurationException("Rule for field " + fieldName + " was not found");
            }

            try {
                var accessor = field.getAccessor();
                if (!accessor.trySetAccessible()) {
                    throw new DataConfigurationException("Field " + fieldName + " is not accessible");
                }

                var fieldValue = accessor.invoke(data);
                var invalidRuleMap = new HashMap<String, String>();


                for(var rule : ruleList) {
                    var valid = executeRule(rule, fieldValue);

                    if(!valid) {
                        invalidRuleMap.put(rule.identifier(), rule.message());
                    }
                }

                if(!invalidRuleMap.isEmpty()) {
                    errorMap.put(fieldName, Map.copyOf(invalidRuleMap));
                }
            } catch (IllegalAccessException | InvocationTargetException exception) {
                throw new DataConfigurationException("Could not read field " + fieldName);
            }
        }

        var validationErrors = ValidationErrors.of(errorMap);

        var filled = errorMap
                .entrySet()
                .stream()
                .anyMatch(entry -> !entry.getValue().isEmpty());

        if (filled) {
            return ValidationFailure.of(validationErrors);
        }

        return ValidationSuccess.of(data);
    }

    @SuppressWarnings("unchecked")
    private static <V> boolean executeRule(Rule<V> rule, Object value) {
        return rule.validator().test((V) value);
    }
}
