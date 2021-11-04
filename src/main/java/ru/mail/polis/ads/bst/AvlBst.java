package ru.mail.polis.ads.bst;

import org.jetbrains.annotations.NotNull;

/**
 * AVL implementation of binary search tree.
 */
public class AvlBst<Key extends Comparable<Key>, Value>
        implements Bst<Key, Value> {

    private Node root;
    private int size;

    private class Node {
        Key key;
        Value value;
        Node left;
        Node right;
        int height;

        public Node(Key key, Value value, int height) {
            this.key = key;
            this.value = value;
            this.height = height;
        }
    }

    public AvlBst() {
        size = 0;
    }

    @Override
    public Value get(@NotNull Key key) {
        Node d = get(root, key);
        return d == null ? null : d.value;
    }

    private Node get(Node x, Key key) {
        if (x == null) return null;
        if (key.compareTo(x.key) > 0) return get(x.right, key);
        if (key.compareTo(x.key) < 0) return get(x.left, key);
        return x;
    }

    @Override
    public void put(@NotNull Key key, @NotNull Value value) {
        root = put(root, key, value);
    }

    private Node put(Node x, Key key, Value value) {
        if (x == null) {
            size++;
            return new Node(key, value, 1);
        }
        if (key.compareTo(x.key) < 0) {
            x.left = put(x.left, key, value);
        } else if (key.compareTo(x.key) > 0) {
            x.right = put(x.right, key, value);
        } else {
            x.value = value;
        }
        fixHeight(x);
        x = balance(x);
        return x;
    }

    private Node rotateRight(Node y) {
        Node x = y.left;
        y.left = x.right;
        x.right = y;
        fixHeight(x);
        fixHeight(y);
        return x;

    }

    private Node rotateLeft(Node x) {
        Node y = x.right;
        x.right = y.left;
        y.left = x;
        fixHeight(x);
        fixHeight(y);
        return y;
    }

    private int factor(Node x) {
        if (x != null)
            return height(x.left) - height(x.right);
        return 0;
    }

    private Node balance(Node x) {
        if (factor(x) == 2) {
            if (factor(x.left) < 0) x.left = rotateLeft(x.left);
            return rotateRight(x);
        }
        if (factor(x) == -2) {
            if (factor(x.right) > 0)
                x.right = rotateRight(x.right);
            return rotateLeft(x);
        }
        return x;
    }

    @Override
    public Value remove(@NotNull Key key) {
        Node toRemove = get(root, key);
        root = remove(root, key);
        return toRemove == null ? null : toRemove.value;
    }

    private Node remove(Node x, Key key) {
        if (x == null) {
            return null;
        }
        if (key.compareTo(x.key) < 0) {
            x.left = remove(x.left, key);
        }
        if (key.compareTo(x.key) > 0) {
            x.right = remove(x.right, key);
        }
        if (key == x.key) {
            x = innerDelete(x);
            size--;
        }
        fixHeight(x);
        x = balance(x);
        return x;
    }

    private Node innerDelete(Node x) {
        if (x.right == null)
            return x.left;
        if (x.left == null)
            return x.right;
        Node t = x;
        x = min(t.right);
        x.right = deleteMin(t.right);
        x.left = t.left;
        return x;
    }

    private Node deleteMin(Node x) {
        if (x.left == null) return x.right;
        x.left = deleteMin(x.left);
        return x;

    }

    @Override
    public Key min() {
        Node m = min(root);
        return m == null ? null : m.key;
    }

    private Node min(Node x) {
        while (x != null && x.left != null) {
            x = x.left;
        }
        return x;
    }

    @Override
    public Value minValue() {
        Node m = min(root);
        return m == null ? null : m.value;
    }

    @Override
    public Key max() {
        Node m = max(root);
        return m == null ? null : m.key;
    }


    private Node max(Node x) {
        while (x != null && x.right != null) {
            x = x.right;
        }
        return x;
    }

    @Override
    public Value maxValue() {
        Node m = max(root);
        return m == null ? null : m.value;
    }

    @Override
    public Key floor(@NotNull Key key) {
        Node x = floor(root, key);
        return x == null ? null : x.key;
    }

    private Node floor(Node x, Key key) {
        if (x == null)
            return null;

        if (x.key == key)
            return x;

        if (key.compareTo(x.key) < 0) {
            return floor(x.left, key);
        }

        Node t = floor(x.right, key);
        return t == null ? x : t;
    }

    @Override
    public Key ceil(@NotNull Key key) {
        Node x = ceil(root, key);
        return x == null ? null : x.key;
    }

    private Node ceil(Node x, Key key) {
        if (x == null)
            return null;

        if (x.key == key)
            return x;

        if (key.compareTo(x.key) > 0) {
            return ceil(x.right, key);
        }

        Node t = ceil(x.left, key);
        return t == null ? x : t;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public int height() {
        return height(root);
    }

    private int height(Node x) {
        return x == null ? 0 : x.height;
    }

    private void fixHeight(Node x) {
        if (x != null)
            x.height = 1 + Math.max(height(x.left), height(x.right));
    }
}
