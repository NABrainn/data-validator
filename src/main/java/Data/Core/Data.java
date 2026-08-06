package Data.Core;

import Data.Rules.Rule;

import java.util.Map;

public interface Data {
    Map<String, Rule<?>> rules();
}
