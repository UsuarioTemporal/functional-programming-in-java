/*
 * Copyright (c) eHealth
 */
package be.jeremy.functional.programming.exercise;

/**
 * @author Jeremy Vanpé (eh056)
 */
public class Tuple<S, T> {
    public S _1;
    public T _2;

    public Tuple(S s, T t) {
        this._1 = s;
        this._2 = t;
    }

}
