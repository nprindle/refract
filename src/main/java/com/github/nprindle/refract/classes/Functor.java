package com.github.nprindle.refract.classes;

import com.github.nprindle.refract.d17n.A1;
import com.github.nprindle.refract.d17n.K1;

import java.util.function.Function;

// F is the Mu of the type the Functor is being implemented for
public interface Functor<F extends K1> {
    <A, B> A1<F, B> map(final Function<? super A, ? extends B> f, final A1<F, A> x);
}
