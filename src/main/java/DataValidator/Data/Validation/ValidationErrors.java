package DataValidator.Data.Validation;

import DataValidator.Data.Exception.ValueMissingException;

import java.util.Collections;
import java.util.List;
import java.util.Map;

public record ValidationErrors(Map<String, Map<String, String>> errorMap) {
    public ValidationErrors {
        if(errorMap == null) {
            throw new ValueMissingException("Error errorMap is null");
        }
        errorMap = Collections.unmodifiableMap(errorMap);
        var match = errorMap
                .entrySet()
                .stream()
                .anyMatch(entry -> {
                    var key = entry.getKey();
                    var value = entry.getValue();
                    return key == null || value == null;
                });

        if(match) {
            throw new ValueMissingException("Map contains keys or values that are null");
        }
    }

    public List<String> getMessages(String fieldName) {
        if(fieldName == null || fieldName.isEmpty()) {
            throw new ValueMissingException("fieldName is null or empty");
        }

        var ruleMap = errorMap.get(fieldName);

        if(ruleMap == null) {
            return List.of();
        }

        return ruleMap.values().stream().toList();
    }

    public static ValidationErrors of(Map<String, Map<String, String>> map) {
        return new ValidationErrors(map);
    }
}
