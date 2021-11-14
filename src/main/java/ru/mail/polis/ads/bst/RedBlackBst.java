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
    private int size;

    private class Node {
        Key key;
        Value value;
        Node left;
        Node right;
        boolean color;

        public Node(Key key, Value value, Boolean color) {
            this.key = key;
            this.value = value;
            this.color = color;
        }
    }

    @Nullable
    @Override
    public Value get(@NotNull Key key) {
        Node res = get(root, key);
        return res == null ? null : res.value;
    }

    @Override
    public void put(@NotNull Key key, @NotNull Value value) {
        root = put(root, key, value);
        root.color = BLACK;
    }

    @Nullable
    @Override
    public Value remove(@NotNull Key key) {
        Node nodeToRemove = get(root, key);
        if (nodeToRemove == null) {
            return null;
        }
        Value value = nodeToRemove.value;
        root = delete(root, key);
        size--;
        return value;
    }

    @Nullable
    @Override
    public Key min() {
        Node min = min(root);
        return min == null ? null : min.key;
    }

    @Nullable
    @Override
    public Value minValue() {
        Node min = min(root);
        return min == null ? null : min.value;
    }

    @Nullable
    @Override
    public Key max() {
        Node max = max(root);
        return max == null ? null : max.key;
    }

    @Nullable
    @Override
    public Value maxValue() {
        Node max = max(root);
        return max == null ? null : max.value;
    }

    @Nullable
    @Override
    public Key floor(@NotNull Key key) {
        Node res = floor(root, key);
        return res == null ? null : res.key;
    }

    @Nullable
    @Override
    public Key ceil(@NotNull Key key) {
        Node res = ceil(root, key);
        return res == null ? null : res.key;
    }

    @Override
    public int size() {
        return size;
    }

    private Node get(Node node, Key key) {
        if (node == null) {
            return null;
        }
        if (key.compareTo(node.key) < 0) {
            return get(node.left, key);
        }
        if (key.compareTo(node.key) > 0) {
            return get(node.right, key);
        }
        return node;
    }

    private Node put(Node node, Key key, Value value) {
        if (node == null) {
            size++;
            return new Node(key, value, RED);
        }
        if (key.compareTo(node.key) < 0) {
            node.left = put(node.left, key, value);
        } else if (key.compareTo(node.key) > 0) {
            node.right = put(node.right, key, value);
        } else {
            node.value = value;
        }
        return fixUp(node);
    }

    private Node min(Node node) {
        if (node == null) {
            return null;
        }
        if (node.left == null) {
            return node;
        }
        return min(node.left);
    }

    private Node max(Node node) {
        if (node == null) {
            return null;
        }
        if (node.right == null) {
            return node;
        }
        return max(node.right);
    }

    private Node floor(Node node, Key key) {
        if (node == null) {
            return null;
        }
        if (node.key == key) {
            return node;
        }
        if (key.compareTo(node.key) < 0) {
            return floor(node.left, key);
        }
        Node bigger = floor(node.right, key);
        return bigger == null ? node : bigger;
    }

    private Node ceil(Node node, Key key) {
        if (node == null) {
            return null;
        }
        if (node.key == key) {
            return node;
        }
        if (key.compareTo(node.key) > 0) {
            return ceil(node.right, key);
        }
        Node smaller = ceil(node.left, key);
        return smaller == null ? node : smaller;
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

    private Node delete(Node x, Key key) {
        if (x == null) {
            return null;
        }
        if (key.compareTo(x.key) < 0) {
            if (x.left != null) {
                if (!isRed(x.left) && !isRed(x.left.left)) {
                    x = moveRedLeft(x);
                }
                x.left = delete(x.left, key);
            }
        } else {
            if (isRed(x.left)) {
                x = rotateRight(x);
                x.right = delete(x.right, key);
            } else if (x.key == key && x.right == null) {
                return null;
            } else {
                if (x.right != null && !isRed(x.right) && !isRed(x.right.left)) {
                    x = moveRedRight(x);
                }
                if (x.key == key) {
                    Node min = min(x.right);
                    x.key = min.key;
                    x.value = min.value;
                    x.right = deleteMin(x.right);
                } else {
                    x.right = delete(x.right, key);
                }
            }
        }
        return fixUp(x);
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

    private Node deleteMax(Node node) {
        if (isRed(node.left)) {
            node = rotateRight(node);
        }
        if (node.right == null) {
            return null;
        }
        if (!isRed(node.right) && !isRed(node.right.right)) {
            node = moveRedRight(node);
        }
        node = deleteMax(node.right);
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

    private Node rotateLeft(Node rotatingLeftNode) {
        Node rightChild = rotatingLeftNode.right;
        rotatingLeftNode.right = rightChild.left;
        rightChild.left = rotatingLeftNode;
        rightChild.color = rotatingLeftNode.color;
        rotatingLeftNode.color = RED;
        return rightChild;
    }

    private Node rotateRight(Node rotatingRightNode) {
        Node leftChild = rotatingRightNode.left;
        rotatingRightNode.left = leftChild.right;
        leftChild.right = rotatingRightNode;
        leftChild.color = rotatingRightNode.color;
        rotatingRightNode.color = RED;
        return leftChild;
    }

    private void flipColors(Node node) {
        node.color = !node.color;
        if (node.left != null) {
            node.left.color = !node.left.color;
        }
        if (node.right != null) {
            node.right.color = !node.right.color;
        }
    }

}
