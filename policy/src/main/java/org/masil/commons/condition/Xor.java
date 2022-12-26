package org.masil.commons.condition;

public class Xor<FACTOR> implements Condition<FACTOR>{

    private final Condition<FACTOR> left;
    private final Condition<FACTOR> right;

    public Xor(Condition<FACTOR> left, Condition<FACTOR> right) {
        this.left = left;
        this.right = right;
    }

    @Override
    public boolean isSatisfy(FACTOR factor) {
        return left.isSatisfy(factor) ^ right.isSatisfy(factor);
    }
}
