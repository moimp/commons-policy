package org.masil.commons.condition;

public class Not<FACTOR> implements Condition<FACTOR>{

    private final Condition<FACTOR> opland;

    public Not(Condition<FACTOR> opland) {
        this.opland = opland;
    }

    @Override
    public boolean isSatisfy(FACTOR factor) {
        return !opland.isSatisfy(factor);
    }
}
