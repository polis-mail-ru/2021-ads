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

    private Node root = null;
    private int size;
    private Value justNowDeleted;

    private class Node {
        Key key;
        Value value;
        Node left;
        Node right;
        boolean color;

        Node(Key key, Value value, boolean color) {
            this.key = key;
            this.value = value;
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
        root.color = BLACK;
    }

    @Nullable
    @Override
    public Value remove(@NotNull Key key) {
        justNowDeleted = null;
        root = delete(root, key);
        if (justNowDeleted != null) {
            size--;
        }
        return justNowDeleted;
    }

    @Nullable
    @Override
    public Key min() {
        return root == null ? null : getMinNode(root).key;
    }

    @Nullable
    @Override
    public Value minValue() {
        return root == null ? null : getMinNode(root).value;
    }

    @Nullable
    @Override
    public Key max() {
        return root == null ? null : getMaxNode(root).key;
    }

    @Nullable
    @Override
    public Value maxValue() {
        return root == null ? null : getMaxNode(root).value;
    }

    @Nullable
    @Override
    public Key floor(@NotNull Key key) {
        Node flNode = getFloor(root, key);
        return flNode != null ? flNode.key : null;
    }

    @Nullable
    @Override
    public Key ceil(@NotNull Key key) {
        Node clNode = getCeil(root, key);
        return clNode != null ? clNode.key : null;
    }

    @Override
    public int size() {
        return size;
    }

    private boolean isRed(Node node) {
        return node != null && node.color == RED;
    }

    private Node rotateLeft(Node node) {
        Node rNode = node.right;
        node.right = rNode.left;
        rNode.left = node;
        rNode.color = node.color;
        node.color = RED;
        return rNode;
    }

    private Node rotateRight(Node node) {
        Node lNode = node.left;
        node.left = lNode.right;
        lNode.right = node;
        lNode.color = node.color;
        node.color = RED;
        return lNode;
    }

    private Node flipColors(Node node) {
        node.color = !node.color;
        node.left.color = !node.left.color;
        node.right.color = !node.right.color;
        return node;
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

    private Node put(Node node, Key key, Value value) {
        if (node == null) {
            size++;
            return new Node(key, value, RED);
        }
        int compRes = key.compareTo(node.key);
        if (compRes < 0) {
            node.left = put(node.left, key, value);
        } else if (compRes > 0) {
            node.right = put(node.right, key, value);
        } else {
            node.value = value;
        }
        return fixUp(node);
    }

    private Node delete(Node node, Key key) {
        if (node == null) {
            return null;
        }

        if (key.compareTo(node.key) < 0) {
            if (node.left != null) {
                if (!isRed(node.left) && !isRed(node.left.left)) {
                    node = moveRedLeft(node);
                }
                node.left = delete(node.left, key);
            }
        } else {
            if (isRed(node.left)) {
                node = rotateRight(node);
                node.right = delete(node.right, key);
            } else if (key.compareTo(node.key) == 0 && node.right == null) {
                justNowDeleted = node.value;
                return null;
            } else {
                if (node.right != null && !isRed(node.right) && !isRed(node.right.left)) {
                    node = moveRedRight(node);
                }
                if (key.compareTo(node.key) == 0) {
                    justNowDeleted = node.value;

                    Node min = getMinNode(node.right);
                    node.key = min.key;
                    node.value = min.value;
                    node.right = deleteMin(node.right);
                } else {
                    node.right = delete(node.right, key);
                }
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

    private Node getMinNode(Node node) {
        return node.left == null ? node : getMinNode(node.left);
    }

    private Node getMaxNode(Node node) {
        return node.right == null ? node : getMaxNode(node.right);
    }

    private Value get(Node node, Key key) {
        if (node == null) {
            return null;
        }

        int compareRes = key.compareTo(node.key);
        if (compareRes < 0) {
            return get(node.left, key);
        } else if (compareRes > 0) {
            return get(node.right, key);
        }

        return node.value;
    }

    private Node getFloor(Node node, Key key) {
        if (node == null) {
            return null;
        }

        int compareRes = key.compareTo(node.key);
        if (compareRes == 0) {
            return node;
        } else if (compareRes < 0) {
            return getFloor(node.left, key);
        }

        Node nextNode = getFloor(node.right, key);
        return nextNode != null ? nextNode : node;
    }

    private Node getCeil(Node node, Key key) {
        if (node == null) {
            return null;
        }

        int compareRes = key.compareTo(node.key);
        if (compareRes == 0) {
            return node;
        } else if (compareRes > 0) {
            return getCeil(node.right, key);
        }

        Node nextNode = getCeil(node.left, key);
        return nextNode != null ? nextNode : node;
    }
}
