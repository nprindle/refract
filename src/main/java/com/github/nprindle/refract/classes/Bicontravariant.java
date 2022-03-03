package com.github.nprindle.refract.classes;

import com.github.nprindle.refract.d17n.A2;
import com.github.nprindle.refract.d17n.K2;

import java.util.function.Function;

public interface Bicontravariant<P extends K2> {
    <A, B, C, D> A2<P, C, D> cimap(final Function<? extends C, ? super A> f, final Function<? extends D, ? super B> g, final A2<P, A, B> x);

    default <A, B, C> A2<P, C, B> cfirst(final Function<? extends C, ? super A> f, final A2<P, A, B> x) {
        return cimap(f, y -> y, x);
    }

    default <A, B, D> A2<P, A, D> csecond(final Function<? extends D, ? super B> g, final A2<P, A, B> x) {
        return cimap(y -> y, g, x);
    }
}