package ru.mail.polis.ads.hash;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class HashTableImpl<Key, Value> implements HashTable<Key, Value> {
    private static final int CAPACITY = 10;
    private final Node[] arr;
    private int size;

    public HashTableImpl() {
        this.arr = new Node[CAPACITY];
    }

    @Override
    public @Nullable Value get(@NotNull Key key) {
        int i = getIndex(key);
        Node node = this.arr[i];
        if (node == null) {
            return null;
        }

        while (node != null && !node.key.equals(key)) {
            node = node.next;
        }

        return node == null ? null : (Value) node.value;
    }

    @Override
    public void put(@NotNull Key key, @NotNull Value value) {
        final int i = getIndex(key);

        Node currentBackedHead = arr[i];
        if (currentBackedHead == null) {
            arr[i] = new Node(key, value);
            size++;
            return;
        }

        while (!currentBackedHead.key.equals(key) && currentBackedHead.next != null) {
            currentBackedHead = currentBackedHead.next;
        }

        if (currentBackedHead.key.equals(key)) {
            currentBackedHead.value = value;
        } else {
            size++;
            currentBackedHead.next = new Node(key, value);
        }
    }

    @Override
    public @Nullable Value remove(@NotNull Key key) {
        final int i = getIndex(key);

        Node currentBackedHead = arr[i];
        if (currentBackedHead == null) {
            return null;
        }

        if (currentBackedHead.key.equals(key)) {
            Value value = (Value) currentBackedHead.value;
            arr[i] = currentBackedHead.next;

            size--;
            return value;
        }

        while (currentBackedHead.next != null && !currentBackedHead.next.key.equals(key)) {
            currentBackedHead = currentBackedHead.next;
        }

        if (currentBackedHead.next == null) {
            return null;
        } else {
            size--;
            Value value = (Value) currentBackedHead.next.value;

            currentBackedHead.next = currentBackedHead.next.next;

            return value;
        }
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private int getIndex(Key key) {
        return key.hashCode() % CAPACITY;
    }

    private static class Node {
        Object key;
        Object value;
        Node next;

        public Node(Object key, Object value) {
            this.key = key;
            this.value = value;
        }
    }
}
