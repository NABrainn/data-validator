package Data.Validation;

import Data.Exception.ValueMissingException;

import java.util.*;

public record ValidationErrors(Map<String, String> errorMap) {
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

    public Optional<String> get(String key) {
        if(key == null) {
            throw new ValueMissingException("Provided key is null");
        }
        var error = errorMap.get(key);
        return Optional.ofNullable(error);
    }

    public boolean filled() {
        return !errorMap.isEmpty();
    }

    public static ValidationErrors of(Map<String, String> map) {
        return new ValidationErrors(map);
    }
}
