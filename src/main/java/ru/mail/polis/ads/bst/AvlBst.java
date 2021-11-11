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

    @Override
    public Value get(@NotNull Key key) {
        return get(root, key);
    }

    @Override
    public void put(@NotNull Key key, @NotNull Value value) {
        root = put(root, key, value);
    }

    @Override
    public Value remove(@NotNull Key key) {
        Value result = get(root, key);
        if (result == null) {
            return null;
        }
        root = delete(root, key);
        return result;
    }

    @Override
    public Key min() {
        return root == null ? null : min(root).key;
    }

    @Override
    public Value minValue() {
        return root == null ? null : min(root).value;
    }

    @Override
    public Key max() {
        return root == null ? null : max(root).key;
    }

    @Override
    public Value maxValue() {
        return root == null ? null : max(root).value;
    }

    @Override
    public Key floor(@NotNull Key key) {
        return floor(root, key);
    }

    @Override
    public Key ceil(@NotNull Key key) {
        return ceil(root, key);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public int height() {
        return height(root);
    }

    private int height(Node node) {
        return node == null ? 0 : node.height;
    }

    private void fixHeight(Node node) {
        node.height = 1 + Math.max(height(node.left), height(node.right));
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

    private Node min(Node node) {
        if (node.left == null) {
            return node;
        }
        return min(node.left);
    }

    private Node max(Node node) {
        if (node.right == null) {
            return node;
        }
        return max(node.right);
    }

    private Node deleteMin(Node x) {
        if (x.left == null) {
            return x.right;
        }
        x.left = deleteMin(x.left);
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
        fixHeight(x);
        x = balance(x);
        return x;
    }

    private Node delete(Node x, Key key) {
        if (x == null) {
            return null;
        }
        if (key.compareTo(x.key) < 0) {
            x.left = delete(x.left, key);
        } else if (key.compareTo(x.key) > 0) {
            x.right = delete(x.right, key);
        } else {
            x = innerDelete(x);
            size--;
        }
        if (x != null) {
            fixHeight(x);
            x = balance(x);
        }
        return x;
    }

    private Key ceil(Node node, Key key) {
        if (node == null) {
            return null;
        }
        if (node.key.compareTo(key) == 0) {
            return key;
        }
        if (node.key.compareTo(key) < 0) {
            return ceil(node.right, key);
        }
        Key ceil = ceil(node.left, key);
        return ceil == null ? node.key : ceil;
    }

    private Key floor(Node node, Key key) {
        if (node == null) {
            return null;
        }
        if (node.key.compareTo(key) == 0) {
            return key;
        }
        if (node.key.compareTo(key) > 0) {
            return floor(node.left, key);
        }
        Key floor = floor(node.right, key);
        return floor == null ? node.key : floor;
    }
}
