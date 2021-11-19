package ru.mail.polis.ads.hash;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.*;

public class HashTableImpl<Key, Value> implements HashTable<Key, Value> {
    private static final double LOAD_FACTOR = 0.75;
    private static final int DEFAULT_CAPACITY = 16;

    private int capacity = DEFAULT_CAPACITY;
    private List<Node<Key, Value>>[] buckets = new LinkedList[capacity];
    private int size = 0;

    public HashTableImpl() {
    }

    @Override
    public @Nullable Value get(@NotNull Key key) {
        int index = calculateIndex(key);
        List<Node<Key, Value>> bucket = buckets[index];
        if (bucket == null) {
            return null;
        }
        for (Node<Key, Value> node : bucket) {
            if (node.key.equals(key)) {
                return node.value;
            }
        }
        return null;
    }

    @Override
    public void put(@NotNull Key key, @NotNull Value value) {
        int index = calculateIndex(key);
        List<Node<Key, Value>> bucket = buckets[index];
        if (bucket == null) {
            bucket = new LinkedList<>();
            buckets[index] = bucket;

        }
        for (Node<Key, Value> node : bucket) {
            if (node.key.equals(key)) {
                node.value = value;
                return;
            }
        }
        bucket.add(new Node<>(key, value));
        size++;
        resizeIfNeeded();
    }

    @Override
    public @Nullable Value remove(@NotNull Key key) {
        int index = calculateIndex(key);
        List<Node<Key, Value>> bucket = buckets[index];
        if (bucket == null) {
            return null;
        }
        for (Node<Key, Value> node : bucket) {
            if (node.key.equals(key)) {
                bucket.remove(node);
                size--;
                return node.value;
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

    private void resizeIfNeeded() {
        if ((double) size / capacity <= LOAD_FACTOR) {
            return;
        }
        List<Node<Key, Value>>[] oldBuckets = buckets;
        capacity = capacity * 2;
        buckets = new LinkedList[capacity];
        for (List<Node<Key, Value>> bucket : oldBuckets) {
            if (bucket == null) {
                continue;
            }
            for (Node<Key, Value> node : bucket) {
                int index = calculateIndex(node.key);
                if (buckets[index] == null) {
                    buckets[index] = new LinkedList<>();
                }
                buckets[index].add(node);
            }
        }
    }

    private int calculateIndex(Key key) {
        return Math.abs(key.hashCode() % capacity);
    }

    private static class Node<Key, Value> {
        final Key key;
        Value value;

        public Node(Key key, Value value) {
            this.key = key;
            this.value = value;
        }
    }
}
