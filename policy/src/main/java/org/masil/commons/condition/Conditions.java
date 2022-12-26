package org.masil.commons.condition;


import java.util.Arrays;
import java.util.Objects;
import java.util.function.Function;

public final class Conditions {

    private Conditions() {}

    public static <FACTOR> Condition<FACTOR> Null() {
        return Objects::isNull;
    }

    public static <FACTOR> Condition<FACTOR> alwaysTrue() {
        return Conditions.always(true);
    }

    public static <FACTOR> Condition<FACTOR> alwaysFalse() {
        return Condition.not(alwaysTrue());
    }

    public static <FACTOR> Condition<FACTOR> always(boolean b) {
        return (factor -> b);
    }

    public static <FACTOR> Condition<FACTOR> not(Condition<FACTOR> condition) {
        return condition.not();
    }

    @SafeVarargs
    public static <FACTOR> Condition<FACTOR> and(Condition<FACTOR> left, Condition<FACTOR> ... rights) {
        return Condition.andAll(left, rights);
    }

    @SafeVarargs
    public static <FACTOR> Condition<FACTOR> or(Condition<FACTOR> left, Condition<FACTOR> ... rights) {
        return Condition.orAll(left, rights);
    }

    public static <FACTOR> Condition<FACTOR> xOr(Condition<FACTOR> left, Condition<FACTOR> rights) {
        return Condition.xOrAll(left,rights);
    }

    @SafeVarargs
    public static <FACTOR> Condition<FACTOR> allTrue(Condition<FACTOR> condition, Condition<FACTOR> ... conditions) {
        return Arrays.stream(conditions).reduce(condition, (left, right) -> left.and(right));
    }

    @SafeVarargs
    public static <FACTOR> Condition<FACTOR> allFalse(Condition<FACTOR> condition, Condition<FACTOR> ... conditions) {
        return Arrays.stream(conditions).reduce(condition, (left, right) -> left.not().and(right.not()));
    }

    @SafeVarargs
    public static <FACTOR> Condition<FACTOR> anyTrue(Condition<FACTOR> condition, Condition<FACTOR> ... conditions) {
        return Arrays.stream(conditions).reduce(condition, (left, right) -> left.or(right));
    }

    public static <EXPECTED> Condition<EXPECTED> equalsTo(EXPECTED expected) {
        return Equals.of(expected);
    }

    public static <EXPECTED, FACTOR> Condition<FACTOR> equalsTo(EXPECTED expected, Function<FACTOR, EXPECTED> extractor) {
        return Equals.of(expected, extractor);
    }
}
