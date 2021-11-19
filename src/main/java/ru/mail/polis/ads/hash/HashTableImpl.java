package ru.mail.polis.ads.hash;

import java.util.Optional;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class HashTableImpl<Key, Value> implements HashTable<Key, Value> {

    private static final int DEFAULT_CAPACITY = 16;
    private static final double LOADFACTOR = 0.75;

    private int capacity = DEFAULT_CAPACITY;
    private int size = 0;
    @SuppressWarnings("unchecked")
    private Node<Key, Value>[] table = (Node<Key, Value>[]) (new Node[DEFAULT_CAPACITY]);

    public HashTableImpl() {
    }

    @Override
    public @Nullable Value get(@NotNull Key key) {
        int hash = Math.abs(key.hashCode()) % capacity;
        if ((table[hash] == null) || (table[hash].find(key) == null)) {
            return null;
        }
        return table[hash].find(key).getValue();
    }

    @Override
    public void put(@NotNull Key key, @NotNull Value value) {
        if ((double) size / capacity > LOADFACTOR) {
            capacity = capacity << 1;
            table = expandList(capacity);
        }
        if (putOrReplace(table, key, value)) {
            size++;
        }
    }

    private Node<Key, Value>[] expandList(int newCapacity) {
        @SuppressWarnings("unchecked") Node<Key, Value>[] newtable = (Node<Key, Value>[]) (new Node[newCapacity]);
        for (Node<Key, Value> element : table) {
            while (element != null) {
                putOrReplace(newtable, element.getKey(), element.getValue());
                element = element.getNext();
            }
        }
        return newtable;
    }

    /**
     * @return true - if put, or false if replaced
     */
    private boolean putOrReplace(Node<Key, Value>[] table, @NotNull Key key, @NotNull Value value) {
        int hash = Math.abs(key.hashCode()) % capacity;
        Node<Key, Value> list = table[hash];
        if (list == null) {
            table[hash] = new Node<Key, Value>(key, value);
        } else if (list.replace(key, value)) {
            return false;
        } else {
            list.addLast(key, value);
        }
        return true;
    }

    @Override
    public @Nullable Value remove(@NotNull Key key) {
        int hash = Math.abs(key.hashCode()) % capacity;
        Node<Key, Value> list = table[hash];
        if (list == null) {
            return null;
        }
        Value value;
        if (list.getNext() == null) {
            value = list.getValue();
            table[hash] = null;
        } else {
            value = list.removeLast().getValue();
        }
        size--;
        return value;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return (size() == 0);
    }

    private static class Node<Key, Value> {
        private Key key;
        private Value value;
        private Node<Key, Value> next;

        public Node(Key key, Value value) {
            this.key = key;
            this.value = value;
        }

        public Key getKey() {
            return key;
        }

        public Value getValue() {
            return value;
        }

        public Node<Key, Value> find(Key key) {
            for (Node<Key, Value> current = this; current != null; current = current.next) {
                if (current.key.equals(key)) {
                    return current;
                }
            }
            return null;
        }

        public void addLast(Key key, Value value) {
            Node<Key, Value> current = this;
            while (current.next != null) {
                current = current.next;
            }
            current.next = new Node<Key, Value>(key, value);
        }

        public Node<Key, Value> getLast() {
            Node<Key, Value> current = this;
            while (current.next != null) {
                current = current.next;
            }
            return current;
        }

        public Node<Key, Value> removeLast() {
            Node<Key, Value> current = this;
            while (current.next != null) {
                current = current.next;
            }
            return current;
        }

        public Node<Key, Value> getNext() {
            return next;
        }

        public boolean replace(Key key, Value value) {
            for (Node<Key, Value> current = this; current != null; current = current.next) {
                if (current.key.equals(key)) {
                    current.value = value;
                    return true;
                }
            }
            return false;
        }
    }
}
