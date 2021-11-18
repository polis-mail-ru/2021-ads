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
    private int size = 0;
    private Value lastDeleted;

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
        Node current = root;
        while (current != null && current.key.compareTo(key) != 0) {
            if (key.compareTo(current.key) > 0) {
                current = current.right;
            } else {
                current = current.left;
            }
        }
        return current != null ? current.value : null;
    }

    @Override
    public void put(@NotNull Key key, @NotNull Value value) {
        root = put(root, key, value);
        size++;
    }

    @Nullable
    @Override
    public Value remove(@NotNull Key key) {
        root = delete(root, key);
        if (lastDeleted != null) {
            size--;
        }
        return lastDeleted;
    }

    @Nullable
    @Override
    public Key min() {
        Node min = min(root);
        return (min != null) ? min.key : null;
    }

    @Nullable
    @Override
    public Value minValue() {
        Node min = min(root);
        return (min != null) ? min.value : null;
    }

    @Nullable
    @Override
    public Key max() {
        Node max = max(root);
        return (max != null) ? max.key : null;
    }

    @Nullable
    @Override
    public Value maxValue() {
        Node max = max(root);
        return (max != null) ? max.value : null;
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

    private Node rotateLeft(@NotNull Node x) {
        Node y = x.right;
        x.right = y.left;
        y.left = x;
        y.color = x.color;
        x.color = RED;
        return y;
    }

    private Node rotateRight(@NotNull Node y) {
        Node x = y.left;
        y.left = x.right;
        x.right = y;
        x.color = y.color;
        y.color = RED;
        return x;
    }

    private boolean isRed(Node x) {
        return x != null && x.color == RED;
    }

    private void flipColors(@NotNull Node x) {
        x.color = !x.color;
        x.left.color = !x.left.color;
        x.right.color = !x.right.color;
    }

    private Node put(Node x, Key key, Value value) {
        if (x == null) {
            return new Node(key, value, RED);
        }

        if (key.compareTo(x.key) < 0) {
            x.left = put(x.left, key, value);
            return fixUp(x);
        }
        if (key.compareTo(x.key) > 0) {
            x.right = put(x.right, key, value);
        } else {
            x.value = value;
            size--;
        }
        return fixUp(x);
    }

    private Node fixUp(Node x) {
        if (isRed(x.right) && !isRed(x.left))
            x = rotateLeft(x);
        if (isRed(x.left) && isRed(x.left.left))
            x = rotateRight(x);
        if (isRed(x.left) && isRed(x.right))
            flipColors(x);
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

    private Node moveRedRight(Node x) {
        flipColors(x);
        if (isRed(x.left.left)) {
            x = rotateRight(x);
            flipColors(x);
        }
        return x;
    }

    private Node deleteMin(Node x) {
        if (x.left == null) {
            return null;
        }
        if (!isRed(x.left) && !isRed(x.left.left)) {
            x = moveRedLeft(x);
        }
        x.left = deleteMin(x.left);
        return fixUp(x);
    }

    private Node delete(Node x, Key key) {
        if (x == null) {
            lastDeleted = null;
            return null;
        }
        if (key.compareTo(x.key) < 0) { // delete left
            if (x.left != null) {
                if (!isRed(x.left) && !isRed(x.left.left)) {
                    x = moveRedLeft(x);
                }
            }
            x.left = delete(x.left, key);
            return fixUp(x);
        }
        // delete right
        if (isRed(x.left)) {
            x = rotateRight(x);
            x.right = delete(x.right, key);
            return fixUp(x);
        }
        if (x.key == key && x.right == null) {
            lastDeleted = x.value;
            return null; // at the bottom
        }

        if (x.right != null && !isRed(x.right) && !isRed(x.right.left)) {
            x = moveRedRight(x); // preserve invariant
        }
        // delete under invariant
        if (x.key == key) {
            lastDeleted = x.value;
            Node min = min(x.right);
            x.key = min.key;
            x.value = min.value;
            x.right = deleteMin(x.right);
        } else {
            x.right = delete(x.right, key);
        }
        return fixUp(x);
    }

    private Node min(Node x) {
        if (x == null) {
            return null;
        }
        Node current = x;
        while (current.left != null) {
            current = current.left;
        }
        return current;
    }

    private Node max(Node x) {
        if (x == null) {
            return null;
        }
        Node current = x;
        while (current.right != null) {
            current = current.right;
        }
        return current;
    }

    private Key floor(Node x, Key key) {
        if (x == null) {
            return null;
        }
        if (x.key.compareTo(key) == 0) {
            return x.key;
        }
        Key left = null;
        Key right = null;
        if (x.key.compareTo(key) > 0) {
            left = floor(x.left, key);
        }
        if (x.key.compareTo(key) < 0) {
            right = floor(x.right, key);
        }
        if ((left != null && left.compareTo(key) == 0) || (right != null && right.compareTo(key) == 0)) {
            return key;
        }
        if (right == null) {
            if (left == null) {
                return (x.key.compareTo(key) < 0) ? x.key : null;
            }
            return MaxFloor(left, x.key, key);
        }
        return MaxFloor(right, x.key, key);
    }

    private @Nullable Key MaxFloor(@NotNull Key first, Key second, Key floor) {
        Key max = (first.compareTo(floor) < 0) ? first
                : (second.compareTo(floor) < 0) ? second
                : null;
        if (max == null) {
            return null;
        }
        return findKeyByCondition(first, second, max
                , first.compareTo(max) > 0
                , first.compareTo(floor) < 0
                , second.compareTo(max) > 0
                , second.compareTo(floor) < 0);
    }

    private Key ceil(Node x, Key key) {
        if (x == null) {
            return null;
        }
        if (x.key.compareTo(key) == 0) {
            return x.key;
        }
        Key left = null;
        Key right = null;
        if (x.key.compareTo(key) > 0) {
            left = ceil(x.left, key);
        }
        if (x.key.compareTo(key) < 0) {
            right = ceil(x.right, key);
        }
        if ((left != null && left.compareTo(key) == 0) || (right != null && right.compareTo(key) == 0)) {
            return key;
        }
        if (right == null) {
            if (left == null) {
                return (x.key.compareTo(key) > 0) ? x.key : null;
            }
            return minCeil(left, x.key, key);
        }
        return minCeil(right, x.key, key);
    }

    private @Nullable Key minCeil(@NotNull Key first, Key second, Key floor) {
        Key min = (first.compareTo(floor) > 0) ? first
                : (second.compareTo(floor) > 0) ? second
                : null;
        if (min == null) {
            return null;
        }
        return findKeyByCondition(first, second, min
                , first.compareTo(min) < 0
                , first.compareTo(floor) > 0
                , second.compareTo(min) < 0
                , second.compareTo(floor) > 0);
    }

    private Key findKeyByCondition(Key first, Key second, Key key, boolean b1, boolean b2, boolean b3, boolean b4) {
        if (b1 && b2) {
            key = first;
        }
        if (b3 && b4) {
            key = second;
        }
        return key;
    }
}
