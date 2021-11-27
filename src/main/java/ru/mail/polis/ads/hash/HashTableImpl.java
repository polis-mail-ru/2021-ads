package ru.mail.polis.ads.hash;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.*;

public class HashTableImpl<Key, Value> implements HashTable<Key, Value> {

    static final float DEFAULT_LOAD_FACTOR = 0.75f;
    static final int DEFAULT_INITIAL_CAPACITY = 1 << 4;

    static final int hash(Object key) {
        int h;
        return (key == null) ? 0 : (h = key.hashCode()) ^ (h >>> 16);
    }

    //Number of elements
    private int size;

    //capacity * LOAD_FACTOR
    private int threshold = (int) (DEFAULT_INITIAL_CAPACITY * DEFAULT_LOAD_FACTOR);

    //Hash table
    private List<Node<Key, Value>>[] table = new List[DEFAULT_INITIAL_CAPACITY];

    public HashTableImpl() {
    }

    @Override
    public @Nullable Value get(@NotNull Key key) {
        Node<Key, Value> e;
        return (e = getNode(hash(key), key)) == null ? null : e.value;
    }

    private Node<Key, Value> getNode(int hash, Key key) {
        List<Node<Key, Value>> list;
        if ((list = table[hash & (table.length - 1)]) != null) {
            for (Node<Key, Value> node : list) {
                if (node.hash == hash && Objects.equals(key, node.key)) {
                    return node;
                }
            }
        }
        return null;
    }

    @Override
    public void put(@NotNull Key key, @NotNull Value value) {
        putVal(hash(key), key, value);
    }

    private void putVal(int hash, Key key, Value value) {
        int i;
        List<Node<Key, Value>> nodeList = table[(i = hash & (table.length - 1))];
        if (nodeList == null) {
            nodeList = table[i] = new LinkedList<>();
        }
        for (Node<Key, Value> node : nodeList) {
            if (node.hash == hash && Objects.equals(key, node.key)) {
                node.value = value;
                return;
            }
        }

        nodeList.add(new Node<>(hash, key, value));

        if (++size > threshold) {
            resize();
        }
    }

    private void resize() {
        threshold = threshold << 1;
        int newCapacity = table.length << 1;
        List<Node<Key, Value>>[] oldTab = table;
        table = new List[newCapacity];
        for (List<Node<Key, Value>> nodeList : oldTab) {
            if (nodeList != null) {
                for (Node<Key, Value> node : nodeList) {
                    List<Node<Key, Value>> newNodeList = table[node.hash & (newCapacity - 1)];
                    if (newNodeList == null) {
                        newNodeList = table[node.hash & (newCapacity - 1)] = new LinkedList<>();
                    }
                    newNodeList.add(node);
                }
            }
        }
    }

    @Override
    public @Nullable Value remove(@NotNull Key key) {
        Node<Key, Value> e;
        return (e = removeNode(hash(key), key)) == null ?
                null : e.value;
    }

    private Node<Key, Value> removeNode(int hash, Key key) {
        List<Node<Key, Value>> nodeList;
        if ((nodeList = table[hash & (table.length - 1)]) == null) {
            return null;
        }
        int i = 0;
        for (Node<Key, Value> node : nodeList) {
            if (node.hash == hash
                    && Objects.equals(key, node.key)) {
                size--;
                Value value = node.value;
                nodeList.remove(node);
                return node;
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

    // Начиная с 16 java лучше было бы использовать record
    private static class Node<Key, Value> {
        final int hash;
        final Key key;
        Value value;

        private Node(int hash, Key key, Value value) {
            this.hash = hash;
            this.key = key;
            this.value = value;
        }

        public final Key getKey() {
            return key;
        }

        public final Value getValue() {
            return value;
        }

        public final String toString() {
            return key + "=" + value;
        }

        public final int hashCode() {
            return Objects.hashCode(key) ^ Objects.hashCode(value);
        }

        public final Value setValue(Value newValue) {
            Value oldValue = value;
            value = newValue;
            return oldValue;
        }

        public final boolean equals(Object o) {
            if (o == this)
                return true;
            Node<?, ?> e = (Node<?, ?>) o;
            return Objects.equals(key, e.getKey()) &&
                    Objects.equals(value, e.getValue());
        }
    }
}
