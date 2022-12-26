package org.masil.commons.condition;

public class Or<FACTOR> implements Condition<FACTOR>{

    private final Condition<FACTOR> left;
    private final Condition<FACTOR> right;

    public Or(Condition<FACTOR> left, Condition<FACTOR> right) {
        this.left = left;
        this.right = right;
    }
    @Override
    public boolean isSatisfy(FACTOR factor) {
        return left.isSatisfy(factor) || right.isSatisfy(factor);
    }
}
