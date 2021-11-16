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

    private Node root;
    private int size = 0;

    private class Node {
        Key key;
        Value value;
        Node left;
        Node right;
        boolean color;

        Node(Key key, Value value, boolean color) {
            this.key = key;
            this.value = value;
            this.left = null;
            this.right = null;
            this.color = color;
        }
    }

    @Nullable
    @Override
    public Value get(@NotNull Key key) {
        Node keyNode = get(root, key);
        return keyNode == null ? null : keyNode.value;
    }

    @Override
    public void put(@NotNull Key key, @NotNull Value value) {
        root = put(root, key, value);
        root.color = BLACK;
    }

    @Nullable
    @Override
    public Value remove(@NotNull Key key) {
        final Node removedNode = get(root, key);
        if (removedNode == null) {
            return null;
        }
        Value removed = removedNode.value;

        root = remove(root, key);
        size--;

        return removed;
    }

    @Nullable
    @Override
    public Key min() {
        if (size == 0) {
            return null;
        }

        return min(root).key;
    }

    @Nullable
    @Override
    public Value minValue() {
        if (size == 0) {
            return null;
        }

        return min(root).value;
    }

    @Nullable
    @Override
    public Key max() {
        if (size == 0) {
            return null;
        }

        return max(root).key;
    }

    @Nullable
    @Override
    public Value maxValue() {
        if (size == 0) {
            return null;
        }

        return max(root).value;
    }

    @Nullable
    @Override
    public Key floor(@NotNull Key key) {
        Node resultNode = floor(root, key);
        return resultNode == null ? null : resultNode.key;
    }

    @Nullable
    @Override
    public Key ceil(@NotNull Key key) {
        Node resultNode = ceil(root, key);
        return resultNode == null ? null : resultNode.key;
    }

    @Override
    public int size() {
        return size;
    }

    private Node get(Node node, Key key) {
        if (node == null) {
            return null;
        }

        if (node.key.compareTo(key) > 0) {
            return get(node.left, key);
        }
        if (node.key.compareTo(key) < 0) {
            return get(node.right, key);
        }

        return node;
    }

    private Node put(Node node, Key key, Value value) {
        if (node == null) {
            size++;
            return new Node(key, value, RED);
        }

        if (node.key.compareTo(key) > 0) {
            node.left = put(node.left, key, value);
        } else if (node.key.compareTo(key) < 0) {
            node.right = put(node.right, key, value);
        } else {
            node.value = value;
        }

        return fix(node);
    }

    private Node remove(Node node, Key key) {
        if (node == null) {
            return null;
        }

        if (node.key.compareTo(key) > 0) {
            if (node.left != null) {
                if (!isRed(node.left) && !isRed(node.left.left)) {
                    node = moveRedLeft(node);
                }

                node.left = remove(node.left, key);
            }
        } else {
            if (isRed(node.left)) {
                node = rotateRight(node);
                node.right = remove(node.right, key);
            } else if (node.key.compareTo(key) == 0 && node.right == null) {
                return null;
            } else {
                if (node.right != null && !isRed(node.right) && !isRed(node.right.left)) {
                    node = moveRedRight(node);
                }
                if (node.key.compareTo(key) == 0) {
                    Node min = min(node.right);
                    node.key = min.key;
                    node.value = min.value;
                    node.right = removeMin(node.right);
                } else {
                    node.right = remove(node.right, key);
                }
            }
        }

        return fix(node);
    }

    private Node removeMin(Node node) {
        if (node.left == null) {
            return null;
        }

        if (!isRed(node.left) && !isRed(node.left.left)) {
            node = moveRedLeft(node);
        }

        node.left = removeMin(node.left);
        return fix(node);
    }

    private Node fix(Node node) {
        if (!isRed(node.left) && isRed(node.right)) {
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
        return node != null && node.color;
    }

    private Node rotateLeft(Node node) {
        Node rightNode = node.right;
        node.right = rightNode.left;
        rightNode.left = node;
        rightNode.color = node.color;
        node.color = RED;
        return rightNode;
    }

    private Node rotateRight(Node node) {
        Node leftNode = node.left;
        node.left = leftNode.right;
        leftNode.right = node;
        leftNode.color = node.color;
        node.color = RED;
        return leftNode;
    }

    private Node flipColors(Node node) {
        node.color = !node.color;
        node.left.color = !node.left.color;
        node.right.color = !node.right.color;
        return node;
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

    private Node min(Node node) {
        return node.left == null ? node : min(node.left);
    }

    private Node max(Node node) {
        return node.right == null ? node : max(node.right);
    }

    private Node floor(Node node, Key key) {
        if (node == null) {
            return null;
        }

        if (node.key.compareTo(key) == 0) {
            return node;
        }
        if (node.key.compareTo(key) > 0) {
            return floor(node.left, key);
        }

        Node rightFloorNode = floor(node.right, key);

        return rightFloorNode != null ? rightFloorNode : node;
    }

    private Node ceil(Node node, Key key) {
        if (node == null) {
            return null;
        }

        if (node.key.compareTo(key) == 0) {
            return node;
        }
        if (node.key.compareTo(key) < 0) {
            return ceil(node.right, key);
        }

        Node leftCeilNode = ceil(node.left, key);

        return leftCeilNode != null ? leftCeilNode : node;
    }
}
