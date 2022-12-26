package org.masil.commons.policy;

@FunctionalInterface
public interface SatisfiedProcessor<TARGET, RESULT> {

    RESULT doProcessBy(TARGET TARGET);
}
