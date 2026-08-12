package DataValidator.Data.Core;

import DataValidator.Data.Rules.Rule;

import java.util.List;
import java.util.Map;

public interface Data<T extends Operation> {
    Map<String, List<Rule<?>>> rules();
    T mapToOperation();
}
