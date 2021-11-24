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
    private Value lastRemoved = null;
    private Node root = null;
    private int size = 0;

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

    @Nullable
    @Override
    public Value get(@NotNull Key key) {
        return findValue(key);
    }

    @Override
    public void put(@NotNull Key key, @NotNull Value value) {
        size++;
        root = putElement(root, key, value);
    }

    @Nullable
    @Override
    public Value remove(@NotNull Key key) {
        root = deleteElement(root, key);
        size = lastRemoved == null ? size : --size;
        return lastRemoved;
    }

    @Nullable
    @Override
    public Key min() {
        if (root == null) {
            return null;
        }
        return findMin(root).key;
    }

    @Nullable
    @Override
    public Value minValue() {
        if (root == null) {
            return null;
        }
        return findMin(root).value;
    }

    @Nullable
    @Override
    public Key max() {
        if (root == null) {
            return null;
        }
        return findMax().key;
    }

    @Nullable
    @Override
    public Value maxValue() {
        if (root == null) {
            return null;
        }
        return findMax().value;

    }

    @Nullable
    @Override
    public Key floor(@NotNull Key key) {
        Node result = findFloor(root, key);
        return result == null ? null : result.key;
    }

    @Nullable
    @Override
    public Key ceil(@NotNull Key key) {
        Node result = findCeil(root, key);
        return result == null ? null : result.key;
    }

    @Override
    public int size() {
        return size;
    }

    private Node findMin(Node from) {
        Node current = from;
        while (current.left != null) {
            current = current.left;
        }
        return current;
    }

    private Node findMax() {
        Node current = root;
        while (current.right != null) {
            current = current.right;
        }
        return current;
    }

    private Node findFloor(Node start, Key key) {
        if (start == null) {
            return null;
        }

        int compare = key.compareTo(start.key);
        if (compare == 0) {
            return start;
        }
        if (compare < 0) {
            return findFloor(start.left, key);
        }

        Node part = findFloor(start.right, key);
        return part == null ? start : part;
    }

    private Node findCeil(Node start, Key key) {
        if (start == null) {
            return null;
        }

        int compare = key.compareTo(start.key);
        if (compare == 0) {
            return start;
        }

        if (compare > 0) {
            return findCeil(start.right, key);
        }

        Node part = findCeil(start.left, key);
        return part == null ? start : part;
    }

    private Node putElement(Node node, Key key, Value value) {
        if (node == null) {
            return new Node(key, value, RED);
        }

        int compare = node.key.compareTo(key);
        if (compare > 0) {
            node.left = putElement(node.left, key, value);
        } else if (compare < 0) {
            node.right = putElement(node.right, key, value);
        } else {
            size--;
            node.value = value;
        }
        return fixUp(node);
    }

    private Node fixUp(Node node) {
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

    private boolean isRed(Node node) {
        return node != null && node.color == RED;
    }

    private void flipColors(Node node) {
        node.color = !node.color;
        node.left.color = !node.left.color;
        node.right.color = !node.right.color;
    }

    private Node rotateLeft(Node node) {
        Node current = node.right;
        node.right = current.left;
        current.left = node;
        current.color = node.color;
        node.color = RED;
        return current;
    }

    private Node rotateRight(Node node) {
        Node current = node.left;
        node.left = current.right;
        current.right = node;
        current.color = node.color;
        node.color = RED;
        return current;
    }

    private Value findValue(Key key) {
        Node current = root;
        int compare;
        while (current != null) {
            compare = current.key.compareTo(key);
            if (compare > 0) {
                current = current.left;
            } else if (compare < 0) {
                current = current.right;
            } else {
                return current.value;
            }
        }
        return null;
    }

    private Node deleteElement(Node node, Key key) {
        if (node == null) {
            lastRemoved = null;
            return null;
        }

        int current = key.compareTo(node.key);
        if (current < 0) {
            if (node.left != null) {
                if (!isRed(node.left) && !isRed(node.left.left)) {
                    node = moveRedLeft(node);
                }
            }
            node.left = deleteElement(node.left, key);
            return fixUp(node);
        } else {
            if (isRed(node.left)) {
                node = rotateRight(node);
                node.right = deleteElement(node.right, key);
                return fixUp(node);
            }

            if (node.key == key && node.right == null) {
                lastRemoved = node.value;
                return null;
            }

            if (node.right != null && !isRed(node.right) && !isRed(node.right.left)) {
                node = moveRedRight(node);
            }

            if (node.key == key) {
                lastRemoved = node.value;
                Node minNode = findMin(node.right);
                node.key = minNode.key;
                node.value = minNode.value;
                node.right = deleteMin(node.right);
            } else {
                node.right = deleteElement(node.right, key);
            }
        }
        return fixUp(node);
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

    private Node moveRedRight(Node node) {
        flipColors(node);
        if (isRed(node.left.left)) {
            node = rotateRight(node);
            flipColors(node);
        }
        return node;
    }

    private Node deleteMin(Node node) {
        if (node.left == null) {
            return null;
        }

        if (!isRed(node.left) && !isRed(node.left.left)) {
            node = moveRedLeft(node);
        }

        node.left = deleteMin(node.left);
        return fixUp(node);
    }
}
