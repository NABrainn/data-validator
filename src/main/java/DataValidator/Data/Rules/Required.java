package DataValidator.Data.Rules;

import java.util.function.Predicate;

public record Required(Predicate<String> validator, String identifier, String message) implements Rule<String> {
    public static Required of(){
        return new Required(input -> input != null && !input.trim().isBlank(), "required", "Field value is required");
    }

    public static Required of(String message){
        return new Required(input -> input != null && !input.trim().isBlank(), "required", message);
    }
}
