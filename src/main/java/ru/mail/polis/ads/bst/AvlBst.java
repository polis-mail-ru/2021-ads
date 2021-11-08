package ru.mail.polis.ads.bst;

import org.jetbrains.annotations.NotNull;

import java.util.Objects;

/**
 * AVL implementation of binary search tree.
 */
public class AvlBst<Key extends Comparable<Key>, Value>
        implements Bst<Key, Value> {

    private static final int BALANCE_FACTOR_VALUE = 2;
    private Node root;
    private int size;
    private Value lastRemoved;


    private class Node {
        Key key;
        Value value;
        Node left;
        Node right;
        int height;

        public Node(Key key, Value value, Node left, Node right, int height) {
            this.key = key;
            this.value = value;
            this.left = left;
            this.right = right;
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
        root = remove(root, key);
        return lastRemoved;
    }

    @Override
    public Key min() {
        return Objects.isNull(root) ? null : min(root).key;
    }

    @Override
    public Value minValue() {
        return Objects.isNull(root) ? null : min(root).value;
    }

    @Override
    public Key max() {
        return Objects.isNull(root) ? null : max(root).key;
    }

    @Override
    public Value maxValue() {
        return Objects.isNull(root) ? null : max(root).value;
    }

    @Override
    public Key floor(@NotNull Key key) {
        final Node floorKeyNode = floor(root, key);
        return Objects.isNull(floorKeyNode) ? null : floorKeyNode.key;
    }

    @Override
    public Key ceil(@NotNull Key key) {
        final Node ceilKeyNode = ceil(root, key);
        return Objects.isNull(ceilKeyNode) ? null : ceilKeyNode.key;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public int height() {
        return height(root);
    }

    private Value get(Node node, Key key) {
        if (Objects.isNull(node)) {
            return null;
        }
        final int compareResult = key.compareTo(node.key);
        if (compareResult < 0) {
            return get(node.left, key);
        }
        if (compareResult > 0) {
            return get(node.right, key);
        }
        return node.value;
    }

    private Node put(Node node, Key key, Value value) {
        if (Objects.isNull(node)) {
            size++;
            return new Node(key, value, null, null, 1);
        }
        if (key.compareTo(node.key) < 0) {
            node.left = put(node.left, key, value);
        } else if (key.compareTo(node.key) > 0) {
            node.right = put(node.right, key, value);
        } else {
            node.value = value;
        }
        fixHeight(node);
        return balance(node);
    }

    private Node deleteMin(Node node) {
        if (Objects.isNull(node.left)) {
            return node.right;
        }
        node.left = deleteMin(node.left);
        return node;
    }

    private Node remove(Node node, Key key) {
        if (Objects.isNull(node)) {
            lastRemoved = null;
            return null;
        }
        if (key.compareTo(node.key) < 0) {
            node.left = remove(node.left, key);
        } else if (key.compareTo(node.key) > 0) {
            node.right = remove(node.right, key);
        } else {
            lastRemoved = node.value;
            size--;
            node = innerDelete(node);
        }
        return node;
    }

    private Node innerDelete(Node node) {
        if (Objects.isNull(node.right)) {
            return node.left;
        }
        if (Objects.isNull(node.left)) {
            return node.right;
        }
        final Node tmp = node;
        node = min(tmp.right);
        node.right = deleteMin(tmp.right);
        node.left = tmp.left;
        return node;
    }

    private Node min(Node node) {
        if (Objects.isNull(node.left)) {
            return node;
        }
        return min(node.left);
    }

    private Node max(Node node) {
        if (Objects.isNull(node.right)) {
            return node;
        }
        return max(node.right);
    }

    private Node floor(Node node, Key key) {
        if (Objects.isNull(node)) {
            return null;
        }
        if (key.compareTo(node.key) < 0) {
            return floor(node.left, key);
        }
        if (key.compareTo(node.key) == 0) {
            return node;
        }
        final Node rightNode = floor(node.right, key);
        return Objects.isNull(rightNode) ? node : rightNode;
    }

    private Node ceil(Node node, Key key) {
        if (Objects.isNull(node)) {
            return null;
        }
        if (key.compareTo(node.key) > 0) {
            return ceil(node.right, key);
        }
        if (key.compareTo(node.key) == 0) {
            return node;
        }
        final Node leftNode = ceil(node.left, key);
        return Objects.isNull(leftNode) ? node : leftNode;
    }

    private int height(Node node) {
        return Objects.isNull(node) ? 0 : node.height;
    }

    private void fixHeight(Node node) {
        node.height = 1 + Math.max(height(node.left), height(node.right));
    }

    private Node rotateRight(Node second) {
        final Node first = second.left;
        second.left = first.right;
        first.right = second;
        fixHeight(first);
        fixHeight(second);
        return first;
    }

    private Node rotateLeft(Node first) {
        final Node second = first.right;
        first.right = second.left;
        second.left = first;
        fixHeight(first);
        fixHeight(second);
        return second;
    }

    private int factor(Node node) {
        return height(node.left) - height(node.right);
    }

    private Node balance(Node node) {
        if (factor(node) == BALANCE_FACTOR_VALUE) {
            if (factor(node.left) < 0) {
                node.left = rotateLeft(node.left);
            }
            return rotateRight(node);
        }
        if (factor(node) == -BALANCE_FACTOR_VALUE) {
            if (factor(node.right) > 0) {
                node.right = rotateRight(node.right);
            }
            return rotateLeft(node);
        }
        return node;
    }
}