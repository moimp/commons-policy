package org.masil.commons.condition;

import static org.masil.commons.condition.Conditions.allTrue;
import static org.masil.commons.condition.Conditions.anyTrue;

@FunctionalInterface
public interface Condition<FACTOR> {

    static <FACTOR> Condition<FACTOR> not(Condition<FACTOR> condition) {
        return condition.not();
    }

    @SafeVarargs
    static <FACTOR> Condition<FACTOR> andAll(Condition<FACTOR> condition, Condition<FACTOR> ... conditions) {
        return allTrue(condition, conditions);
    }

    @SafeVarargs
    static <FACTOR> Condition<FACTOR> orAll(Condition<FACTOR> condition, Condition<FACTOR> ... conditions) {
        return anyTrue(condition, conditions);
    }

    static <FACTOR> Condition<FACTOR> xOrAll(Condition<FACTOR> left, Condition<FACTOR> right) {
        return left.xor(right);
    }

    default Condition<FACTOR> and(Condition<FACTOR> right, Condition<FACTOR> ... rights) {
        return andAll(new And<>(this, right), rights);
    }

    default Condition<FACTOR> or(Condition<FACTOR> right, Condition<FACTOR> ... rights) {
        return orAll(new Or<>(this, right), rights);
    }

    default Condition<FACTOR> xor(Condition<FACTOR> right) {
        return new Xor<>(this, right);
    }

    default Condition<FACTOR> not() {
        return new Not<>(this);
    }

    boolean isSatisfy(FACTOR factor);
}
