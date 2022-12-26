package org.masil.commons.policy;


import org.masil.commons.condition.Condition;

public class GeneralPolicy<FACTOR> implements Policy<FACTOR> {

    public static <FACTOR> Policy<FACTOR> create(Condition<FACTOR> condition) {
        return new GeneralPolicy<>(condition);
    }

    private final Condition<FACTOR> condition;

    GeneralPolicy(Condition<FACTOR> condition) {
        this.condition = condition;
    }

    @Override
    public Condition<FACTOR> getCondition() {
        return condition;
    }

}
