package ru.mail.polis.ads.bst;

import org.jetbrains.annotations.NotNull;

/**
 * AVL implementation of binary search tree.
 */
public class AvlBst<Key extends Comparable<Key>, Value>
        implements Bst<Key, Value> {

    private Node root;

    private class Node {
        Key key;
        Value value;
        Node left;
        Node right;
        int height;
    }

    Value get(Node x, Key key) {
        if (x == null) {
            return null;
        }
        if (key.compareTo(x.key) < 0) {
            return get(x.left, key);
        }
        if (key.compareTo(x.key) > 0) {
            return get(x.right, key);
        }
        return x.value;
    }
    @Override
    public Value get(@NotNull Key key) {
        return get(root, key);
    }

    @Override
    public void put(@NotNull Key key, @NotNull Value value) {
        throw new UnsupportedOperationException("Implement me");
    }

    @Override
    public Value remove(@NotNull Key key) {
        throw new UnsupportedOperationException("Implement me");
    }

    @Override
    public Key min() {
        throw new UnsupportedOperationException("Implement me");
    }

    @Override
    public Value minValue() {
        throw new UnsupportedOperationException("Implement me");
    }

    @Override
    public Key max() {
        throw new UnsupportedOperationException("Implement me");
    }

    @Override
    public Value maxValue() {
        throw new UnsupportedOperationException("Implement me");
    }

    @Override
    public Key floor(@NotNull Key key) {
        throw new UnsupportedOperationException("Implement me");
    }

    @Override
    public Key ceil(@NotNull Key key) {
        throw new UnsupportedOperationException("Implement me");
    }

    @Override
    public int size() {
        throw new UnsupportedOperationException("Implement me");
    }

    @Override
    public int height() {
        throw new UnsupportedOperationException("Implement me");
    }
}
