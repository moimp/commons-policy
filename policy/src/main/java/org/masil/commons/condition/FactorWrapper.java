package org.masil.commons.condition;

import static org.masil.commons.condition.Conditions.Null;

public class FactorWrapper<FACTOR> {

    public static <FACTOR> FactorWrapper<FACTOR> wrap(FACTOR factor) {
        return new FactorWrapper<>(factor);
    }

    public static <FACTOR> FactorWrapper<FACTOR> factor(FACTOR factor) {
        return wrap(factor);
    }

    private final FACTOR factor;

    private FactorWrapper(FACTOR factor) {
        this.factor = factor;
    }

    public boolean is(Condition<FACTOR> condition) {
        return condition.isSatisfy(factor);
    }

    public boolean isNull() {
        return is(Null());
    }

    public boolean isNot(Condition<FACTOR> condition) {
        return is(condition.not());
    }

    public boolean isNotNull() {
        return isNot(Null());
    }
}
