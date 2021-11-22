package ru.mail.polis.ads.hash;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class HashTableImpl<Key, Value> implements HashTable<Key, Value> {

    private static final int DEFAULT_START_SIZE = 16;

    private List<Pair>[] data = new List[DEFAULT_START_SIZE];
    private int capacity = DEFAULT_START_SIZE;
    private int size = 0;

    private class Pair {
        Key key;
        Value value;

        public Pair(Key key, Value value) {
            this.key = key;
            this.value = value;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Pair pair = (Pair) o;
            return key.equals(pair.key);
        }

        @Override
        public int hashCode() {
            return Objects.hash(key);
        }
    }

    public HashTableImpl() {
    }

    @Override
    public @Nullable Value get(@NotNull Key key) {
        int index = getIndex(key);
        if (data[index] == null) {
            return null;
        }
        for (Pair pair : data[index]) {
            if (pair.key.equals(key)) {
                return pair.value;
            }
        }
        return null;
    }

    @Override
    public void put(@NotNull Key key, @NotNull Value value) {
        int index = getIndex(key);
        if (data[index] == null) {
            data[index] = new LinkedList<Pair>();
            data[index].add(new Pair(key, value));
            size++;
            return;
        }
        for (Pair pair : data[index]) {
            if (pair.key.equals(key)) {
                pair.value = value;
                return;
            }
        }
        data[index].add(new Pair(key, value));
        size++;
    }

    @Override
    public @Nullable Value remove(@NotNull Key key) {
        int index = getIndex(key);
        if (data[index] == null) {
            return null;
        }
        int i = 0;
        Value result = null;
        for (Pair pair : data[index]) {
            if (pair.key.equals(key)) {
                result = pair.value;
                break;
            }
            i++;
        }
        if (result == null) {
            return null;
        }
        data[index].remove(i);
        size--;
        return result;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private int getIndex(@NotNull Key key) {
        return (key.hashCode() & 0x7fffffff) % capacity;
    }
}
