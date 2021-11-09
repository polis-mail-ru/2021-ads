package ru.mail.polis.ads.bst;

import org.jetbrains.annotations.NotNull;

/**
 * AVL implementation of binary search tree.
 */
public class AvlBst<Key extends Comparable<Key>, Value>
        implements Bst<Key, Value> {

    private Node root;
    private int size;
    private Node removed;

    private class Node {
        Key key;
        Value value;
        Node left;
        Node right;
        int height;

        private Node(Key key, Value value, int height) {
            this.key = key;
            this.value = value;
            this.height = height;
        }
    }

    @Override
    public Value get(@NotNull Key key) {
        if (isEmpty()) {
            return null;
        }
        return get(root, key);
    }

    @Override
    public void put(@NotNull Key key, @NotNull Value value) {
        root = put(root, key, value);
    }

    @Override
    public Value remove(@NotNull Key key) {
        root = delete(root, key);
        if (removed == null) {
            return null;
        }
        Value deleted = removed.value;
        removed = null;
        return deleted;
    }

    @Override
    public Key min() {
        return (root == null) ? null : min(root).key;
    }

    @Override
    public Value minValue() {
        return (root == null) ? null : min(root).value;
    }

    @Override
    public Key max() {
        return (root == null) ? null : max(root).key;
    }

    @Override
    public Value maxValue() {
        return (root == null) ? null : max(root).value;
    }

    @Override
    public Key floor(@NotNull Key key) {
        Node node = floor(root, key);
        return (node != null) ? node.key : null;
    }

    @Override
    public Key ceil(@NotNull Key key) {
        Node node = ceil(root, key);
        return (node != null) ? node.key : null;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public int height() {
        return height(root);
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

    private Node put(Node x, Key key, Value value) {
        if (x == null) {
            size++;
            return new Node(key, value, 1);
        }
        int compareKey = key.compareTo(x.key);
        if (compareKey < 0) {
            x.left = put(x.left, key, value);
        } else if (compareKey > 0) {
            x.right = put(x.right, key, value);
        } else {
            x.value = value;
        }
        fixHeight(x);
        x = balance(x);
        return x;
    }

    private Node min(Node x) {
        return x.left == null ? x : min(x.left);
    }

    private Node max(Node x) {
        return x.right == null ? x : max(x.right);
    }

    private Node floor(Node x, Key key) {
        if (x == null) {
            return null;
        }
        int cmp = key.compareTo(x.key);
        if (cmp == 0) {
            return x;
        }
        if (cmp < 0) {
            return floor(x.left, key);
        }
        Node y = floor(x.right, key);
        return y != null ? y : x;
    }

    private Node ceil(Node x, Key key) {
        if (x == null) {
            return null;
        }
        int cmp = key.compareTo(x.key);
        if (cmp == 0) {
            return x;
        }
        if (cmp > 0) {
            return ceil(x.right, key);
        }
        Node y = ceil(x.left, key);
        return y != null ? y : x;
    }

    private Node delete(Node x, Key key) {
        if (x == null) {
            return null;
        }
        int compareRes = key.compareTo(x.key);
        if (compareRes < 0) {
            x.left = delete(x.left, key);
        } else if (compareRes > 0) {
            x.right = delete(x.right, key);
        } else {
            removed = x;
            size--;
            x = innerDelete(x);
        }
        return x;
    }

    private Node innerDelete(Node x) {
        if (x.right == null) {
            return x.left;
        }
        if (x.left == null) {
            return x.right;
        }
        Node t = x;
        x = min(t.right);
        x.right = deleteMin(t.right);
        x.left = t.left;
        return x;
    }

    private Node deleteMin(Node x) {
        if (x.left == null) {
            return x.right;
        }
        x.left = deleteMin(x.left);
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

    private Node rotateRight(Node y) {
        Node x = y.left;
        y.left = x.right;
        x.right = y;
        fixHeight(y);
        fixHeight(x);
        return x;
    }

    private int height(Node node) {
        return node == null ? 0 : node.height;
    }

    private void fixHeight(Node x) {
        x.height = 1 + Math.max(height(x.left), height(x.right));
    }

    private int factor(Node x) {
        return height(x.left) - height(x.right);
    }

    private Node balance(Node x) {
        if (factor(x) == 2) {
            if (factor(x.left) < 0) {
                x.left = rotateLeft(x.left);
            }
            return rotateRight(x);
        }
        if (factor(x) == -2) {
            if (factor(x.right) > 0) {
                x.right = rotateRight(x.right);
            }
            return rotateLeft(x);
        }
        return x;
    }
}
