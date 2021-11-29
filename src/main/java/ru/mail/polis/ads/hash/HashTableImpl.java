package ru.mail.polis.ads.hash;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.LinkedList;
import java.util.List;

public class HashTableImpl<Key, Value> implements HashTable<Key, Value> {
    private static final double LOAD_FACTOR = 0.75;
    private static final int INIT_CAPACITY = 16;

    private class Pair {
        Key key;
        Value value;

        Pair(Key key, Value value) {
            this.key = key;
            this.value = value;
        }
    }

    private int capacity;
    private List<Pair>[] buckets;
    private int size;

    public HashTableImpl() {
        this.capacity = INIT_CAPACITY;
        this.buckets = new LinkedList[capacity];
    }

    @Override
    public @Nullable Value get(@NotNull Key key) {
        final List<Pair> pairs = buckets[getIndex(key)];
        if (pairs == null) {
            return null;
        }
        Pair tmp = null;
        for (Pair pair : pairs) {
            if (key.equals(pair.key)) {
                tmp = pair;
            }
        }
        return tmp == null ? null : tmp.value;
    }

    @Override
    public void put(@NotNull Key key, @NotNull Value value) {
        int index = getIndex(key);
        List<Pair> pairs = buckets[index];
        if (pairs == null) {
            pairs = new LinkedList<>();
            buckets[index] = pairs;
        }
        if (!containsKey(key)) {
            pairs.add(new Pair(key, value));
            size++;
            if ((double) size / capacity > LOAD_FACTOR) {
                resize();
            }
            return;
        }
        for (Pair pair : pairs) {
            if (key.equals(pair.key)) {
                pair.value = value;
                return;
            }
        }
    }

    @Override
    public @Nullable Value remove(@NotNull Key key) {
        int index = getIndex(key);
        if (buckets[index] == null) {
            return null;
        }
        Pair tmp = null;
        for (Pair pair : buckets[index]) {
            if (key.equals(pair.key)) {
                tmp = pair;
                buckets[index].remove(pair);
                size--;
                break;
            }
        }
        return tmp == null ? null : tmp.value;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private int getIndex(Key key) {
        return (key.hashCode() & 0x7fffffff) % capacity;
    }

    private void resize() {
        List<Pair>[] tmpArray = buckets;
        capacity *= 2;
        size = 0;
        buckets = new LinkedList[capacity];
        for (int i = 0; i < tmpArray.length; i++) {
            List<Pair> bucket = tmpArray[i];
            if (bucket != null) {
                for (Pair pair : bucket) {
                    put(pair.key, pair.value);
                }
            }
        }
    }
}
