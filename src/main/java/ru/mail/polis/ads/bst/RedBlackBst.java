package ru.mail.polis.ads.bst;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;

/**
 * LLRB implementation of binary search tree.
 */
public class RedBlackBst<Key extends Comparable<Key>, Value>
        implements Bst<Key, Value> {

    private static final boolean BLACK = false;
    private static final boolean RED = true;

    private Node root = null;
    private int size = 0;
    private Value lastRemElem = null;

    private class Node {
        Key key;
        Value value;
        Node left;
        Node right;
        boolean color;

        public Node(Key key, Value value, Node left, Node right, boolean color) {
            this.key = key;
            this.value = value;
            this.left = left;
            this.right = right;
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
        size++;
        root.color = BLACK;
    }

    @Nullable
    @Override
    public Value remove(@NotNull Key key) {
        lastRemElem = null;
        root = delete(root, key);
        return Objects.isNull(lastRemElem) ? null : lastRemElem;
    }

    @Nullable
    @Override
    public Key min() {
        return Objects.isNull(root) ? null : min(root).key;
    }

    @Nullable
    @Override
    public Value minValue() {
        return Objects.isNull(root) ? null : min(root).value;
    }

    @Nullable
    @Override
    public Key max() {
        return Objects.isNull(root) ? null : max(root).key;
    }

    @Nullable
    @Override
    public Value maxValue() {
        return Objects.isNull(root) ? null : max(root).value;
    }

    @Nullable
    @Override
    public Key floor(@NotNull Key key) {
        Node result = floor(root, key);
        return Objects.isNull(result) ? null : result.key;
    }

    @Nullable
    @Override
    public Key ceil(@NotNull Key key) {
        Node result = ceil(root, key);
        return Objects.isNull(result) ? null : result.key;
    }

    @Override
    public int size() {
        return size;
    }

    private Node deleteMin(Node node) {
        if (Objects.isNull(node.left)) {
            return null;
        }
        if (!isRed(node.left) && !isRed(node.left.left)) {
            node = moveRedLeft(node);
        }
        node.left = deleteMin(node.left);
        return fixUp(node);
    }

    private void flipColors(Node node) {
        node.color = !node.color;
        node.left.color = !node.left.color;
        node.right.color = !node.right.color;
    }

    private Node moveRedRight(Node node) {
        flipColors(node);
        if (isRed(node.left.left)) {
            node = rotateRight(node);
            flipColors(node);
        }
        return node;
    }

    boolean isRed(Node node) {
        return !Objects.isNull(node) && node.color == RED;
    }

    private Node ceil(Node node, Key key) {
        if (Objects.isNull(node)) {
            return null;
        }
        int cmp = node.key.compareTo(key);
        if (cmp == 0) {
            return node;
        }
        if (cmp < 0) {
            return ceil(node.right, key);
        }
        Node tmp_node = ceil(node.left, key);
        return Objects.isNull(tmp_node) ? node : tmp_node;
    }

    private Node floor(Node node, Key key) {
        if (Objects.isNull(node)) {
            return null;
        }
        int cmp = node.key.compareTo(key);
        if (cmp == 0) {
            return node;
        }
        if (cmp > 0) {
            return floor(node.left, key);
        }
        Node tmp_node = floor(node.right, key);
        return Objects.isNull(tmp_node) ? node : tmp_node;
    }

    private Node max(Node x) {
        return Objects.isNull(x.right) ? x : max(x.right);
    }

    private Node min(Node x) {
        return Objects.isNull(x.left) ? x : min(x.left);
    }

    private Value get(Node node, Key key) {
        if (Objects.isNull(node)) {
            return null;
        }
        if (key.compareTo(node.key) < 0) {
            return get(node.left, key);
        }
        if (key.compareTo(node.key) > 0) {
            return get(node.right, key);
        }
        return node.value;
    }

    private Node put(Node node, Key key, Value value) {
        if (Objects.isNull(node)) {
            return new Node(key, value, null, null, RED);
        }
        int compare = key.compareTo(node.key);
        if (compare < 0) {
            node.left = put(node.left, key, value);
        } else if (compare > 0) {
            node.right = put(node.right, key, value);
        } else {
            size--;
            node.value = value;
        }
        return fixUp(node);
    }

    Node fixUp(Node node) {
        if (isRed(node.right) && !isRed(node.left)) {
            node = rotateLeft(node);
        }
        if (isRed(node.left) && isRed(node.left.left)) {
            node = rotateRight(node);
        }
        if (isRed(node.left) && isRed(node.right)) {
            flipColors(node);
        }
        return node;
    }

    private Node rotateRight(Node node2) {
        Node node1 = node2.left;
        node2.left = node1.right;
        node1.right = node2;
        node1.color = node2.color;
        node2.color = RED;
        return node1;
    }

    private Node rotateLeft(Node node1) {
        Node node2 = node1.right;
        node1.right = node2.left;
        node2.left = node1;
        node2.color = node1.color;
        node1.color = RED;
        return node2;
    }

    private Node moveRedLeft(Node node) {
        flipColors(node);
        if (isRed(node.right.left)) {
            node.right = rotateRight(node.right);
            node = rotateLeft(node);
            flipColors(node);
        }
        return node;
    }

    private Node delete(Node node, Key key) {
        if (Objects.isNull(node)) {
            return null;
        }
        int compare = key.compareTo(node.key);
        if (compare < 0) {
            if (!Objects.isNull(node.left)) {
                if (!isRed(node.left) && !isRed(node.left.left)) {
                    node = moveRedLeft(node);
                }
                node.left = delete(node.left, key);
            }
        } else {
            if (isRed(node.left)) {
                node = rotateRight(node);
                node.right = delete(node.right, key);
            } else if (compare == 0 && Objects.isNull(node.right)) {
                size--;
                lastRemElem = node.value;
                return null;
            } else {
                if (!Objects.isNull(node.right) && !isRed(node.right) && !isRed(node.right.left)) {
                    node = moveRedRight(node);
                }
                if (key.compareTo(node.key) == 0) {
                    lastRemElem = node.value;
                    Node min = min(node.right);
                    node.key = min.key;
                    node.value = min.value;
                    node.right = deleteMin(node.right);
                    size--;
                } else {
                    node.right = delete(node.right, key);
                }
            }
        }
        return fixUp(node);
    }
}

