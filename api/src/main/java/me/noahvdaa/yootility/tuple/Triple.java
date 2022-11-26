package me.noahvdaa.yootility.tuple;

import org.jetbrains.annotations.Nullable;

public interface Triple<A, B, C> {

    @Nullable
    A first();

    @Nullable
    B second();

    @Nullable
    C third();

    static <A, B, C> MutableTriple<A, B, C> mutable(@Nullable A first, @Nullable B second, @Nullable C third) {
        return new MutableTriple<>(first, second, third);
    }

    static <A, B, C> ImmutableTriple<A, B, C> immutable(@Nullable A first, @Nullable B second, @Nullable C third) {
        return new ImmutableTriple<>(first, second, third);
    }

}
