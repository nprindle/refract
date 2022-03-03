package com.github.nprindle.refract.classes;

import com.github.nprindle.refract.d17n.A2;
import com.github.nprindle.refract.d17n.K2;
import com.github.nprindle.refract.data.Either;

import java.util.function.Function;

public interface Cochoice<P extends K2> {
    <A, B, C> A2<P, A, B> unleft(final A2<P, Either<A, C>, Either<B, C>> p);
    <A, B, C> A2<P, A, B> unright(final A2<P, Either<C, A>, Either<C, B>> p);
}