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

    private Node root = null;
    private int size;
    private Value removed = null;

    private class Node {
        Key key;
        Value value;
        Node left;
        Node right;
        boolean color;

        private Node(Key key, Value value, boolean color) {
            this.key = key;
            this.value = value;
            this.color = color;
        }
    }

    @Nullable
    @Override
    public Value get(@NotNull Key key) {
        return get(root, key);
    }

    @Override
    public void put(@NotNull Key key, @NotNull Value value) {
        root = put(root, key, value);
        root.color = BLACK;
    }

    @Nullable
    @Override
    public Value remove(@NotNull Key key) {
        removed = null;
        root = delete(root, key);
        if (removed == null) {
            return null;
        }
        return removed;
    }

    @Nullable
    @Override
    public Key min() {
        return (root == null) ? null : min(root).key;
    }

    @Nullable
    @Override
    public Value minValue() {
        return (root == null) ? null : min(root).value;
    }

    @Nullable
    @Override
    public Key max() {
        return (root == null) ? null : max(root).key;
    }

    @Nullable
    @Override
    public Value maxValue() {
        return (root == null) ? null : max(root).value;
    }

    @Nullable
    @Override
    public Key floor(@NotNull Key key) {
        Node res = floor(root, key);
        return (res == null) ? null : res.key;
    }

    @Nullable
    @Override
    public Key ceil(@NotNull Key key) {
        Node res = ceil(root, key);
        return (res == null) ? null : res.key;
    }

    @Override
    public int size() {
        return size;
    }

    private Node floor(Node node, Key key) {
        if (node == null) {
            return null;
        }

        int compare = node.key.compareTo(key);
        if (compare == 0) {
            return node;
        }

        if (compare > 0) {
            return floor(node.left, key);
        }

        Node rightFloor = floor(node.right, key);
        if (rightFloor == null || key.compareTo(rightFloor.key) < 0) {
            return node;
        }
        return rightFloor;
    }

    private Node ceil(Node node, Key key) {
        if (node == null) {
            return null;
        }

        int compare = node.key.compareTo(key);
        if (compare == 0) {
            return node;
        }

        if (compare < 0) {
            return ceil(node.right, key);
        }

        Node leftCeil = ceil(node.left, key);
        if (leftCeil == null || key.compareTo(leftCeil.key) > 0) {
            return node;
        }
        return leftCeil;
    }

    private Node min(Node x) {
        if (x.left == null) {
            return x;
        }
        return min(x.left);
    }

    private Node max(Node x) {
        if (x.right == null) {
            return x;
        }
        return max(x.right);
    }

    private Node delete(Node x, Key key) {
        if (x == null) {
            return null;
        }

        int compare = key.compareTo(x.key);
        if (compare < 0) {
            if (x.left != null) {
                if (!isRed(x.left) && !isRed(x.left.left)) {
                    x = moveRedLeft(x);
                }
                x.left = delete(x.left, key);
            }
        } else {
            if (isRed(x.left)) {
                x = rotateRight(x);
                x.right = delete(x.right, key);
            } else if (compare == 0 && x.right == null) {
                removed = x.value;
                size--;
                return null;
            } else {
                if (x.right != null && !isRed(x.right) && !isRed(x.right.left)) {
                    x = moveRedRight(x);
                }
                if (key.compareTo(x.key) == 0) {
                    removed = x.value;
                    size--;
                    Node min = min(x.right);
                    x.key = min.key;
                    x.value = min.value;
                    x.right = deleteMin(x.right);
                } else {
                    x.right = delete(x.right, key);
                }
            }
        }

        return fixUp(x);
    }

    private Node deleteMin(Node x) {
        if (x.left == null) {
            return null;
        }
        if (!isRed(x.left) && !isRed(x.left.left)) {
            x = moveRedLeft(x);
        }
        x.left = deleteMin(x.left);
        return fixUp(x);
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

    private boolean isRed(Node x) {
        return x != null && x.color == RED;
    }

    private Node rotateLeft(Node x) {
        Node y = x.right;
        x.right = y.left;
        y.left = x;
        y.color = x.color;
        x.color = RED;
        return y;
    }

    private Node rotateRight(Node y) {
        Node x = y.left;
        y.left = x.right;
        x.right = y;
        x.color = y.color;
        y.color = RED;
        return x;
    }

    private Node flipColors(Node x) {
        x.color = !x.color;
        x.left.color = !x.left.color;
        x.right.color = !x.right.color;
        return x;
    }

    private Node put(Node x, Key key, Value value) {
        if (x == null) {
            size++;
            return new Node(key, value, RED);
        }

        int compare = key.compareTo(x.key);
        if (compare < 0) {
            x.left = put(x.left, key, value);
        } else if (compare > 0) {
            x.right = put(x.right, key, value);
        } else {
            x.value = value;
        }

        return fixUp(x);
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
}
