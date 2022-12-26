package org.masil.commons.policy;

@FunctionalInterface
public interface UnSatisfiedProcessor<TARGET, RESULT> {

    RESULT doProcessBy(TARGET TARGET);
}
