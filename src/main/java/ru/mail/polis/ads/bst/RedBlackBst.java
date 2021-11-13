package ru.mail.polis.ads.bst;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * LLRB implementation of binary search tree.
 */
public class RedBlackBst<Key extends Comparable<Key>, Value>
        implements Bst<Key, Value> {

    private static final boolean BLACK = false;
    private static final boolean RED = true;

    private Node root;
    private int size;

    @Nullable
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
        root.color = BLACK;
    }

    private Node put(Node x, Key key, Value value) {
        if (x == null) {
            ++size;
            return new Node(key, value, RED);
        }
        if (x.key.compareTo(key) < 0)
            x.right = put(x.right, key, value);
        else if (x.key.compareTo(key) > 0)
            x.left = put(x.left, key, value);
        else
            x.value = value;
        return fixUp(x);
    }

    @Nullable
    @Override
    public Value remove(@NotNull Key key) {
        Node toRemove = get(root, key);
        Value retval = toRemove == null ? null : toRemove.value;
        root = delete(root, key);
        return retval;
    }

    @Nullable
    @Override
    public Key min() {
        Node m = min(root);
        return m == null ? null : m.key;
    }

    @Nullable
    @Override
    public Value minValue() {
        Node m = min(root);
        return m == null ? null : m.value;
    }

    @Nullable
    @Override
    public Key max() {
        Node m = max(root);
        return m == null ? null : m.key;
    }

    @Nullable
    @Override
    public Value maxValue() {
        Node m = max(root);
        return m == null ? null : m.value;
    }

    @Nullable
    @Override
    public Key floor(@NotNull Key key) {
        Node x = floor(root, key);
        return x == null ? null : x.key;
    }

    @Nullable
    @Override
    public Key ceil(@NotNull Key key) {
        Node x = ceil(root, key);
        return x == null ? null : x.key;
    }

    @Override
    public int size() {
        return size;
    }

    private Node max(Node x) {
        while (x != null && x.right != null) {
            x = x.right;
        }
        return x;
    }

    private Node min(Node x) {
        while (x != null && x.left != null) {
            x = x.left;
        }
        return x;
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

    private Node fixUp(Node x) {
        if (isRed(x.right) && !isRed(x.left))
            x = rotateLeft(x);
        if (isRed(x.left) && isRed(x.left.left))
            x = rotateRight(x);
        if (isRed(x.left) && isRed(x.right))
            flipColors(x);
        return x;
    }

    private Node flipColors(Node x) {
        x.color = !x.color;
        x.left.color = !x.left.color;
        x.right.color = !x.right.color;
        return x;
    }

    private Node rotateRight(Node x) {
        Node y = x.left;
        x.left = y.right;
        y.right = x;
        y.color = x.color;
        x.color = RED;
        return y;
    }

    private Node rotateLeft(Node x) {
        Node y = x.right;
        x.right = y.left;
        y.left = x;
        y.color = x.color;
        x.color = RED;
        return y;
    }

    private boolean isRed(Node x) {
        return x != null && x.color == RED;
    }

    private boolean isBlack(Node x) {
        return !isRed(x);
    }

    private Node moveRedLeft(Node x) {
        flipColors(x);
        if (isRed(x.right.left)) {
            x.right = rotateRight(x.right);
            x = rotateLeft(x);
            flipColors(x);
        }
        return x;
    }

    private Node moveRedRight(Node x) {
        flipColors(x);
        if (isRed(x.left.left)) {
            x = rotateRight(x);
            flipColors(x);
        }
        return x;
    }

    private Node deleteMin(Node x) {
        if (x.left == null)
            return null;
        if (!isRed(x.left) && !isRed(x.left.left)) {
            x = moveRedLeft(x);
        }
        x.left = deleteMin(x.left);
        return fixUp(x);
    }

    private Node delete(Node x, Key key) {
        if (x == null) {
            return null;
        }
        if (key.compareTo(x.key) < 0) {
            if (x.left != null) {
                if (isBlack(x.left) && isBlack(x.left.left)) {
                    x = moveRedLeft(x);
                }
                x.left = delete(x.left, key);
            }
        } else {
            if (isRed(x.left)) {
                x = rotateRight(x);
                x.right = delete(x.right, key);
            } else if (key.compareTo(x.key) == 0 && x.right == null) {
                --size;
                return null;
            } else {
                if (x.right != null && isBlack(x.right) && isBlack(x.right.left)) {
                    x = moveRedRight(x);
                }
                deleteUnderInvariant(x, key);
            }
        }
        return fixUp(x);
    }

    private void deleteUnderInvariant(Node x, Key key) {
        if (key.compareTo(x.key) == 0) {
            Node min = min(x.right);
            x.key = min.key;
            x.value = min.value;
            x.right = deleteMin(x.right);
            --size;
        } else {
            x.right = delete(x.right, key);
        }
    }

    private class Node {
        Key key;
        Value value;
        Node left;
        Node right;
        boolean color;

        public Node(Key key, Value value, boolean color) {
            this.key = key;
            this.value = value;
            this.color = color;
        }
    }

}
