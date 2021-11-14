package ru.mail.polis.ads.bst;

import java.util.Objects;

import org.jetbrains.annotations.NotNull;

/**
 * AVL implementation of binary search tree.
 */
public class AvlBst<Key extends Comparable<Key>, Value>
        implements Bst<Key, Value> {

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

    private int size;
    private Node root;

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
        Value removedNodeValue = get(key);
        root = remove(root, key);
        return removedNodeValue;
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
        if (key.equals(x.key)) {
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
        Node temp = x;
        x = min(temp.right);
        x.right = deleteMin(temp.right);
        x.left = temp.left;
        return x;
    }

    private Node max(Node x) {
        if (x == null) {
            return null;
        }
        Node temp = x;
        while ((temp != null) && (Objects.requireNonNull(temp).right != null)) {
            temp = temp.right;
        }
        return temp;
    }

    Node deleteMin(Node x) {
        if (x.left == null) {
            return x.right;
        }
        x.left = deleteMin(x.left);
        return x;
    }

    @Override
    public Key min() {
        Node x = min(root);
        return (x != null) ? x.key : null;
    }

    private Node min(Node x) {
        if (x == null) {
            return null;
        }
        Node temp = x;
        while ((temp != null) && (Objects.requireNonNull(temp).left != null)) {
            temp = temp.left;
        }
        return temp;
    }

    @Override
    public Value minValue() {
        Node x = min(root);
        return (x != null) ? x.value : null;
    }

    @Override
    public Key max() {
        Node x = max(root);
        return (x != null) ? x.key : null;
    }

    @Override
    public Value maxValue() {
        Node x = max(root);
        return (x != null) ? x.value : null;
    }

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
        return size;
    }

    @Override
    public int height() {
        return height(root);
    }

    private int height(Node x) {
        return (x != null) ? x.height : 0;
    }

    private void fixHeight(Node x) {
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

    private Node rotateLeft(Node y) {
        Node x = y.right;
        y.right = x.left;
        x.left = y;
        fixHeight(x);
        fixHeight(y);
        return x;
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

    private int factor(Node x) {
        if (x == null) {
            return 0;
        }
        return height(x.left) - height(x.right);
    }

}
