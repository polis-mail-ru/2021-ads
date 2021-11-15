package ru.mail.polis.ads.bst;

import org.jetbrains.annotations.NotNull;

/**
 * AVL implementation of binary search tree.
 */
public class AvlBst<Key extends Comparable<Key>, Value>
        implements Bst<Key, Value> {

    private Node root = null;
    private int size = 0;
    private Value removedValue;

    private class Node {
        Key key;
        Value value;
        Node left;
        Node right;
        int height;

        Node(Key key, Value value, int height) {
            this.key = key;
            this.value = value;
            this.height = height;
        }
    }

    @Override
    public Value get(@NotNull Key key) {
        return get(root, key);
    }

    private Value get(Node x, Key key) {
        if (x == null) {
            return null;
        }
        int resultOfCompare = key.compareTo(x.key);
        if (resultOfCompare < 0) {
            return get(x.left, key);
        }
        if (resultOfCompare > 0) {
            return get(x.right, key);
        }
        return x.value;
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
        int resultOfCompare = key.compareTo(x.key);
        if (resultOfCompare < 0) {
            x.left = put(x.left, key, value);
        } else if (resultOfCompare > 0) {
            x.right = put(x.right, key, value);
        } else {
            x.value = value;
        }
        fixHeight(x);
        x = balance(x);
        return x;
    }

    private void fixHeight(Node x) {
        x.height = 1 + Math.max(height(x.left), height(x.right));
    }

    private Node balance(Node x) {
        int xFactor = factor(x);
        if (xFactor == 2) {
            if (factor(x.left) < 0) {
                x.left = rotateLeft(x.left);
            }
            return rotateRight(x);
        }
        if (xFactor == -2) {
            if (factor(x.right) > 0) {
                x.right = rotateRight(x.right);
            }
            return rotateLeft(x);
        }
        return x;
    }

    private int factor(Node x) {
        return height(x.left) - height(x.right);
    }

    private Node rotateRight(Node y) {
        Node x = y.left;
        y.left = x.right;
        x.right = y;
        fixHeight(y);
        fixHeight(x);
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

    @Override
    public Value remove(@NotNull Key key) {
        removedValue = null;
        root = delete(root, key);
        if (removedValue == null) {
            return null;
        }
        size--;
        return removedValue;
    }

    private Node delete(Node x, Key key) {
        if (x == null) {
            return null;
        }
        int resultOfCompare = key.compareTo(x.key);
        if (resultOfCompare < 0) {
            x.left = delete(x.left, key);
        } else if (resultOfCompare > 0) {
            x.right = delete(x.right, key);
        } else {
            removedValue = x.value;
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

    private Node min(Node x) {
        if (x.left == null) {
            return x;
        }
        return min(x.left);
    }

    @Override
    public Key min() {
        return (root != null) ? min(root).key : null;
    }

    @Override
    public Value minValue() {
        return (root != null) ? min(root).value : null;
    }

    @Override
    public Key max() {
        return (root != null) ? max(root).key : null;
    }

    private Node max(Node x) {
        if (x.right == null) {
            return x;
        }
        return max(x.right);
    }

    @Override
    public Value maxValue() {
        return (root != null) ? max(root).value : null;    }

    @Override
    public Key floor(@NotNull Key key) {
        Node result = floor(root, key);
        return (result != null) ? result.key : null;
    }

    private Node floor(Node x, Key key) {
        if (x == null) {
            return null;
        }
        int resultOfCompare = x.key.compareTo(key);
        if (resultOfCompare == 0) {
            return x;
        }
        if (resultOfCompare > 0) {
            return floor(x.left, key);
        }
        Node result = floor(x.right, key);
        if (result == null || key.compareTo(result.key) < 0) {
            return x;
        }
        return result;
    }

    @Override
    public Key ceil(@NotNull Key key) {
        Node result = ceil(root, key);
        return (result != null) ? result.key : null;
    }

    private Node ceil(Node x, Key key) {
        if (x == null) {
            return null;
        }
        int resultOfCompare = x.key.compareTo(key);
        if (resultOfCompare == 0) {
            return x;
        }
        if (resultOfCompare < 0) {
            return ceil(x.right, key);
        }
        Node result = ceil(x.left, key);
        if (result == null || key.compareTo(result.key) > 0) {
            return x;
        }
        return result;
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
}
