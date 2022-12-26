package org.masil.commons.condition;

import java.util.function.Function;

public class Equals<EXPECTED_TYPE, FACTOR> extends ObjectCondition<EXPECTED_TYPE, FACTOR> implements Condition<FACTOR> {

    public static <EXPECTED_TYPE> Condition<EXPECTED_TYPE> of(EXPECTED_TYPE expectedValue) {
        return Equals.of(expectedValue, e -> e);
    }

    public static <EXPECTED, FACTOR> Condition<FACTOR> of(EXPECTED expectedValue, Function<FACTOR, EXPECTED> extractor) {
        return new Equals<>(expectedValue, extractor);
    }

    public Equals(EXPECTED_TYPE expectedValue, Function<FACTOR, EXPECTED_TYPE> extractor) {
        super(expectedValue, extractor);
    }

    boolean isSatisfy(EXPECTED_TYPE expectedValue, EXPECTED_TYPE extractedValue) {
        return expectedValue.equals(extractedValue);
    }


}
