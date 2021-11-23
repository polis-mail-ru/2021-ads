package ru.mail.polis.ads.hash;

import java.util.LinkedList;
import java.util.List;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class HashTableImpl<Key, Value> implements HashTable<Key, Value> {

    private static final int INITIAL_SIZE = 16;
    private static final double LOAD_FACTOR = 0.75;

    private List<Pair<Key, Value>>[] buckets = new LinkedList[INITIAL_SIZE];
    private int capacity = INITIAL_SIZE;
    private int size = 0;

    public HashTableImpl() {
    }

    @Override
    public @Nullable Value get(@NotNull Key key) {
        int hash = getHash(key);
        if (buckets[hash] == null) {
            return null;
        }
        for (Pair<Key, Value> pair : buckets[hash]) {
            if (pair.key.equals(key)) {
                return pair.value;
            }
        }
        return null;
    }

    @Override
    public void put(@NotNull Key key, @NotNull Value value) {
        if (isThreshold()) {
            resize();
        }
        int hash = getHash(key);
        if (buckets[hash] == null) {
            buckets[hash] = new LinkedList<>();
        }
        for (Pair<Key, Value> pair : buckets[hash]) {
            if (pair.key.equals(key)) {
                pair.value = value;
                return;
            }
        }
        buckets[hash].add(new Pair<>(key, value));
        size++;
    }

    @Override
    public @Nullable Value remove(@NotNull Key key) {
        int hash = getHash(key);
        if (buckets[hash] == null) {
            return null;
        }
        int indexToRemove = 0;
        for (Pair<Key, Value> pair : buckets[hash]) {
            indexToRemove++;
            if (pair.key.equals(key) && pair.value != null) {
                buckets[hash].remove(indexToRemove - 1);
                size--;
                return pair.value;
            }
        }
        return null;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private int getHash(@NotNull Key key) {
        return key.hashCode() & (capacity - 1);
    }

    private boolean isThreshold() {
        return (double) size + 1 >= LOAD_FACTOR * capacity;
    }

    private void resize() {
        capacity *= 1.6;
        List<Pair<Key, Value>>[] updateSizeList = new List[capacity];
        for (List<Pair<Key, Value>> bucket : buckets) {
            if (bucket != null) {
                for (Pair<Key, Value> pair : bucket) {
                    put(pair.key, pair.value);
                }
            }
            buckets = updateSizeList;
        }
    }

    private static class Pair<Key, Value> {
        Key key;
        Value value;

        public Pair(Key key, Value value) {
            this.key = key;
            this.value = value;
        }
    }
}