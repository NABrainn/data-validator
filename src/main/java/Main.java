import DataValidator.Data.Core.Command;
import DataValidator.Data.Exception.ValidationException;
import DataValidator.Data.Result.ValidationFailure;
import DataValidator.Data.Result.ValidationSuccess;
import DataValidator.Data.Rules.Required;
import DataValidator.Data.Rules.Rule;
import DataValidator.Data.Core.Data;
import DataValidator.Service.Validator;

record UserRequest(String firstName, String lastName) implements Data<User>{
    @Override
    public Map<String, List<Rule<?>>> rules() {
        return Map.of(
                "firstName", List.of(Required.of()),
                "lastName", List.of(Required.of())
        );
    }

    @Override
    public User mapToOperation() {
        return User.of(firstName, lastName);
    }

    public static UserRequest of(String firstName, String lastName) {
        return new UserRequest(firstName, lastName);
    }
}

record User(String firstName, String lastName) implements Command {

    public static User of(String firstName, String lastName) {
        return new User(firstName, lastName);
    }
}

void main() {
    try {
        var dirtyRequest = UserRequest.of("ddd", "lastName");
        var result = Validator.validate(dirtyRequest);
        switch (result) {
            case ValidationFailure(var errors) -> {
                for(var error : errors.getMessages("firstName")) {
                    IO.println(error);
                }
            }
            case ValidationSuccess(var cleanRequest) -> {
                var command = cleanRequest.mapToOperation();
            }
        }
    } catch (ValidationException e) {
        throw new RuntimeException(e);
    }
}