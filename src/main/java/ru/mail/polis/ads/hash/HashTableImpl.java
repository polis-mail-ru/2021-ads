package ru.mail.polis.ads.hash;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.LinkedList;
import java.util.List;

public class HashTableImpl<Key, Value> implements HashTable<Key, Value> {

    private static final int[] capacities = new int[]{61, 127, 271, 631, 1657, 3169, 7057, 24571, 47629, 84673, 129793, 139969};
    private final float LOAD_FACTOR = 0.75F;
    private final int INITIAL_CAPACITY = 31;

    private int currentSize;
    private int newCapacityIndex;
    private int capacity = INITIAL_CAPACITY;
    private List<Node<Key, Value>>[] table = new List[INITIAL_CAPACITY];

    public HashTableImpl() {
    }

    @Override
    public @Nullable Value get(@NotNull Key key) {
        List<Node<Key, Value>> nodes = table[hash(key)];
        if (nodes == null) {
            return null;
        }
        for (Node<Key, Value> node : nodes) {
            if (node.key.equals(key)) {
                return node.value;
            }
        }
        return null;
    }

    @Override
    public void put(@NotNull Key key, @NotNull Value value) {
        if (currentSize > LOAD_FACTOR * capacity) {
            resize();
        }
        int index = hash(key);
        if (table[index] == null) {
            table[index] = new LinkedList<>();
        } else {
            for (Node<Key, Value> node : table[index]) {
                if (node.key.equals(key)) {
                    node.value = value;
                    return;
                }
            }
        }
        table[index].add(new Node<>(key, value));
        currentSize++;
    }

    @Override
    public @Nullable Value remove(@NotNull Key key) {
        List<Node<Key, Value>> nodes = table[hash(key)];
        if (nodes == null) {
            return null;
        }
        for (Node<Key, Value> node : nodes) {
            if (node.key.equals(key)) {
                currentSize--;
                Value removedValue = node.value;
                nodes.remove(node);
                return removedValue;
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

    private int hash(Key key) {
        return Math.abs(key.hashCode() % capacity);
    }

    private void resize() {
        List<Node<Key, Value>>[] currentTable = table;
        if (newCapacityIndex < capacities.length) {
            capacity = capacities[newCapacityIndex];
            newCapacityIndex++;
        } else {
            capacity *= 2;
        }
        table = new List[capacity];
        for (List<Node<Key, Value>> list : currentTable) {
            if (list != null) {
                for (Node<Key, Value> node : list) {
                    int index = hash(node.key);
                    if (table[index] == null) {
                        table[index] = new LinkedList<>();
                    }
                    table[index].add(node);
                }
            }
        }
    }

    private static class Node<K, V> {
        final K key;
        V value;

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }
}
