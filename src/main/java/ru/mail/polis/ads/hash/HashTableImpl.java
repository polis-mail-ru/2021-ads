package ru.mail.polis.ads.hash;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class HashTableImpl<Key, Value> implements HashTable<Key, Value> {
    public static final int INITIAL_CAPACITY = 16;
    public static final double LOAD_FACTOR = 0.75;
    private static final int PRIMARY = 31;
    private static final List<Long> PRIMARY_POWERS = new ArrayList<>();
    private int size = 0;
    private Entry[] table;

    static {
        PRIMARY_POWERS.add((long) PRIMARY);
    }

    private class Entry implements Map.Entry<Key, Value> {
        private final Key key;
        private Value value;
        private Entry next;

        public Entry(Key key, Value value) {
            this.key = key;
            this.value = value;
        }

        @Override
        public Key getKey() {
            return key;
        }

        @Override
        public Value getValue() {
            return value;
        }

        @Override
        public Value setValue(Value value) {
            this.value = value;
            return value;
        }

        public Entry getNext() {
            return next;
        }

        public void setNext(Entry next) {
            this.next = next;
        }

    }

    @SuppressWarnings("unchecked")
    private void possibleResize() {
        if ((double) size / table.length < LOAD_FACTOR) {
            return;
        }
        Entry[] bigger = (Entry[]) Array.newInstance(Entry.class, table.length * 2);
        for (Entry entry : table) {
            if (entry == null) {
                continue;
            }
            putInMap(entry, bigger);
        }
        table = bigger;
    }


    private void putInMap(Entry entry, Entry[] ht) {
        while (entry != null) {
            int hash = hash(entry.getKey(), ht.length);
            if (ht[hash] == null) {
                ht[hash] = new Entry(entry.getKey(), entry.getValue());
                entry = entry.getNext();
                continue;
            }
            Entry start = ht[hash];
            while (start.getNext() != null) {
                start = start.getNext();
            }
            start.setNext(new Entry(entry.getKey(), entry.getValue()));
            entry = entry.getNext();
        }
    }

    private int hash(Key key, int length) {
//        if (key instanceof String) {
//            return (int) (stringHash((String) key) % length);
//        }
        return Math.abs(key.hashCode() % length);
    }

    @SuppressWarnings("unchecked")
    public HashTableImpl() {
        table = (Entry[]) Array.newInstance(Entry.class, INITIAL_CAPACITY);
    }

    @Override
    public @Nullable Value get(@NotNull Key key) {
        Entry entry = table[hash(key, table.length)];
        if (entry == null) {
            return null;
        }
        while (entry != null) {
            if (entry.getKey().equals(key)) {
                return entry.getValue();
            }
            entry = entry.getNext();
        }
        return null;
    }

    @Override
    public void put(@NotNull Key key, @NotNull Value value) {
        int hash = hash(key, table.length);
        Entry next = table[hash];
        if (next == null) {
            table[hash] = new Entry(key, value);
            size++;
            return;
        }
        Entry previous = null;
        while (next != null) {
            if (next.getKey().equals(key)) {
                next.setValue(value);
                return;
            }
            previous = next;
            next = next.getNext();
        }
        previous.setNext(new Entry(key, value));
        size++;
        possibleResize();
    }

    @Override
    public @Nullable Value remove(@NotNull Key key) {
        int hash = hash(key, table.length);
        Entry entry = table[hash];
        if (entry == null) {
            return null;
        }
        Entry previous = null;
        while (entry != null) {
            if (entry.getKey().equals(key)) {
                if (previous == null) {
                    table[hash] = entry.getNext();
                } else {
                    previous.setNext(entry.getNext());
                }
                size--;
                return entry.getValue();
            }
            previous = entry;
            entry = entry.getNext();
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

    private long stringHash(String key) {
        long result = 0;
        for (int i = 0; i < key.length(); i++) {
            if (PRIMARY_POWERS.size() == i) {
                PRIMARY_POWERS.add(PRIMARY_POWERS.get(i - 1) * PRIMARY);
            }
            long temp = (long) key.charAt(i) * PRIMARY_POWERS.get(i);
            result += temp;
        }
        return (result < 0) ? result * -1 : result;
    }

}
