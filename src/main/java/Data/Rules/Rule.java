package Data.Rules;

import java.util.function.Predicate;

public sealed interface Rule<T> permits Required {
    Predicate<T> validator();
    String message();
}
