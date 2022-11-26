package me.noahvdaa.yootility.tuple;

import org.jetbrains.annotations.Nullable;

public class MutableTriple<A, B, C> implements Triple<A, B, C> {

    private A first;
    private B second;
    private C third;

    protected MutableTriple(@Nullable A first, @Nullable B second, @Nullable C third) {
        this.first = first;
        this.second = second;
        this.third = third;
    }

    @Nullable
    public A first() {
        return this.first;
    }

    public void first(@Nullable A first) {
        this.first = first;
    }

    @Nullable
    public B second() {
        return this.second;
    }

    public void second(@Nullable B second) {
        this.second = second;
    }

    @Nullable
    public C third() {
        return this.third;
    }

    public void third(@Nullable C third) {
        this.third = third;
    }

}
