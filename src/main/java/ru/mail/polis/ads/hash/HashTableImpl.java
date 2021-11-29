package ru.mail.polis.ads.hash;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.*;

public class HashTableImpl<Key, Value> implements HashTable<Key, Value> {

    private static final int[] capacities = new int[] {31, 53, 89, 151, 257, 439, 751, 1277, 2179, 3709, 6311, 10729,
            18251, 31033, 52757, 152501, 259271, 440761, 749297, 1273823};
    private static final float DEFAULT_LOAD_FACTOR = 0.75f;

    private int currentSize;
    private int capacityIndex;
    private int capacity = capacities[capacityIndex++];
    private List<Node<Key, Value>>[] table = list(capacity);

    private static class Node<Key, Value> {
        private final Key key;
        private Value value;

        public Node(Key key, Value value) {
            this.key = key;
            this.value = value;
        }
    }

    public HashTableImpl() {
    }

    @Override
    public @Nullable Value get(@NotNull Key key) {
        List<Node<Key, Value>> bucket = table[hash(key)];
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

    private int hash(Key key) {
        return (key.hashCode() & 0x7fffffff) % capacity;
    }

    @Override
    public void put(@NotNull Key key, @NotNull Value value) {
        if (currentSize > DEFAULT_LOAD_FACTOR * capacity) {
            resize();
        }
        int index = hash(key);
        List<Node<Key, Value>> bucket = table[index];
        if (bucket == null) {
            table[index] = new LinkedList<>();
            bucket = table[index];
        }
        for (Node<Key, Value> node : bucket) {
            if (node.key.equals(key)) {
                node.value = value;
                return;
            }
        }
        bucket.add(new Node<>(key, value));
        currentSize++;
    }

    @Override
    public @Nullable Value remove(@NotNull Key key) {
        List<Node<Key, Value>> bucket = table[hash(key)];
        if (bucket == null) {
            return null;
        }
        Value value;
        Node<Key, Value> node;
        for (Iterator<Node<Key, Value>> iter = bucket.iterator(); iter.hasNext(); ) {
           node = iter.next();
           if (node.key.equals(key)) {
               value = node.value;
               iter.remove();
               currentSize--;
               return value;
           }
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

    private void resize() {
        if (capacityIndex < capacities.length) {
            capacity = capacities[capacityIndex++];
        } else {
            capacity *= 1.7;
        }
        List<Node<Key, Value>>[] oldTable = table;
        table = list(capacity);
        currentSize = 0;
        for (List<Node<Key, Value>> bucket : oldTable) {
            if (bucket != null) {
                for (Node<Key, Value> node : bucket) {
                    put(node.key, node.value);
                }
            }
        }
    }

    @SuppressWarnings("unchecked")
    private List<Node<Key, Value>>[] list(int capacity) {
        return new LinkedList[capacity];
    }
}
