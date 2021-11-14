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

        Node(Key key, Value value, int height) {
            this.key = key;
            this.value = value;
            this.height = height;
        }
    }

    @Override
    public Value get(@NotNull Key key) {
        Node tmp = get(root, key);
        return tmp == null ? null : tmp.value;
    }

    private Node get(Node x, Key key) {
        if (x == null) {
            return null;
        }
        if (key.compareTo(x.key) < 0) {
            return get(x.left, key);
        }
        if (key.compareTo(x.key) > 0) {
            return get(x.right, key);
        }
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

    @Override
    public Value remove(@NotNull Key key) {
        if (size == 0 || !containsKey(key)) {
            return null;
        }
        Node tmp = get(root, key);
        root = delete(root, key);
        size--;
        return tmp.value;
    }

    private Node deleteMin(Node x) {
        if (x.left == null) {
            return x.right;
        }
        x.left = deleteMin(x.left);
        return x;
    }

    private Node delete(Node x, Key key) {
        if (x == null) {
            return null;
        }
        if (key.compareTo(x.key) < 0) {
            x.left = delete(x.left, key);
        }
        if (key.compareTo(x.key) > 0) {
            x.right = delete(x.right, key);
        }
        if (key == x.key) {
            x = innerDelete(x);
        }
        fixHeight(x);
        x = balance(x);
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

    @Override
    public Key min() {
        if (size == 0) {
            return null;
        }
        Node tmp = min(root);
        return tmp == null ? null : tmp.key;
    }

    @Override
    public Value minValue() {
        if (size == 0) {
            return null;
        }
        Node tmp = min(root);
        return tmp == null ? null : tmp.value;
    }

    private Node min(Node x) {
        if (x.left == null) {
            return x;
        }
        return min(x.left);
    }

    @Override
    public Key max() {
        if (size == 0) {
            return null;
        }
        Node tmp = max(root);
        return tmp == null ? null : tmp.key;
    }

    @Override
    public Value maxValue() {
        if (size == 0) {
            return null;
        }
        Node tmp = max(root);
        return tmp == null ? null : tmp.value;
    }

    private Node max(Node x) {
        if (x.right == null) {
            return x;
        }
        return max(x.right);
    }

    @Override
    public Key floor(@NotNull Key key) {
        if (size == 0 || key.compareTo(min()) < 0) {
            return null;
        }
        if (containsKey(key)) {
            return key;
        }
        return floor(root, key).key;
    }

    private Node floor(Node x, Key key) {
        if (key.compareTo(x.key) < 0) {
            if (key.compareTo(x.left.key) > 0) {
                return x.left;
            }
            return floor(x.left, key);
        }
        if (key.compareTo(x.key) > 0) {
            if (x.right == null || key.compareTo(x.right.key) < 0) {
                return x;
            }
            return floor(x.right, key);
        }
        return x;
    }

    @Override
    public Key ceil(@NotNull Key key) {
        if (size == 0 || key.compareTo(max()) > 0) {
            return null;
        }
        if (containsKey(key)) {
            return key;
        }
        return ceil(root, key).key;
    }

    private Node ceil(Node x, Key key) {
        if (key.compareTo(x.key) < 0) {
            if (x.left == null || key.compareTo(x.left.key) > 0) {
                return x;
            }
            return ceil(x.left, key);
        }
        if (key.compareTo(x.key) > 0) {
            if (key.compareTo(x.right.key) < 0) {
                return x.right;
            }
            return ceil(x.right, key);
        }
        return x;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public int height() {
        if (size == 0) {
            return 0;
        }
        return height(root);
    }

    public int height(Node x) {
        return x == null ? 0 : x.height;
    }

    private void fixHeight(Node x) {
        if (x == null) {
            return;
        }
        x.height = 1 + Math.max(height(x.left), height(x.right));
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

    private int factor(Node x) {
        return height(x.left) - height(x.right);
    }

    private Node balance(Node x) {
        if (x == null) {
            return null;
        }
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
