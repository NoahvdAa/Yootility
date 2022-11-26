package me.noahvdaa.yootility.tuple;

import org.jetbrains.annotations.Nullable;

public class MutablePair<A, B> implements Pair<A, B> {

    private A first;
    private B second;

    protected MutablePair(@Nullable A first, @Nullable B second) {
        this.first = first;
        this.second = second;
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

}
