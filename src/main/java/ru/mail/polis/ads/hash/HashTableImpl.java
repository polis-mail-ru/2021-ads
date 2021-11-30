package ru.mail.polis.ads.hash;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.LinkedList;
import java.util.List;

public class HashTableImpl<Key, Value> implements HashTable<Key, Value> {

    private int size = 0;
    private static final int INITIAL_CAPACITY = 16;
    private int capacity = INITIAL_CAPACITY;
    private static final float LOAD_FACTOR = 0.75f;
    @SuppressWarnings("unchecked")
    private List<HashPair>[] table = new List[INITIAL_CAPACITY];

    public HashTableImpl() {
    }

    @Override
    public @Nullable Value get(@NotNull Key key) {
        List<HashPair> pair = table[hash(key)];
        if (pair == null) {
            return null;
        }
        for (HashPair p : pair) {
            if (p.key.equals(key)) {
                return p.value;
            }
        }
        return null;
    }

    @Override
    public void put(@NotNull Key key, @NotNull Value value) {
        if (size >= capacity * LOAD_FACTOR) {
            resize();
        }
        int hash = hash(key);
        List<HashPair> elements = table[hash];
        if (elements == null) {
            elements = new LinkedList<>();
            table[hash] = elements;
        }
        for (HashPair e : elements) {
            if (e.key.equals(key)) {
                e.value = value;
                return;
            }
        }
        elements.add(new HashPair(key, value));
        size++;
    }

    @Override
    public @Nullable Value remove(@NotNull Key key) {
        List<HashPair> pairs = table[hash(key)];
        if (pairs == null) {
            return null;
        }
        for (int i = 0; i < pairs.size(); i++) {
            HashPair pair = pairs.get(i);
            if (pair.key.equals(key)) {
                Value value = pair.value;
                pairs.remove(i);
                size--;
                return value;
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

    private class HashPair {
        Key key;
        Value value;

        public HashPair(Key key, Value value) {
            this.key = key;
            this.value = value;
        }
    }

    private int hash(Key key) {
        return (key.hashCode() & 0x7fffffff) % capacity;
    }

    private void resize() {
        capacity *= 2;
        @SuppressWarnings("unchecked")
        List<HashPair>[] newTable = new List[capacity];
        for (List<HashPair> pair : table) {
            if (pair != null) {
                for (HashPair p : pair) {
                    putInNewTable(newTable, p);
                }
            }
        }
        table = newTable;
    }

    private void putInNewTable(List<HashPair>[] newTable, HashPair pair) {
        int hash = hash(pair.key);
        List<HashPair> pairs = newTable[hash];
        if (pairs == null) {
            pairs = new LinkedList<>();
            newTable[hash] = pairs;
        }
        pairs.add(pair);
    }
}
