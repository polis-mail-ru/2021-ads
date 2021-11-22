package ru.mail.polis.ads.bst;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * LLRB implementation of binary search tree.
 */
public class RedBlackBst<Key extends Comparable<Key>, Value>
        implements Bst<Key, Value> {

    private Node root = null;
    private int size = 0;
    private static final boolean BLACK = false;
    private static final boolean RED = true;

    private class Node {
        Key key;
        Value value;
        Node left;
        Node right;
        boolean color;
    }

    boolean isRed(Node x) {
        return x != null && x.color == RED;
    }

    private Value get(Node x, Key key) {
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

    @Nullable
    @Override
    public Value get(@NotNull Key key) {
        return get(root, key);
    }

    Node rotateLeft(Node x) {
        Node y = x.right;
        x.right = y.left;
        y.left = x;
        y.color = x.color;
        x.color = RED;
        return y;
    }

    Node rotateRight(Node y) {
        Node x = y.left;
        y.left = x.right;
        x.right = y;
        x.color = y.color;
        y.color = RED;
        return x;
    }

    Node flipColors(Node x) {
        x.color = !x.color;
        x.left.color = !x.left.color;
        x.right.color = !x.right.color;
        return x;
    }

    Node fixUp(Node x) {
        if (isRed(x.right) && !isRed(x.left)) {
            x = rotateLeft(x);
        }
        if (isRed(x.left) && isRed(x.left.left)) {
            x = rotateRight(x);
        }
        if (isRed(x.left) && isRed(x.right)) {
            flipColors(x);
        }
        return x;
    }

    @Override
    public void put(@NotNull Key key, @NotNull Value value) {
        throw new UnsupportedOperationException("Implement me");
    }

    @Nullable
    @Override
    public Value remove(@NotNull Key key) {
        throw new UnsupportedOperationException("Implement me");
    }

    private Node min(Node x) {
        if (x == null) {
            return null;
        }
        if (x.left == null) {
            return x;
        }
        return min(x.left);
    }

    @Nullable
    @Override
    public Key min() {
        Node minNode = min(root);
        if (minNode == null) {
            return null;
        }
        return minNode.key;
    }

    @Nullable
    @Override
    public Value minValue() {
        Node minNode = min(root);
        if (minNode == null) {
            return null;
        }
        return minNode.value;
    }

    private Node max(Node x) {
        if (x == null) {
            return null;
        }
        if (x.right == null) {
            return x;
        }
        return max(x.right);
    }

    @Nullable
    @Override
    public Key max() {
        Node maxNode = max(root);
        if (maxNode == null) {
            return null;
        }
        return maxNode.key;
    }

    @Nullable
    @Override
    public Value maxValue() {
        Node maxNode = max(root);
        if (maxNode == null) {
            return null;
        }
        return maxNode.value;
    }

    private Node floor(Node x, Key key) {
        if (x == null) {
            return null;
        }
        if (key.compareTo(x.key) == 0) {
            return x;
        }
        if (key.compareTo(x.key) < 0) {
            return floor(x.left, key);
        }
        Node t = floor(x.right, key);
        return (t == null || t.key.compareTo(key) > 0) ? x : t;
    }

    @Nullable
    @Override
    public Key floor(@NotNull Key key) {
        Node floorNode = floor(root, key);
        if (floorNode == null) {
            return null;
        }
        return floorNode.key;
    }

    private Node ceil(Node x, Key key) {
        if (x == null) {
            return null;
        }
        if (key.compareTo(x.key) == 0) {
            return x;
        }
        if (key.compareTo(x.key) > 0) {
            return ceil(x.right, key);
        }
        Node t = ceil(x.left, key);
        return (t == null || t.key.compareTo(key) < 0) ? x : t;
    }

    @Nullable
    @Override
    public Key ceil(@NotNull Key key) {
        Node ceilNode = ceil(root, key);
        if (ceilNode == null) {
            return null;
        }
        return ceilNode.key;
    }

    @Override
    public int size() {
        return size;
    }
}
