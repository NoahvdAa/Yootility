package me.noahvdaa.yootility.tuple;

import org.jetbrains.annotations.Nullable;

public class ImmutablePair<A, B> implements Pair<A, B> {

    private final A first;
    private final B second;

    protected ImmutablePair(@Nullable A first, @Nullable B second) {
        this.first = first;
        this.second = second;
    }

    @Nullable
    public A first() {
        return this.first;
    }

    @Nullable
    public B second() {
        return this.second;
    }

}
