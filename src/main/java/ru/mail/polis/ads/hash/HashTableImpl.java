package ru.mail.polis.ads.hash;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class HashTableImpl<Key, Value> implements HashTable<Key, Value> {
    static final int DEFAULT_INITIAL_CAPACITY = 1 << 4;
    static final float DEFAULT_LOAD_FACTOR = 0.75f;

    private int size = DEFAULT_INITIAL_CAPACITY;
    private int count;

    private Node<Key, Value>[] nodes = new Node[size];

    private static class Node<Key, Value> {
        final Key key;
        Value value;
        Node<Key, Value> next;

        public Node(Key key, Value value, Node<Key, Value> next) {
            this.key = key;
            this.value = value;
            this.next = next;
        }
    }

    @Nullable
    @Override
    public Value get(@NotNull Key key) {
        Node<Key, Value> node = nodes[index(key)];
        while (node != null) {
            if (node.key.equals(key)) {
                return node.value;
            }
            node = node.next;
        }
        return null;
    }

    @Override
    public void put(@NotNull Key key, @NotNull Value value) {
        if ((float) (count + 1) > DEFAULT_LOAD_FACTOR * size) {
            resize();
        }
        int index = index(key);
        Node<Key, Value> node = nodes[index];
        while (node != null) {
            if (node.key.equals(key)) {
                node.value = value;
                return;
            }
            node = node.next;
        }
        ++count;
        nodes[index] = new Node<>(key, value, nodes[index]);
    }

    @Nullable
    @Override
    public Value remove(@NotNull Key key) {
        int index = index(key);
        Node<Key, Value> node = nodes[index];
        if (node == null) {
            return null;
        }
        if (node.key.equals(key)) {
            --count;
            nodes[index] = node.next;
            return node.value;
        }
        while (node != null) {
            if (node.key.equals(key)) {
                --count;
                node.next = node.next.next;
                return node.value;
            }
            node = node.next;
        }
        return null;
    }

    @Override
    public int size() {
        return count;
    }

    @Override
    public boolean isEmpty() {
        return count == 0;
    }

    private int index(Key key) {
        return (key.hashCode() & 0x7fffffff) % size;
    }

    private void resize() {
        Node<Key, Value>[] old = nodes;
        size *= 2;
        count = 0;
        nodes = new Node[size];
        for (Node<Key, Value> elem : old) {
            while (elem != null) {
                put(elem.key, elem.value);
                elem = elem.next;
            }
        }
    }
}