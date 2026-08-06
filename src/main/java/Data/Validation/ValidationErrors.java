package Data.Validation;

import Data.Exception.ValueMissingException;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Optional;

public record ValidationErrors(List<ValidationError> all) {
    public ValidationErrors {
        if(all == null) {
            throw new ValueMissingException("Error list is null");
        }
        var match = all
                .stream()
                .anyMatch(Objects::isNull);

        if(match) {
            throw new ValueMissingException("List contains objects that are null");
        }
    }

    public Optional<ValidationError> getFirst() {
        try {
            return Optional.of(all.getFirst());
        }
        catch (NoSuchElementException e) {
            return Optional.empty();
        }
    }

    public Optional<ValidationError> getLast() {
        try {
            return Optional.of(all.getLast());
        }
        catch (NoSuchElementException e) {
            return Optional.empty();
        }
    }

    public boolean hasErrors() {
        return !all.isEmpty();
    }

    public static ValidationErrors of(List<ValidationError> validationItems) {
        return new ValidationErrors(validationItems);
    }
}
