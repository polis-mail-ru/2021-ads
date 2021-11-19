package ru.mail.polis.ads.hash;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class HashTableImpl<Key, Value> implements HashTable<Key, Value> {
    private int DEFAULT_CAPACITY = 16;
    private int capacity = DEFAULT_CAPACITY;
    private float LOAD_FACTOR = 0.75f;
    private int size = 0;

    private List<Node>[] data = new List[DEFAULT_CAPACITY];

    public HashTableImpl() {
    }

    @Override
    public @Nullable Value get(@NotNull Key key) {
        List<Node> nodes = data[hash(key)];
        if (nodes == null) {
            return null;
        }

        for (Node node : nodes) {
            if (node.key.equals(key)) {
                return node.value;
            }
        }
        return null;
    }

    private int hash(Key key) {
        return Math.abs(key.hashCode() % capacity);//(key.hashCode() % capacity) & 0x7fffffff;
    }

    @Override
    public void put(@NotNull Key key, @NotNull Value value) {
        if (size >= capacity * LOAD_FACTOR) {
            resize();
        }
        int hash = hash(key);
        List<Node> nodes = data[hash];
        if (nodes == null) {
            nodes = new LinkedList<>();
            data[hash] = nodes;
        }
        for (Node node : nodes) {
            if (node.key.equals(key)) {
                node.value = value;
                return;
            }
        }
        nodes.add(new Node(key, value));
        size++;
    }

    private void resize() {
        capacity *= 2;
        List<Node>[] newTable = new List[capacity];

        for (List<Node> list : data) {
            if (list != null) {
                for (Node node : list) {
                    put(newTable, node);
                }
            }
        }
        data = newTable;
    }

    private void put(List<Node>[] newTable, Node node) {
        int hash = hash(node.key);
        List<Node> nodes = newTable[hash];
        if (nodes == null) {
            nodes = new LinkedList<>();
            newTable[hash] = nodes;
        }
        for (Node newNode : nodes) {
            if (newNode.key.equals(node.key)) {
                newNode.value = node.value;
                return;
            }
        }
        nodes.add(node);
    }

    @Override
    public @Nullable Value remove(@NotNull Key key) {
        List<Node> nodes = data[hash(key)];
        if (nodes == null) {
            return null;
        }

        for (Iterator<Node> i = nodes.listIterator(); i.hasNext(); ) {
            Node node = i.next();
            if (node.key.equals(key)) {
                size--;
                Value value = node.value;
                i.remove();
                return value;
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

    private class Node {
        Key key;
        Value value;

        public Node(Key key, Value value) {
            this.key = key;
            this.value = value;
        }
    }
}
