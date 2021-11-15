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

        public Node(Key key, Value value, boolean color) {
            this.key = key;
            this.value = value;
            this.color = color;
        }
    }

    public boolean isRed(Node node) {
        return node != null && node.color;
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

    private Node rotateRight(Node point) {
        Node temp = point.left;
        point.left = temp.right;
        temp.right = point;
        temp.color = point.color;
        point.color = RED;
        return temp;
    }

    private Node rotateLeft(Node point) {
        Node temp = point.right;
        point.right = temp.left;
        temp.left = point;
        temp.color = point.color;
        point.color = RED;
        return temp;
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

    private Node removeMin(Node node) {
        if (node.left == null) {
            return null;
        }
        if (!isRed(node.left) && !isRed(node.left.left)) {
            node = moveRedLeft(node);
        }
        node.left = removeMin(node.left);
        return fixUp(node);
    }

    private Value get(Node node, Key key) {
        if (node == null) {
            return null;
        }
        int compare = key.compareTo(node.key);
        if (compare < 0) {
            return get(node.left, key);
        }
        if (compare > 0) {
            return get(node.right, key);
        }
        return node.value;
    }

    @Nullable
    @Override
    public Value get(@NotNull Key key) {
        return get(this.root, key);
    }

    private Node put(Node node, Key key, Value value) {
        if (node == null) {
            ++this.size;
            return new Node(key, value, RED);
        }
        int compare = key.compareTo(node.key);
        if (compare < 0) {
            node.left = put(node.left, key, value);
        } else if (compare > 0) {
            node.right = put(node.right, key, value);
        } else {
            node.value = value;
        }
        return fixUp(node);
    }

    @Override
    public void put(@NotNull Key key, @NotNull Value value) {
        this.root = put(this.root, key, value);
        this.root.color = BLACK;
    }

    private Node remove(Node node, Key key) {
        if (node == null) {
            return null;
        }
        if (key.compareTo(node.key) < 0) {
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
            } else if (key.compareTo(node.key) == 0 && node.right == null) {
                --this.size;
                return null;
            } else {
                if (node.right != null && !isRed(node.right) && !isRed(node.right.left)) {
                    node = moveRedRight(node);
                }
                rebalance(node, key);
            }
        }
        return fixUp(node);
    }

    private void rebalance(Node node, Key key) {
        if (key.compareTo(node.key) == 0) {
            --this.size;
            Node min = min(node.right);
            node.key = min.key;
            node.value = min.value;
            node.right = removeMin(node.right);
        } else {
            node.right = remove(node.right, key);
        }
    }

    @Nullable
    @Override
    public Value remove(@NotNull Key key) {
        Value value = get(key);
        if (value == null) {
            return null;
        }
        this.root = remove(this.root, key);
        return value;
    }

    @Nullable
    @Override
    public Key min() {
        Node node = min(this.root);
        if (node == null) {
            return null;
        }
        return node.key;
    }

    @Nullable
    @Override
    public Value minValue() {
        Node node = min(this.root);
        if (node == null) {
            return null;
        }
        return node.value;
    }

    @Nullable
    @Override
    public Key max() {
        Node node = max(this.root);
        if (node == null) {
            return null;
        }
        return node.key;
    }

    @Nullable
    @Override
    public Value maxValue() {
        Node node = max(this.root);
        if (node == null) {
            return null;
        }
        return node.value;
    }

    private Node floor(Node node, Key key) {
        if (node == null) {
            return null;
        }
        int compare = key.compareTo(node.key);
        if (compare == 0) {
            return node;
        }
        if (compare < 0) {
            return floor(node.left, key);
        }
        Node subNode = floor(node.right, key);
        if (subNode == null) {
            return node;
        }
        return key.compareTo(subNode.key) < 0 ? node : subNode;
    }

    @Nullable
    @Override
    public Key floor(@NotNull Key key) {
        Node node = floor(this.root, key);
        if (node == null) {
            return null;
        }
        return node.key;
    }

    private Node ceil(Node node, Key key) {
        if (node == null) {
            return null;
        }
        int compare = key.compareTo(node.key);
        if (compare == 0) {
            return node;
        }
        if (compare > 0) {
            return ceil(node.right, key);
        }
        Node subNode = ceil(node.left, key);
        if (subNode == null) {
            return node;
        }
        return key.compareTo(subNode.key) > 0 ? node : subNode;
    }

    @Nullable
    @Override
    public Key ceil(@NotNull Key key) {
        Node node = ceil(this.root, key);
        if (node == null) {
            return null;
        }
        return node.key;
    }

    @Override
    public int size() {
        return this.size;
    }
}
