package ru.mail.polis.ads.hash;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class HashTableImpl<Key, Value> implements HashTable<Key, Value> {

    private static final int[] PRIME_CAPACITIES = {
                   53,        97,       193,       389,        769,     1543,     3079,
                 6151,     12289,     24593,     49157,      98317,   196613,   393241,
               786433,   1572869,   3145739,   6291469,   12582917, 25165843, 50331653,
            100663319, 201326611, 402653189, 805306457, 1610612741
    };

    private static final int DEFAULT_INITIAL_CAPACITY = PRIME_CAPACITIES[0];
    private static final double LOAD_FACTOR = 0.75;

    int size;
    Node[] table;
    int capacityIndex = 0;
    int capacity = DEFAULT_INITIAL_CAPACITY;

    public HashTableImpl() {
    }

    @Override
    public @Nullable Value get(@NotNull Key key) {
        if (table == null) {
            return null;
        }
        final int hash = key.hashCode();
        final Node bucket = bucket(hash);
        return bucket == null ? null : bucket.get(key);
    }

    @Override
    public void put(@NotNull Key key, @NotNull Value value) {
        if (table == null || size >= LOAD_FACTOR * capacity()) {
            table = resize();
        }
        final int hash = key.hashCode();
        final int index = index(hash);
        if (table[index] == null) {
            table[index] = new Node(hash, key, value);
            size++;
            return;
        }
        table[index].put(hash, key, value);
    }

    @Override
    public @Nullable Value remove(@NotNull Key key) {
        if (table == null) {
            return null;
        }
        final int hash = key.hashCode();
        final int index = index(hash);
        if (table[index] == null) {
            return null;
        }
        if (table[index].next == null && table[index].key.equals(key)) {
            Value previous = table[index].value;
            table[index] = null;
            size--;
            return previous;
        }
        return table[index].remove(hash, key);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    private int capacity() {
        return table != null ? table.length : DEFAULT_INITIAL_CAPACITY;
    }

    private int index(int hash) {
        return Math.floorMod(hash, capacity());
    }

    private Node bucket(int hash) {
        return table[index(hash)];
    }

    private Node[] resize() {
        if (capacityIndex >= PRIME_CAPACITIES.length) {
            return table;
        }
        if (table != null) {
            capacityIndex++;
        }
        final int newCapacity = PRIME_CAPACITIES[capacityIndex];
        final Node[] newTable = (Node[])new HashTableImpl<?, ?>.Node[newCapacity];
        if (table == null) {
            return newTable;
        }
        for (Node bucket : table) {
            for (Node node = bucket, next; node != null; node = next) {
                next = node.next;
                int index = Math.floorMod(node.hash, newCapacity);
                node.next = newTable[index];
                newTable[index] = node;
            }
        }
        return newTable;
    }

    private class Node {
        final Key key;
        final int hash;
        Value value;
        Node next;

        Node(int hash, Key key, Value value, Node next) {
            this.hash = hash;
            this.key = key;
            this.value = value;
            this.next = next;
        }

        Node(int hash, Key key, Value value) {
            this(hash, key, value, null);
        }

        Node(Key key, Value value) {
            this(key.hashCode(), key, value, null);
        }

        Value get(Key key) {
            Node node = this;
            while (!(node.next == null || node.hash == key.hashCode() && node.key.equals(key))) {
                node = node.next;
            }
            return node.value;
        }

        void put(int hash, Key key, Value value) {
            Node node = this;
            while (!(node.next == null || node.hash == hash && node.key.equals(key))) {
                node = node.next;
            }
            //node.next == null || node.key.equals(key)
            if (node.next != null || node.key.equals(key)) {
                node.value = value;
                return;
            }
            node.next = new Node(hash, key, value);
            size++;
        }

        Value remove(int hash, Key key) {
            Node node = this;
            while (!(node.next.next == null || node.next.hash == hash && node.next.key.equals(key))) {
                node = node.next;
            }
            if (node.next.next != null || node.next.hash == hash && node.next.key.equals(key)) {
                size--;
            }
            Value previous = node.next.value;
            node.next = node.next.next;
            return previous;
        }
    }
}
