import DataValidator.Data.Core.Operation;
import DataValidator.Data.Exception.ValidationException;
import DataValidator.Data.Result.ValidationFailure;
import DataValidator.Data.Result.ValidationSuccess;
import DataValidator.Data.Rules.Required;
import DataValidator.Data.Rules.Rule;
import DataValidator.Data.Core.Data;
import DataValidator.Service.Validator;

record UserRequest(String firstName, String lastName) implements Data{
    @Override
    public Map<String, List<Rule<?>>> rules() {
        return Map.of(
                "firstName", List.of(Required.of()),
                "lastName", List.of(Required.of())
        );
    }

    @Override
    public Operation mapToOperation() {
        return null;
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
        var dirtyRequest = UserRequest.of("ddd", "lastName");
        var result = Validator.validate(dirtyRequest);
        switch (result) {
            case ValidationFailure(var errors) -> {
                for(var error : errors.getMessages("firstName")) {
                    IO.println(error);
                }
            }
            case ValidationSuccess(var cleanRequest) -> {
                var fname = cleanRequest.firstName();
                IO.println(fname);
            }
        }
    } catch (ValidationException e) {
        throw new RuntimeException(e);
    }
}