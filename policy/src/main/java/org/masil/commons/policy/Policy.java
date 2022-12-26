package org.masil.commons.policy;

import org.masil.commons.condition.Condition;

public interface Policy<FACTOR> extends Condition<FACTOR> {

    Condition<FACTOR> getCondition();

    @Override
    default boolean isSatisfy(FACTOR factor) {
        return getCondition().isSatisfy(factor);
    }

    default  boolean handle(FACTOR factor) {
        return handle(factor, true, false);
    }

    default <RESULT> RESULT handle(FACTOR factor, RESULT result) {
        return handle(factor, result, (t)-> result, (t)-> null);
    }

    default <RESULT> RESULT handle(FACTOR factor, RESULT result, RESULT defaultResult) {
        if (isSatisfy(factor)) {
            return result;
        }
        return defaultResult;
    }

    default <RESULT, TARGET> RESULT handle(FACTOR factor, TARGET target, SatisfiedProcessor<TARGET, RESULT> satisfiedProcessor) {
        return handle(factor, target, satisfiedProcessor, t -> {
            throw new HandleException();
        });
    }

    default <RESULT, TARGET> RESULT handle(FACTOR factor, TARGET target, SatisfiedProcessor<TARGET, RESULT> satisfiedProcessor, UnSatisfiedProcessor<TARGET, RESULT> unSatisfiedProcessor) {
        if (isSatisfy(factor)) {
            return satisfiedProcessor.doProcessBy(target);
        }
        return unSatisfiedProcessor.doProcessBy(target);
    }

}
