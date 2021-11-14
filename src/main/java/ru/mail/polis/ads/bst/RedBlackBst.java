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
    private int size = 0;
    private Node root;

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
        Node res = findNode(root, key);
        return (res == null) ? null : res.value;
    }

    private Node findNode(Node start, Key key) {
        if (start == null) {
            return null;
        }
        int res = start.key.compareTo(key);
        if (res > 0) {
            return findNode(start.left, key);
        } else if (res < 0) {
            return findNode(start.right, key);
        }
        return start;
    }

    @Override
    public void put(@NotNull Key key, @NotNull Value value) {
        root = put(root, key, value);
        root.color = BLACK;
    }

    private Node put(Node current, Key key, Value value) {
        if (current == null) {
            size++;
            return new Node(key, value, RED);
        }
        int res = current.key.compareTo(key);
        if (res > 0) {
            current.left = put(current.left, key, value);
        } else if (res < 0) {
            current.right = put(current.right, key, value);
        } else {
            current.value = value;
        }
        return fix(current);
    }

    private Node fix(Node node) {
        if (!isRed(node.left) && isRed(node.right)) {
            node = rotateLeft(node);
        }
        if (isRed(node.left) && isRed(node.left.left)) {
            node = rotateRight(node);
        }
        if (isRed(node.left) && isRed(node.right)) {
            flipColours(node);
        }
        return node;
    }

    private Node flipColours(Node node) {
        node.color = !node.color;
        node.left.color = !node.left.color;
        node.right.color = !node.right.color;
        return node;
    }

    private Node rotateRight(Node y) {
        Node x = y.left;
        y.left = x.right;
        x.right = y;
        x.color = y.color;
        y.color = RED;
        return x;
    }

    private boolean isRed(Node node) {
        return node != null && node.color;
    }

    private Node rotateLeft(Node x) {
        Node y = x.right;
        x.right = y.left;
        y.left = x;
        y.color = x.color;
        x.color = RED;
        return y;
    }

    @Nullable
    @Override
    public Value remove(@NotNull Key key) {
        Node node = findNode(root, key);
        if (node == null) {
            return null;
        }
        Value value = node.value;
        root = delete(root, key);
        size--;
        return value;
    }

    private Node moveRedLeft(Node x) {
        flipColours(x);
        if (isRed(x.right.left)) {
            x.right = rotateRight(x.right);
            x = rotateLeft(x);
            flipColours(x);
        }
        return x;
    }

    private Node moveRedRight(Node x) {
        flipColours(x);
        if (isRed(x.left.left)) {
            x = rotateRight(x);
            flipColours(x);
        }
        return x;
    }

    private Node deleteMin(Node start) {
        if (start.left == null) {
            return null;
        }
        if (!isRed(start.left) && !isRed(start.left.left)) {
            moveRedLeft(start);
        }
        start.left = deleteMin(start.left);
        return fix(start);
    }

    private Node delete(Node current, Key key) {
        if (current == null) {
            return null;
        }
        int res = current.key.compareTo(key);
        if (res > 0) {
            if (current.left != null) {
                if (!isRed(current.left) && !isRed(current.left.left)) {
                    current = moveRedLeft(current);
                }
                current.left = delete(current.left, key);
            }
        } else {
            if (isRed(current.left)) {
                current = rotateRight(current);
                current.right = delete(current.right, key);
            } else if (res == 0 && current.right == null) {
                return null;
            } else {
                if (current.right != null && !isRed(current.right) && !isRed(current.right.left)) {
                    current = moveRedRight(current);
                }
                if (current.key == key) {
                    Node min = minNode(current.right);
                    current.key = min.key;
                    current.value = min.value;
                    current.right = deleteMin(current.right);
                } else {
                    current.right = delete(current.right, key);
                }
            }
        }
        return fix(current);
    }

    private Node minNode(Node x) {
        if (x == null) {
            return null;
        }
        if (x.left == null) {
            return x;
        }
        return minNode(x.left);
    }

    @Nullable
    @Override
    public Key min() {
        Node res = minNode(root);
        return (res == null) ? null : res.key;
    }

    @Nullable
    @Override
    public Value minValue() {
        Node min = minNode(root);
        return (min == null) ? null : min.value;
    }

    @Nullable
    @Override
    public Key max() {
        Node res = maxNode(root);
        return (res == null) ? null : res.key;
    }

    private Node maxNode(Node current) {
        if (current == null) {
            return null;
        }
        if (current.right == null) {
            return current;
        }
        return maxNode(current.right);
    }

    @Nullable
    @Override
    public Value maxValue() {
        Node res = maxNode(root);
        return (res == null) ? null : res.value;
    }

    @Nullable
    @Override
    public Key floor(@NotNull Key key) {
        Node floor = floor(root, key);
        return (floor == null) ? null : floor.key;
    }

    private Node floor(Node start, Key key) {
        if (start == null) {
            return null;
        }
        int result = start.key.compareTo(key);
        if (result > 0) {
            Node next = floor(start.left, key);
            if (next == null) {
                return (start.key.compareTo(key) < 0) ? start : null;
            }
            return next;
        }
        if (result < 0) {
            Node next = floor(start.right, key);
            if (next == null) {
                return (start.key.compareTo(key) < 0) ? start : null;
            }
            return next;
        }
        return start;
    }

    @Nullable
    @Override
    public Key ceil(@NotNull Key key) {
        Node node = ceil(root, key);
        return (node == null) ? null : node.key;
    }

    private Node ceil(Node start, Key key) {
        if (start == null) {
            return null;
        }
        int result = start.key.compareTo(key);
        if (result > 0) {
            Node next = ceil(start.left, key);
            if (next == null) {
                return (start.key.compareTo(key) > 0) ? start : null;
            }
            return next;
        }
        if (result < 0) {
            Node next = ceil(start.right, key);
            if (next == null) {
                return (start.key.compareTo(key) > 0) ? start : null;
            }
            return next;
        }
        return start;
    }

    @Override
    public int size() {
        return size;
    }
}
