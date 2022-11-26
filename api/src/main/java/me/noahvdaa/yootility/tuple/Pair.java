package me.noahvdaa.yootility.tuple;

import org.jetbrains.annotations.Nullable;

public interface Pair<A, B> {

    @Nullable
    A first();

    @Nullable
    B second();

    static <A, B> MutablePair<A, B> mutable(@Nullable A first, @Nullable B second) {
        return new MutablePair<>(first, second);
    }

    static <A, B> ImmutablePair<A, B> immutable(@Nullable A first, @Nullable B second) {
        return new ImmutablePair<>(first, second);
    }

}
