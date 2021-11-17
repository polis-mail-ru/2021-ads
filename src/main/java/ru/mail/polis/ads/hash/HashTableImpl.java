package ru.mail.polis.ads.hash;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.lang.reflect.Array;
import java.util.HashMap;
import java.util.LinkedList;

public class HashTableImpl<Key, Value> implements HashTable<Key, Value> {
    private static final int INITIAL_CAPACITY = 16;

    private int capacity = INITIAL_CAPACITY;
    private int size = 0;
    @SuppressWarnings("unchecked")
    private Node<Key,Value>[] table = (Node<Key,Value>[])new Node[INITIAL_CAPACITY];

    public HashTableImpl() {
    }

    @Override
    public @Nullable Value get(@NotNull Key key) {
        int index = Math.abs(key.hashCode()) % capacity;

        if (table[index] == null) {
            return null;
        }

        Node<Key, Value> node = table[index];
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
        int hash = Math.abs(key.hashCode());
        int index = hash % capacity;
        Node<Key, Value> newNode = new Node<>(key, value, hash);

        if (table[index] == null) {
            table[index] = newNode;
            size++;
            return;
        }

        Node<Key, Value> node = table[index];
        Node<Key, Value> prev = node;
        while (node != null) {
            if (node.key.equals(key)) {
                node.value = value;
                node.keyHash = hash;
                return;
            }
            prev = node;
            node = node.next;
        }

        prev.next = newNode;
        size++;

        if ((double) size / capacity >= 0.75) {
            resize();
        }
    }

    @Override
    public @Nullable Value remove(@NotNull Key key) {
        int index = Math.abs(key.hashCode()) % capacity;

        Node<Key, Value> node = table[index];

        if (node == null) {
            return null;
        }

        if (node.key.equals(key)) {
            table[index] = node.next;
            size--;
            return node.value;
        }

        while (node.next != null) {
            if (node.next.key.equals(key)) {
                Value res = node.next.value;
                node.next = node.next.next;
                size--;
                return res;
            }
            node = node.next;
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

    private static class Node<Key, Value> {
        Key key;
        Value value;
        int keyHash;
        Node<Key, Value> next = null;

        Node(Key key, Value value, int keyHash) {
            this.key = key;
            this.value = value;
            this.keyHash = keyHash;
        }

        Node<Key, Value> newNodeWithoutNext(){
            return new Node<>(key, value, keyHash);
        }
    }

    private void resize() {
        int newCapacity = capacity * 2;

        @SuppressWarnings("unchecked")
        Node<Key,Value>[] newTable = (Node<Key,Value>[])new Node[newCapacity];

        for (int i = 0; i < capacity; i++) {
            if (table[i] == null) {
                continue;
            }

            Node<Key, Value> node = table[i];
            while (node != null) {
                putInNewTable(newTable, newCapacity, node.newNodeWithoutNext());
                node = node.next;
            }
        }

        capacity = newCapacity;
        table = newTable;
    }

    private void putInNewTable(Node<Key,Value>[] newTable, int newCapacity, Node<Key,Value> newNode) {
        int index = newNode.keyHash % newCapacity;

        if (newTable[index] == null) {
            newTable[index] = newNode;
            return;
        }

        Node<Key, Value> node = newTable[index];
        while (node.next != null) {
            node = node.next;
        }

        node.next = newNode;
    }
}
