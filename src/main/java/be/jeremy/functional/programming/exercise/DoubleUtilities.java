/*
 * Copyright (c) eHealth
 */
package be.jeremy.functional.programming.exercise;

import be.jeremy.functional.programming.exercise.optional.Option;

import java.util.function.Function;

import static be.jeremy.functional.programming.exercise.List.length;
import static be.jeremy.functional.programming.exercise.optional.Option.none;
import static be.jeremy.functional.programming.exercise.optional.Option.some;

/**
 * @author Jeremy Vanpé (eh056)
 */
public class DoubleUtilities {

    public static Function<List<Double>, Double> sum = l -> l.foldLeft(0.0, x -> y -> x + y);
    public static Function<List<Double>, Option<Double>> mean = l -> l.isEmpty() ? none() : some(sum.apply(l) / length(l));
    public static Function<List<Double>, Option<Double>> variance = l -> mean.apply(l).flatMap(m -> mean.apply(l.map(d -> Math.pow(d - m, 2))));

}
