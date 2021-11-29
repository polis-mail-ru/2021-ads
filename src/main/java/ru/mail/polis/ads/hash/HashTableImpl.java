package ru.mail.polis.ads.hash;

import java.util.LinkedList;
import java.util.List;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class HashTableImpl<Key, Value> implements HashTable<Key, Value> {

    private final static int INITIAL_CAPACITY = 16;
    private final static double LOAD_FACTOR = 0.75;
    private int currentCapacity = 0;
    private int tableSize = INITIAL_CAPACITY;
    private List<Entry>[] table = new LinkedList[tableSize];

    public HashTableImpl() {
    }

    @Override
    public @Nullable Value get(@NotNull Key key) {
        int index = getHash(key);
        List<Entry> bucket = table[index];
        if (bucket == null) {
            return null;
        }
        Entry resEntry = findEntryByKey(key, bucket);
        return (resEntry != null) ? resEntry.value : null;
    }

    @Override
    public void put(@NotNull Key key, @NotNull Value value) {
        int index = getHash(key);
        if (table[index] == null) {
            table[index] = new LinkedList<>();
        } else {
            for (Entry entry : table[index]) {
                if (entry.key.equals(key)) {
                    entry.value = value;
                    return;
                }
            }
        }
        table[index].add(new Entry(key, value));
        currentCapacity++;
        if ((double) currentCapacity / tableSize >= LOAD_FACTOR) {
            resize();
        }
    }

    @Override
    public @Nullable Value remove(@NotNull Key key) {
        int index = getHash(key);
        List<Entry> bucket = table[index];
        if (bucket == null) {
            return null;
        }
        Entry resEntry = findEntryByKey(key, bucket);
        if (resEntry == null) {
            return null;
        }
        table[index].remove(resEntry);
        currentCapacity--;
        return resEntry.value;
    }

    @Override
    public int size() {
        return currentCapacity;
    }

    @Override
    public boolean isEmpty() {
        return currentCapacity == 0;
    }

    private int getHash(Key key) {
        return (key.hashCode() & 0x7fffffff) % tableSize;
    }

    private void resize() {
        List<Entry>[] startingArray = table;
        tableSize *= 2;
        table = new LinkedList[tableSize];
        for (List<Entry> element : startingArray) {
            if (element != null) {
                for (Entry entry : element) {
                    int index = getHash(entry.key);
                    if (table[index] == null) {
                        table[index] = new LinkedList<>();
                    }
                    table[index].add(entry);
                }
            }
        }
    }

    private @Nullable Entry findEntryByKey(Key key, List<Entry> bucket) {
        Entry res = null;
        for (Entry entry : bucket) {
            if (entry.key.equals(key)) {
                res = entry;
                break;
            }
        }
        return res;
    }

    private class Entry {
        private final Key key;
        private Value value;

        Entry(Key key, Value value) {
            this.key = key;
            this.value = value;
        }
    }
}
