package DataValidator.Data.Rules;

import java.util.function.Predicate;

public interface Rule<T> {
    Predicate<T> validator();
    String identifier();
    String message();
}
