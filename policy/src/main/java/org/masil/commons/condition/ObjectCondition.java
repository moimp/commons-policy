package org.masil.commons.condition;

import java.util.function.Function;

import static org.masil.commons.condition.FactorWrapper.factor;


public abstract class ObjectCondition<EXPECTED_TYPE, FACTOR> implements Condition<FACTOR> {

    private final EXPECTED_TYPE expectedValue;
    private final Function<FACTOR, EXPECTED_TYPE> extractor;

    public ObjectCondition(EXPECTED_TYPE expectedValue, Function<FACTOR, EXPECTED_TYPE> extractor) {
        this.expectedValue = expectedValue;
        this.extractor = extractor;
    }

    @Override
    public boolean isSatisfy(FACTOR factor) {
        EXPECTED_TYPE extracted = extractor(factor);

        if(factor(expectedValue).isNull() && factor(extracted).isNull()) {
            return true;
        }

        if(factor(expectedValue).isNull() && factor(extracted).isNotNull()) {
            return false;
        }

        if(factor(expectedValue).isNotNull() && factor(extracted).isNull()) {
            return false;
        }

        if(factor(expectedValue).isNotNull() && factor(extracted).isNotNull()) {
            return isSatisfy(expectedValue, extracted);
        }

        throw new IllegalStateException();
    }

    abstract boolean isSatisfy(EXPECTED_TYPE expectedValue, EXPECTED_TYPE extractedValue);

    EXPECTED_TYPE extractor(FACTOR factor) {
        return extractor.apply(factor);
    }
}
