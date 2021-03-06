package com.github.nprindle.refract.classes;

import com.github.nprindle.refract.d17n.A1;
import com.github.nprindle.refract.d17n.K1;
import java.util.function.BiFunction;
import java.util.function.Function;

public interface Applicative<M extends Applicative.Mu, F extends K1> extends Functor<M, F> {
  public static interface Mu extends Functor.Mu {}

  public static <Mu extends Applicative.Mu, F extends K1> Applicative<Mu, F> resolve(
      final A1<Mu, F> p) {
    return (Applicative<Mu, F>) p;
  }

  <A> A1<F, A> pure(final A x);

  // TODO: allow defining lift2 instead of ap
  <A, B> A1<F, B> ap(final A1<F, Function<? super A, ? extends B>> f, final A1<F, A> x);

  default <A, B, C> BiFunction<A1<F, A>, A1<F, B>, A1<F, C>> lift2(
      final BiFunction<? super A, ? super B, ? extends C> f) {
    return (x, y) -> apply2(f, x, y);
  }

  default <A, B, C> A1<F, C> apply2(
      final BiFunction<? super A, ? super B, ? extends C> f, final A1<F, A> x, final A1<F, B> y) {
    return ap(map(x2 -> y2 -> f.apply(x2, y2), x), y);
  }

  default <A, B> A1<F, B> before(final A1<F, A> fx, final A1<F, B> fy) {
    return this.apply2((x, y) -> y, fx, fy);
  }

  default <A, B> A1<F, A> then(final A1<F, A> fx, final A1<F, B> fy) {
    return this.apply2((x, y) -> x, fx, fy);
  }

  default Applicative<M, F> backwards() {
    final Applicative<M, F> base = this;

    return new Applicative<M, F>() {
      @Override
      public <A, B> A1<F, B> map(final Function<? super A, ? extends B> f, final A1<F, A> x) {
        return base.map(f, x);
      }

      @Override
      public <A> A1<F, A> pure(final A x) {
        return base.pure(x);
      }

      @Override
      public <A, B> A1<F, B> ap(final A1<F, Function<? super A, ? extends B>> f, final A1<F, A> x) {
        return base.apply2((x2, f2) -> f2.apply(x2), x, f);
      }

      @Override
      public <A, B, C> A1<F, C> apply2(
          final BiFunction<? super A, ? super B, ? extends C> f,
          final A1<F, A> x,
          final A1<F, B> y) {
        return base.apply2((b, a) -> f.apply(a, b), y, x);
      }

      @Override
      public <A, B, C> BiFunction<A1<F, A>, A1<F, B>, A1<F, C>> lift2(
          final BiFunction<? super A, ? super B, ? extends C> f) {
        return (x, y) -> base.apply2((b, a) -> f.apply(a, b), y, x);
      }
    };
  }
}
