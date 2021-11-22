package ru.mail.polis.ads.hash;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.LinkedList;
import java.util.List;

public class HashTableImpl<Key, Value> implements HashTable<Key, Value> {

    private final static double LOAD_FACTOR = 0.75;
    private final static int INITIAL_SIZE = 16;

    private int size = 0;
    private int capacity = INITIAL_SIZE;

    private List<Node<Key, Value>>[] table = new LinkedList[capacity];

    private class Node<Key, Value> {
        Key key;
        Value value;

        Node(Key key, Value value) {
            this.key = key;
            this.value = value;
        }
    }

    public HashTableImpl() {
    }

    @Override
    public @Nullable Value get(@NotNull Key key) {
        List<Node<Key, Value>> bucket = table[getHash(key)];
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
        int putIndex = getHash(key);
        List<Node<Key, Value>> bucket = table[putIndex];
        if (bucket == null) {
            bucket = new LinkedList<>();
            table[putIndex] = bucket;
        } else {
            for (Node<Key, Value> node : bucket) {
                if (node.key.equals(key)) {
                    node.value = value;
                    return;
                }
            }
        }

        bucket.add(new Node<>(key, value));
        size++;
        resize();
    }

    @Override
    public @Nullable Value remove(@NotNull Key key) {
        List<Node<Key, Value>> bucket = table[getHash(key)];
        if (bucket == null) {
            return null;
        }

        for (Node<Key, Value> node : bucket) {
            if (node.key.equals(key)) {
                Value result = node.value;
                bucket.remove(node);
                size--;
                return result;
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

    private int getHash(Key key) {
        return Math.abs(key.hashCode() % capacity);
    }

    private void resize() {
        if ((double) size / capacity <= LOAD_FACTOR) {
            return;
        }

        List<Node<Key, Value>>[] fixedTable = table;
        capacity = capacity * 2;

        table = new LinkedList[capacity];
        for (List<Node<Key, Value>> bucket : fixedTable) {
            if (bucket == null) {
                continue;
            }

            for (Node<Key, Value> node : bucket) {
                int index = getHash(node.key);

                if (table[index] == null) {
                    table[index] = new LinkedList<>();
                }

                table[index].add(node);
            }
        }
    }
}
