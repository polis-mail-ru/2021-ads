package ru.mail.polis.ads.hash;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.tuple.Pair;
import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
@State(Scope.Benchmark)
@Fork(value = 2, jvmArgs = {"-Xms1G", "-Xmx1G"}, warmups = 1)
@Warmup(iterations = 2)
@Measurement(iterations = 3)
public class HashTableJmh {

    @Param({"1000000"})
    private int TEST_DATA_SIZE;

    private List<Pair<String, String>> TEST_DATA;

    public static void main(String[] args) throws RunnerException {

        Options opt = new OptionsBuilder()
            .include(HashTableJmh.class.getSimpleName())
            .forks(1)
            .build();

        new Runner(opt).run();
    }

    @Setup
    public void setup() {
        TEST_DATA = new ArrayList<>();
        for (int i = 0; i < TEST_DATA_SIZE; i++) {
            TEST_DATA.add(Pair.of(RandomStringUtils.random(128), RandomStringUtils.random(128)));
        }
    }

    @Benchmark
    public void benchmarkDefaultHashmap(Blackhole bh) {
        HashMap<String, String> hashMap = new HashMap<>();
        for (int i = 0; i < TEST_DATA_SIZE; i++) {
            hashMap.put(TEST_DATA.get(i).getKey(), TEST_DATA.get(i).getValue());
            bh.consume(hashMap.get(TEST_DATA.get(i).getKey()));
        }
    }

    @Benchmark
    public void benchmarkHashTableImpl(Blackhole bh) {
        HashTable<String, String> hashTable = new HashTableImpl<>();
        for (int i = 0; i < TEST_DATA_SIZE; i++) {
            hashTable.put(TEST_DATA.get(i).getKey(), TEST_DATA.get(i).getValue());
            bh.consume(hashTable.get(TEST_DATA.get(i).getKey()));
        }

    }

}
