package com.github.nprindle.refract.data;

import com.github.nprindle.refract.d17n.A1;
import com.github.nprindle.refract.d17n.K1;
import com.github.nprindle.refract.classes.Functor;

import java.util.function.Function;

public final class Const<A, B> implements A1<Const.Mu<A>, B> {
    public static final class Mu<A> implements K1 {}

    public static <A, B> Const<A, B> unbox(final A1<Const.Mu<A>, B> p) {
        return (Const<A, B>) p;
    }

    private final A value;

    public Const(final A value) {
        this.value = value;
    }

    public A value() {
        return this.value;
    }

    public static <A, B> A get(final A1<Const.Mu<A>, B> p) {
        return Const.unbox(p).value();
    }

    public static final class Instances {
        public static <C> Functor<Const.Mu<C>> functor() {
            return new Const.Instances.FunctorI();
        }

        private static class FunctorI<C> implements Functor<Const.Mu<C>> {
            @Override
            public <A, B> A1<Const.Mu<C>, B> map(final Function<? super A, ? extends B> f, final A1<Const.Mu<C>, A> x) {
                return new Const(Const.get(x));
            }
        }
    }
}

