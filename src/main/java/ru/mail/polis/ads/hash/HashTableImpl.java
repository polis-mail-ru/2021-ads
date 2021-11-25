package ru.mail.polis.ads.hash;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class HashTableImpl<Key, Value> implements HashTable<Key, Value> {
    private static final int DEFAULT_CAPACITY = 1 << 4;
    private static final double LOAD_FACTOR = 0.75;
    private int capacity;
    private int size;

    @SuppressWarnings("unchecked")
    private Node<Key, Value>[] buckets = (Node<Key, Value>[]) (new Node[DEFAULT_CAPACITY]);

    public HashTableImpl() {
        capacity = DEFAULT_CAPACITY;
    }

    @Override
    public @Nullable Value get(@NotNull Key key) {
        final int hashKey = hash(key);
        Node<Key, Value> bucket = buckets[hashKey];
        while (bucket != null) {
            if (bucket.key.equals(key)) {
                return bucket.value;
            }
            bucket = bucket.nextNode;
        }
        return null;
    }

    @Override
    public void put(@NotNull Key key, @NotNull Value value) {
        put(buckets, key, value);
    }

    @Override
    public @Nullable Value remove(@NotNull Key key) {
        final int hashKey = hash(key);
        Node<Key, Value> bucket = buckets[hashKey];
        if (bucket == null) {
            return null;
        }
        size--;
        if (bucket.key.equals(key)) {
            buckets[hashKey] = bucket.nextNode;
            return bucket.value;
        }
        while (bucket != null) {
            if (bucket.key.equals(key)) {
                bucket.nextNode = bucket.nextNode.nextNode;
                return bucket.value;
            }
            bucket = bucket.nextNode;
        }
        size++;
        return null;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    private void put(Node<Key, Value>[] bct, @NotNull Key key, @NotNull Value value) {
        final int hashKey = hash(key);
        Node<Key, Value> bucket = bct[hashKey];
        while (bucket != null) {
            if (bucket.key.equals(key)) {
                bucket.value = value;
                return;
            }
            bucket = bucket.nextNode;
        }
        size++;
        bct[hashKey] = new Node<>(key, value, bct[hashKey]);
        checkCapacity();
    }

    private void checkCapacity() {
        if (size / LOAD_FACTOR > capacity) {
            capacity = capacity << 1;
            buckets = rebaseBuckets();
        }
    }

    private int hash(Key key) {
        return (key.hashCode() & 0x7fffffff) % capacity;
    }

    private Node<Key, Value>[] rebaseBuckets() {
        size = 0;
        @SuppressWarnings("unchecked")
        Node<Key, Value>[] newBuckets = (Node<Key, Value>[]) (new Node[capacity]);
        for (Node<Key, Value> bucket : buckets) {
            while (bucket != null) {
                put(newBuckets, bucket.key, bucket.value);
                bucket = bucket.nextNode;
            }
        }
        return newBuckets;
    }

    private static class Node<K, V> {
        K key;
        V value;
        Node<K, V> nextNode;

        public Node(K key, V value, Node<K, V> nextNode) {
            this.key = key;
            this.value = value;
            this.nextNode = nextNode;
        }
    }
}
