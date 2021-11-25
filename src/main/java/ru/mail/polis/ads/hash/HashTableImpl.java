package ru.mail.polis.ads.hash;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class HashTableImpl<Key, Value> implements HashTable<Key, Value> {

    private static final int INITIAL_CAPACITY = 16;
    private static final float LOAD_FACTOR = 0.75F;
    private int currentCapacity;
    private Node<Key, Value>[] array;
    private int occupied = 0;
    private int size = 0;

    @SuppressWarnings("unchecked")
    public HashTableImpl() {
        currentCapacity = INITIAL_CAPACITY;
        array = (Node<Key, Value>[]) new Node[INITIAL_CAPACITY];
    }

    @Override
    public @Nullable Value get(@NotNull Key key) {
        return findValue(key);
    }

    @Override
    public void put(@NotNull Key key, @NotNull Value value) {
        Node<Key, Value> node = new Node<>(key, value);
        insert(node);
    }

    @Override
    public @Nullable Value remove(@NotNull Key key) {
        return deleteElement(key);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private int hash(Key key) {
        int h = key.hashCode();
        return (h ^ (h >>> 16)) & (currentCapacity - 1);
    }

    private void insert(Node<Key, Value> node) {
        int index = hash(node.getKey());
        Node<Key, Value> current = array[index];
        if (current == null) {
            array[index] = node;
            occupied++;
        } else {
            Node<Key, Value> prev = null;
            while (current != null) {
                if (current.getKey().equals(node.getKey())) {
                    current.setValue(node.getValue());
                    return;
                }
                prev = current;
                current = current.getNextNode();
            }
            prev.setNextNode(node);
        }

        size++;
        if (((float) occupied / currentCapacity) > LOAD_FACTOR) {
            upgrade();
        }
    }

    @SuppressWarnings("unchecked")
    private void upgrade() {
        currentCapacity *= 2;
        Node<Key, Value>[] newArray = (Node<Key, Value>[]) new Node[currentCapacity];

        int index;
        Node<Key, Value> current;
        Node<Key, Value> nextElement;
        for (Node<Key, Value> node : array) {
            if (node == null) {
                continue;
            }

            current = node;
            while (current != null) {
                nextElement = current.getNextNode();
                index = hash(current.getKey());
                current.setNextNode(null);
                if (newArray[index] == null) {
                    newArray[index] = current;
                } else {
                    insertInBucket(newArray[index], current);
                }
                current = nextElement;
            }
        }
        array = newArray;
    }

    private void insertInBucket(Node<Key, Value> start, Node<Key, Value> newNode) {
        Node<Key, Value> current = start;

        while (current.getNextNode() != null) {
            current = current.getNextNode();
        }
        current.setNextNode(newNode);
    }

    private Value findValue(Key key) {
        Node<Key, Value> current = array[hash(key)];
        if (current == null) {
            return null;
        }

        while (current != null) {
            if (current.getKey().equals(key)) {
                return current.getValue();
            }
            current = current.getNextNode();
        }
        return null;
    }

    private Value deleteElement(Key key) {
        int index = hash(key);
        if (array[index] == null) {
            return null;
        }

        Node<Key, Value> current = array[index];
        Node<Key, Value> prev = null;
        while (current != null) {
            if (current.getKey().equals(key)) {
                size--;
                if (prev == null && current.getNextNode() == null) {
                    array[index] = null;
                } else if (prev == null) {
                    array[index] = current.getNextNode();
                } else {
                    prev.setNextNode(current.getNextNode());
                }
                return current.getValue();
            }
            prev = current;
            current = current.getNextNode();
        }
        return null;
    }

}
