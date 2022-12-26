package org.masil.commons.condition;

import org.junit.jupiter.api.Test;

import java.util.Objects;

import static org.assertj.core.api.Assertions.assertThat;
import static org.masil.commons.condition.Conditions.*;

class ConditionsTest {

    static final Condition<Integer> even = i -> i % 2 == 0;
    static final Condition<Integer> odd = even.not();
    static final Condition<Integer> greaterThan10 = i -> 10 < i;

    @Test
    void test_alwaysTrue() {
        assertThat(alwaysTrue().isSatisfy(1)).isTrue();
        assertThat(Condition.not(alwaysTrue()).isSatisfy(1)).isFalse();
    }

    @Test
    void test_alwaysFalse() {
        assertThat(alwaysFalse().isSatisfy(1)).isFalse();
        assertThat(Condition.not(alwaysFalse()).isSatisfy(1)).isTrue();
    }

    @Test
    void test_not() {
        assertThat(even.isSatisfy(2)).isTrue();
        assertThat(Condition.not(even).isSatisfy(2)).isFalse();
    }

    @Test
    void test_and() {
        assertThat(Condition.andAll(even, greaterThan10).isSatisfy(12)).isTrue();
    }

    @Test
    void test_or() {
        assertThat(Condition.orAll(even, odd, greaterThan10).isSatisfy(11)).isTrue();
    }

    @Test
    void test_xOr() {
        // what is Xor
        // 0 0 => 0
        // 0 1 => 1
        // 1 0 => 1
        // 1 1 => 0
        assertThat(Condition.xOrAll(even, greaterThan10).isSatisfy(9)).isFalse();
        assertThat(Condition.xOrAll(even, greaterThan10).isSatisfy(10)).isTrue();
        assertThat(Condition.xOrAll(even, greaterThan10).isSatisfy(11)).isTrue();
        assertThat(Condition.xOrAll(even, greaterThan10).isSatisfy(12)).isFalse();

    }

    @Test
    void test_allTrue() {
        assertThat(allTrue(even, greaterThan10).isSatisfy(12)).isTrue();
    }

    @Test
    void test_allFalse() {
        assertThat(allFalse(even, greaterThan10).isSatisfy(9)).isTrue();
    }

    @Test
    void test_anyTrue() {
        assertThat(anyTrue(even, odd, greaterThan10).isSatisfy(11)).isTrue();
    }

    @Test
    void test_equalsTo_primitive_type() {
        assertThat(equalsTo(1).isSatisfy(1)).isTrue();
        assertThat(equalsTo(1).isSatisfy(2)).isFalse();
        assertThat(equalsTo(true).isSatisfy(true)).isTrue();
        assertThat(equalsTo(true).isSatisfy(false)).isFalse();
    }

    static class Foo {
        public static Foo of(int age) {
            return new Foo(age);
        }

        private final int age;

        public Foo(int age) {
            this.age = age;
        }

        public int getAge() {
            return age;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Foo foo = (Foo) o;
            return age == foo.age;
        }

        @Override
        public int hashCode() {
            return Objects.hash(age);
        }
    }
    @Test
    void test_equalsTo_object_type() {
        assertThat(equalsTo(Foo.of(10)).isSatisfy(Foo.of(10))).isTrue();
        assertThat(equalsTo(Foo.of(10)).isSatisfy(Foo.of(9))).isFalse();

        assertThat(equalsTo(10, Foo::getAge).isSatisfy(Foo.of(10))).isTrue();
        assertThat(equalsTo(10, Foo::getAge).isSatisfy(Foo.of(9))).isFalse();
    }
}
