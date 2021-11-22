package ru.mail.polis.ads.bst;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * LLRB implementation of binary search tree.
 */
public class RedBlackBst<Key extends Comparable<Key>, Value> implements Bst<Key, Value> {
    private static final boolean BLACK = false;
    private static final boolean RED = true;

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

    Node root;

    @Nullable
    @Override
    public Value get(@NotNull Key key) {
        return get(root, key);
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

    @Override
    public void put(@NotNull Key key, @NotNull Value value) {
        root = put(root, key, value);
        root.color = BLACK;
    }

    private Node put(Node x, Key key, Value value) {
        if (x == null) {
            return new Node(key, value, RED);
        }
        if (key.compareTo(x.key) < 0) {
            x.left = put(x.left, key, value);
        } else if (key.compareTo(x.key) > 0) {
            x.right = put(x.right, key, value);
        } else {
            x.value = value;
        }
        return fixUp(x);
    }

    @Nullable
    @Override
    public Value remove(@NotNull Key key) {
        Value removedValue = get(key);
        if (removedValue == null) {
            return null;
        }
        root = remove(root, key);
        return removedValue;
    }

    private Node remove(Node x, Key key) {
        if (x == null) {
            return null;
        }
        if (key.compareTo(x.key) < 0) {
            if (x.left != null) {
                if (!isRed(x.left) && !isRed(x.left.left)) {
                    x = moveRedLeft(x);
                }
            }
            x.left = remove(x.left, key);
        } else {
            if (isRed(x.left)) {
                x = rotateRight(x);
                x.right = remove(x.right, key);
            } else if (x.key.equals(key) && (x.right == null)) {
                return null;
            } else {
                if ((x.right != null) && !isRed(x.right) && !isRed(x.right.left)) {
                    x = moveRedRight(x);
                }
                if (x.key.equals(key)) {
                    Node min = min(x.right);
                    x.key = min.key;
                    x.value = min.value;
                    x.right = deleteMin(x.right);
                } else {
                    x.right = remove(x.right, key);
                }
            }
        }
        return fixUp(x);
    }

    Node moveRedLeft(Node x) {
        flipColors(x);
        if (isRed(x.right.left)) {
            x.right = rotateRight(x.right);
            x = rotateLeft(x);
            flipColors(x);
        }
        return x;
    }

    Node deleteMin(Node x) {
        if (x.left == null) {
            return null;
        }
        if (!isRed(x.left) && !isRed(x.left.left)) {
            x = moveRedLeft(x);
        }
        x.left = deleteMin(x.left);
        return fixUp(x);
    }

    private Node min(Node x) {
        if (x == null) {
            return null;
        }
        Node temp = x;
        while (temp.left != null) {
            temp = temp.left;
        }
        return temp;
    }

    Node moveRedRight(Node x) {
        flipColors(x);
        if (isRed(x.left.left)) {
            x = rotateRight(x);
            flipColors(x);
        }
        return x;
    }

    @Nullable
    @Override
    public Key min() {
        Node x = min(root);
        return (x != null) ? x.key : null;
    }

    @Nullable
    @Override
    public Value minValue() {
        Node x = min(root);
        return (x != null) ? x.value : null;
    }

    @Nullable
    @Override
    public Key max() {
        Node x = max(root);
        return (x != null) ? x.key : null;
    }

    private Node max(Node x) {
        if (x == null) {
            return null;
        }
        Node temp = x;
        while (temp.right != null) {
            temp = temp.right;
        }
        return temp;
    }

    @Nullable
    @Override
    public Value maxValue() {
        Node x = max(root);
        return (x != null) ? x.value : null;
    }

    @Nullable
    @Override
    public Key floor(@NotNull Key key) {
        Node x = floor(root, key);
        if (x == null) {
            return null;
        }
        return x.key;
    }

    private Node floor(Node x, Key key) {
        if (x == null) {
            return null;
        }
        if (x.key.compareTo(key) > 0) {
            return (x.left == null) ? null : floor(x.left, key);
        }
        if ((x.key.equals(key)) || (x.right == null)) {
            return x;
        }
        Node floor = floor(x.right, key);
        if ((floor == null) || (floor.key.compareTo(key) > 0)) {
            return x;
        }
        return floor;
    }

    @Nullable
    @Override
    public Key ceil(@NotNull Key key) {
        Node x = ceil(root, key);
        if (x == null) {
            return null;
        }
        return x.key;
    }

    private Node ceil(Node x, Key key) {
        if (x == null) {
            return null;
        }
        if (x.key.compareTo(key) < 0) {
            return (x.right == null) ? null : ceil(x.right, key);
        }
        if ((x.key.equals(key)) || (x.left == null)) {
            return x;
        }
        Node ceil = ceil(x.left, key);
        if ((ceil == null) || (ceil.key.compareTo(key) < 0)) {
            return x;
        }
        return ceil;
    }

    @Override
    public int size() {
        return size(root);
    }

    private int size(Node node) {
        return (node == null) ? 0 : 1 + size(node.left) + size(node.right);
    }

    boolean isRed(Node x) {
        return ((x != null) && (x.color == RED));
    }

    private Node rotateRight(Node y) {
        Node x = y.left;
        y.left = x.right;
        x.right = y;
        x.color = y.color;
        y.color = RED;
        return x;
    }

    private Node rotateLeft(Node x) {
        Node y = x.right;
        x.right = y.left;
        y.left = x;
        y.color = x.color;
        x.color = RED;
        return y;
    }

    private Node flipColors(Node x) {
        x.color = !x.color;
        x.left.color = !x.left.color;
        x.right.color = !x.right.color;
        return x;
    }

    private Node fixUp(Node x) {
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
}
