import Data.Exception.ValidationException;
import Data.Result.ValidationFailure;
import Data.Result.ValidationSuccess;
import Data.Rules.Required;
import Data.Rules.Rule;
import Data.Core.Data;
import Service.Validator;

record UserRequest(String firstName, String lastName) implements Data {
    @Override
    public Map<String, Rule<?>> rules() {
        return Map.of(
                "firstName", Required.of(),
                "lastName", Required.of()
        );
    }

    public static UserRequest of(String firstName, String lastName) {
        return new UserRequest(firstName, lastName);
    }
}

record User(String firstName, String lastName) {

    public static User of(UserRequest userRequest) {
        return new User(userRequest.firstName(), userRequest.lastName());
    }
}

void main() {
    try {
        var dirtyRequest = UserRequest.of("", "lastName");
        var result = Validator.validate(dirtyRequest);
        switch (result) {
            case ValidationFailure(var errors) -> {
                IO.println(errors.toString());
            }
            case ValidationSuccess(var cleanRequest) -> {
                var user = User.of(cleanRequest);
                IO.println(user);
            }
        }
    } catch (ValidationException e) {
        throw new RuntimeException(e);
    }
}