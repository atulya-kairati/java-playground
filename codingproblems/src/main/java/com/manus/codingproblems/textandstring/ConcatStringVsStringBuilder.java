package com.manus.codingproblems.textandstring;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Mode;

import java.util.List;


public class ConcatStringVsStringBuilder {

    @Benchmark
//    @OutputTimeUnit(TimeUnit.NANOSECONDS)
    @BenchmarkMode(Mode.AverageTime)
    public void useString() {
        List<String> list = List.of("One", "Two", "Three", "Four");
        String comb = "";

        for (String word : list) {
            comb += word;
        }
    }

    @Benchmark
    public void useStringBuilder() {

        List<String> list = List.of("One", "Two", "Three", "Four");
        StringBuilder comb = new StringBuilder();

        for (String word : list) {
            comb.append(word);
        }
    }

    public static void main(String[] args) throws Exception {
        org.openjdk.jmh.Main.main(args);
    }
}
