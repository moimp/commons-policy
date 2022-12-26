package org.masil.commons.policy;

import org.junit.jupiter.api.Test;
import org.masil.commons.condition.Condition;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class GeneralPolicyTest {

    static final Condition<Integer> EVEN_COND = i -> i % 2 == 0;
    static final Policy<Integer> EVEN_POLICY = GeneralPolicy.create(EVEN_COND);
    public static final int ODD = 1;
    public static final int EVEN = 2;

    @Test
    void when_condition_is_satisfied_return_true() {
        assertThat(EVEN_POLICY.handle(ODD)).isFalse();
        assertThat(EVEN_POLICY.handle(EVEN)).isTrue();
    }

    @Test
    void when_condition_is_satisfied_return_result() {
        assertThat(EVEN_POLICY.handle(ODD, 1)).isEqualTo(null);
        assertThat(EVEN_POLICY.handle(EVEN, 1)).isEqualTo(1);
    }

    @Test
    void when_condition_is_unsatisfied_if_set_default_result_return_default_result() {
        assertThat(EVEN_POLICY.handle(ODD, true, false)).isFalse();
        assertThat(EVEN_POLICY.handle(EVEN, true, false)).isTrue();
    }


    @Test
    void when_condition_is_satisfied_call_satisfiedProcessor() {
        SatisfiedProcessor<Integer, Integer> satisfiedProcessor = t -> t;
        UnSatisfiedProcessor<Integer, Integer> neverCallThisProcessor = (t) -> {throw new IllegalStateException();};

        assertThat(EVEN_POLICY.handle(EVEN, 9, satisfiedProcessor, neverCallThisProcessor)).isEqualTo(9);
    }

    @Test
    void when_condition_is_unsatisfied_call_unsatisfiedProcessor() {

        SatisfiedProcessor<Integer, Integer> neverCallThisProcessor = (t) -> {throw new IllegalStateException();};
        UnSatisfiedProcessor<Integer, Integer> unSatisfiedProcessor = t -> t;

        assertThat(EVEN_POLICY.handle(ODD, 9, neverCallThisProcessor, unSatisfiedProcessor)).isEqualTo(9);
    }

    @Test
    void handle2() {
        assertThatThrownBy(() -> EVEN_POLICY.handle(ODD, 8, t -> 10));
    }

}
