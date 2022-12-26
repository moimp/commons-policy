package org.masil.commons.condition;

import org.junit.jupiter.api.Test;

public class AndTest {


    static final Condition<Integer> even = i -> i % 2 == 0;
    static final Condition<Integer> equalsTo4 = i -> i == 4;

    @Test
    void name() {
        boolean aa = even.and(equalsTo4).isSatisfy(4);
    }
}
