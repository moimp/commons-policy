package org.masil.commons.condition;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.masil.commons.condition.FactorWrapper.factor;

class EqualsTest {
    Equals<Integer, SomeOne> equalsTo10 = new Equals<>(10, SomeOne::getAge);
    Equals<Integer, SomeOne> equalsToNull = new Equals<>(null, SomeOne::getAge);

    public static class SomeOne {
        Integer age;

        public SomeOne(Integer age) {
            this.age = age;
        }

        public Integer getAge() {
            return age;
        }
    }


    @Test
    void simpleFactor() {
        Condition<Integer> sut = Equals.of(1);
        assertThat(sut.isSatisfy(1)).isTrue();
        assertThat(sut.isSatisfy(2)).isFalse();

    }

    @Test
    void objectFactor() {
        assertThat(factor(new SomeOne(10)).is(equalsTo10)).isTrue();
        assertThat(factor(new SomeOne(9)).is(equalsTo10)).isFalse();
        assertThat(factor(new SomeOne(null)).is(equalsToNull)).isTrue();
        assertThat(factor(new SomeOne(null)).is(equalsTo10)).isFalse();
        assertThat(factor(new SomeOne(10)).is(equalsToNull)).isFalse();
    }

}
