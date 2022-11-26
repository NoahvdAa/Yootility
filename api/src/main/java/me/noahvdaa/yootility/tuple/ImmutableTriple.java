package me.noahvdaa.yootility.tuple;

import org.jetbrains.annotations.Nullable;

public class ImmutableTriple<A, B, C> implements Triple<A, B, C> {

    private final A first;
    private final B second;
    private final C third;

    protected ImmutableTriple(@Nullable A first, @Nullable B second, @Nullable C third) {
        this.first = first;
        this.second = second;
        this.third = third;
    }

    @Nullable
    public A first() {
        return this.first;
    }

    @Nullable
    public B second() {
        return this.second;
    }

    @Nullable
    public C third() {
        return this.third;
    }

}
