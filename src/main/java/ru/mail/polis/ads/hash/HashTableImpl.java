package ru.mail.polis.ads.hash;

import java.util.ArrayList;
import java.util.Objects;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class HashTableImpl<Key, Value> implements HashTable<Key, Value> {

    private static class Entry<K, V> {

        private final K key;

        private V value;

        private int hashCode; // может быть -1, это значит что элемент удален

        public Entry(K key, V value, int hashCode) {
            this.key = key;
            this.value = value;
            this.hashCode = hashCode;
        }

    }

    private final static int BASE_CAPACITY = 8;

    private final static float BASE_FACTOR = 0.75f;

    private final float loadFactor;

    private ArrayList<Entry<Key, Value>> array;

    private int capacity;

    private int size;

    public HashTableImpl() {
        this(BASE_CAPACITY, BASE_FACTOR);
    }

    public HashTableImpl(int capacity, float loadFactor) {
        this.capacity = capacity;
        this.array = new ArrayList<Entry<Key, Value>>(capacity);
        for (int i = 0; i < this.capacity; ++i) {
            this.array.add(null);
        }
        this.loadFactor = loadFactor;
        this.size = 0;
    }

    private final int hashCode(Key key) {
        return Objects.hashCode(key) & Integer.MAX_VALUE;
    }

    private int getIndex(int hashCode) {
        return getIndexIterative(hashCode, 0);
    }

    private int getIndexIterative(int hashCode, int iteration) {
        return (hashCode + iteration) % this.capacity;
    }

    private void resize() {

        ArrayList<Entry<Key, Value>> temp = this.array;
        this.capacity = this.capacity << 1;
        this.array = new ArrayList<>(this.capacity);
        this.size = 0;

        for (int i = 0; i < this.capacity; ++i) {
            this.array.add(null);
        }
        for (Entry<Key, Value> entry : temp) {
            if (entry != null && entry.hashCode != -1) {
                put(entry.key, entry.value);
            }
        }
    }

    @Override
    public @Nullable Value get(@NotNull Key key) {
        int hashCode = hashCode(key);
        int index = getIndex(hashCode);
        Entry<Key, Value> start = this.array.get(index);

        if (start == null) {
            return null;
        }

        int i = 0;
        Entry<Key, Value> offset = start;

        do {
            if (offset.hashCode == hashCode && key.equals(offset.key)) {
                return offset.value;
            }
            offset = this.array.get(index = getIndexIterative(hashCode, ++i));
        } while (offset != start && offset != null);

        return null;
    }

    @Override
    public void put(@NotNull Key key, @NotNull Value value) {

        if ((this.size + 0.0f) / this.capacity >= this.loadFactor) {
            resize();
        }

        int hashCode = hashCode(key);
        int index = getIndex(hashCode);
        Entry<Key, Value> start = this.array.get(index);

        if (start != null) {

            int i = 0;
            Entry<Key, Value> offset = start;

            do {
                if (offset.hashCode == hashCode && key.equals(offset.key)) {
                    offset.value = value;
                    return;
                }
                offset = this.array.get(index = getIndexIterative(hashCode, ++i));
            } while (offset != start && offset != null);

        }

        ++this.size;
        Entry<Key, Value> entry = new Entry<Key, Value>(key, value, hashCode);
        this.array.set(index, entry);
    }

    @Override
    public @Nullable Value remove(@NotNull Key key) {
        int hashCode = hashCode(key);
        int index = getIndex(hashCode);
        Entry<Key, Value> start = this.array.get(index);

        if (start == null) {
            return null;
        }

        int i = 0;
        Entry<Key, Value> offset = start;

        do {
            if (offset.hashCode == hashCode && key.equals(offset.key)) {
                offset.hashCode = -1;
                --this.size;
                return offset.value;
            }
            offset = this.array.get(index = getIndexIterative(hashCode, ++i));
        } while (offset != start && offset != null);

        return null;
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public boolean isEmpty() {
        return this.size == 0;
    }

}
