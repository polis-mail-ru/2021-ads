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

    private int blackHeight;
    private int size;
    private Node lastRemoved;

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

        public Node(Node src) {
            this(src.key, src.value, src.left, src.right, src.color);
        }
    }

    private Node root;

    @Nullable
    @Override
    public Value get(@NotNull Key key) {
        Node got = get(root, key);
        return got == null ? null : got.value;
    }

    @Override
    public void put(@NotNull Key key, @NotNull Value value) {
        root = put(root, key, value);
        blackHeight += root.color == RED ? 1 : 0;
        root.color = BLACK;
    }

    @Nullable
    @Override
    public Value remove(@NotNull Key key) {
        int fixedSize = size;
        root = delete(root, key);
        if (fixedSize == size || lastRemoved == null) {
            return null;
        }
        return lastRemoved.value;
    }

    @Nullable
    @Override
    public Key min() {
        return root == null ? null : min(root).key;
    }

    @Nullable
    @Override
    public Value minValue() {
        return root == null ? null : min(root).value;
    }

    @Nullable
    @Override
    public Key max() {
        return root == null ? null : max(root).key;
    }

    @Nullable
    @Override
    public Value maxValue() {
        return root == null ? null : max(root).value;
    }

    @Nullable
    @Override
    public Key floor(@NotNull Key key) {
        return floor(root, key);
    }

    @Nullable
    @Override
    public Key ceil(@NotNull Key key) {
        return ceil(root, key);
    }

    @Override
    public int size() {
        return size;
    }

    public int getBlackHeight() {
        return blackHeight;
    }

    public void deleteMin() {
        root = deleteMin(root);
        root.color = BLACK;
    }

    public void deleteMax() {
        root = deleteMax(root);
        root.color = BLACK;
    }

    private Node get(Node x, Key key) {
        if (x == null) {
            return null;
        }
        if (key.compareTo(x.key) < 0) {
            return get(x.left, key);
        }
        if (key.compareTo(x.key) > 0) {
            return get(x.right, key);
        }
        return x;
    }

    private Node put(Node x, Key key, Value value) {
        if (x == null) {
            ++size;
            return new Node(key, value, null, null, RED);
        }
        if (key.compareTo(x.key) < 0) {
            x.left = put(x.left, key, value);
        } else if (key.compareTo(x.key) > 0) {
            x.right = put(x.right, key, value);
        } else {
            x.value = value;
        }
        return fixUp(x);
    }

    private Node min(Node x) {
        if (x.left != null) {
            return min(x.left);
        }
        return x;
    }

    private Node max(Node x) {
        if (x.right != null) {
            return max(x.right);
        }
        return x;
    }

    private Key floor(Node x, Key key) {
        if (x == null) {
            return null;
        }
        if (key.compareTo(x.key) == 0
                || (x.key.compareTo(key) < 0 && (x.right == null || min(x.right).key.compareTo(key) > 0))) {
            return x.key;
        }
        if (key.compareTo(x.key) < 0) {
            return floor(x.left, key);
        }
        return floor(x.right, key);
    }

    private Key ceil(Node x, Key key) {
        if (x == null) {
            return null;
        }
        if (key.compareTo(x.key) == 0 || (key.compareTo(x.key) < 0 && x.left == null)) {
            return x.key;
        }
        if (key.compareTo(x.key) > 0) {
            return ceil(x.right, key);
        }
        return ceil(x.left, key);
    }

    private boolean isRed(Node x) {
        return x != null && x.color == RED;
    }

    private boolean isBlack(Node x) {
        return !isRed(x);
    }

    private Node rotateLeft(Node x) {
        Node y = x.right;
        x.right = y.left;
        y.left = x;
        y.color = x.color;
        x.color = RED;
        return y;
    }

    private Node rotateRight(Node y) {
        Node x = y.left;
        y.left = x.right;
        x.right = y;
        x.color = y.color;
        y.color = RED;
        return x;
    }

    private Node flipColors(Node x) {
        x.color = !x.color;
        x.left.color = !x.left.color;
        x.right.color = !x.right.color;
        return x;
    }

    private Node fixUp(Node x) {
        if (x == null) {
            return null;
        }
        if (isRed(x.right) && isBlack(x.left)) {
            x = rotateLeft(x);
        }
        if (isRed(x.left) && isRed(x.left.left)) {
            x = rotateRight(x);
        }
        if (isRed(x.right) && isRed(x.left)) {
            flipColors(x);
        }
        return x;
    }

    private Node moveRedLeft(Node x) {
        flipColors(x);
        if (isRed(x.right.left)) {
            x.right = rotateRight(x.right);
            x = rotateLeft(x);
            flipColors(x);
        }
        return x;
    }

    private Node deleteMin(Node x) {
        if (x.left == null) {
            return null;
        }
        if (isBlack(x.left) && isBlack(x.left.left)) {
            x = moveRedLeft(x);
        }
        x.left = deleteMin(x.left);
        return fixUp(x);
    }

    private Node moveRedRight(Node x) {
        flipColors(x);
        if (isRed(x.left.left)) {
            x = rotateRight(x);
            flipColors(x);
        }
        return x;
    }

    private Node deleteMax(Node x) {
        if (isRed(x.left)) {
            x = rotateRight(x);
        }
        if (x.right == null) {
            return null;
        }
        if (isBlack(x.right) && isBlack(x.right.left)) {
            x = moveRedRight(x);
        }
        x.right = deleteMax(x.right);
        return fixUp(x);
    }

    private Node delete(Node x, Key key) {
        if (x == null) {
            return null;
        }
        if (key.compareTo(x.key) < 0) {
            if (x.left != null) {
                if (isBlack(x.left) && isBlack(x.left.left)) {
                    x = moveRedLeft(x);
                }
                x.left = delete(x.left, key);
            }
        } else {
            if (isRed(x.left)) {
                x = rotateRight(x);
                x.right = delete(x.right, key);
            } else if (key.compareTo(x.key) == 0 && x.right == null) {
                --size;
                lastRemoved = new Node(x);
                return null;
            } else {
                if (x.right != null && isBlack(x.right) && isBlack(x.right.left)) {
                    x = moveRedRight(x); // preserve invariant
                }
                deleteUnderInvariant(x, key);
            }
        }
        return fixUp(x);
    }

    private void deleteUnderInvariant(Node x, Key key) {
        if (key.compareTo(x.key) == 0) {
            --size;
            lastRemoved = new Node(x);
            Node min = min(x.right);
            x.key = min.key;
            x.value = min.value;
            x.right = deleteMin(x.right);
        } else {
            x.right = delete(x.right, key);
        }
    }

}
