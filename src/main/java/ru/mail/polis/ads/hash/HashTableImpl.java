package ru.mail.polis.ads.hash;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.*;

public class HashTableImpl<Key, Value> implements HashTable<Key, Value> {

    private static final double LOAD_FACTOR = 0.75;
    private static final int INITIAL_CAPACITY = 29;

    private List<Map<Integer, Pair<Key, Value>>> hashTable = new ArrayList<>(INITIAL_CAPACITY);
    private int currentSize;
    private int tableSize = INITIAL_CAPACITY;


    public HashTableImpl() {
        initArray();
    }

    @Override
    public @Nullable Value get(@NotNull Key key) {
        int hash = key.hashCode();
        Map<Integer, Pair<Key, Value>> link = hashTable.get(hash % tableSize);
        if (link != null) {
            Pair<Key, Value> get = link.get(hash);
            return (get != null) ? get.value : null;
        }
        return null;
    }

    @Override
    public void put(@NotNull Key key, @NotNull Value value) {
        putImpl(key, value, true);
    }

    @Override
    public @Nullable Value remove(@NotNull Key key) {
        int hash = key.hashCode();
        int index = hash % tableSize;
        Map<Integer, Pair<Key, Value>> link = hashTable.get(index);
        if (link == null) {
            return null;
        }
        Pair<Key, Value> deleted = link.remove(hash);
        if (deleted != null) {
            currentSize--;
            return deleted.value;
        }
        return null;
    }

    @Override
    public int size() {
        return currentSize;
    }

    @Override
    public boolean isEmpty() {
        return currentSize == 0;
    }

    private void putImpl(@NotNull Key key, @NotNull Value value, boolean countSize) {
        int hash = key.hashCode();
        int index = hash % tableSize;
        Map<Integer, Pair<Key, Value>> link = hashTable.get(index);
        if (link == null) {
            hashTable.set(index, new HashMap<>());
            hashTable.get(index).put(hash, new Pair<>(key, value));
            if (countSize) {
                currentSize++;
            }
            return;
        }
        Pair<Key, Value> get = link.get(hash);
        if (get != null && get.value.equals(value)) {
            currentSize--;
        }
        link.put(hash, new Pair<>(key, value));
        if (countSize) {
            currentSize++;
        }
        resize();
    }

    private double getCurrentLoadFactor() {
        return (double) currentSize / tableSize;
    }

    private void resize() {
        if (getCurrentLoadFactor() < LOAD_FACTOR) {
            return;
        }
        int oldSize = tableSize;
        tableSize = oldSize * 2;
        List<Map<Integer, Pair<Key, Value>>> oldHash = hashTable;
        hashTable = new ArrayList<>(tableSize);
        initArray();

        for (Map<Integer, Pair<Key, Value>> link : oldHash) {
            if (link == null) {
                continue;
            }
            for (Pair<Key, Value> element : link.values()) {
                putImpl(element.key, element.value, false);
            }
        }
    }

    private void initArray() {
        for (int i = 0; i < tableSize; i++) {
            hashTable.add(null);
        }
    }

    private static class Pair<Key, Value> {
        Key key;
        Value value;

        Pair(Key key, Value value) {
            this.key = key;
            this.value = value;
        }
    }
}
