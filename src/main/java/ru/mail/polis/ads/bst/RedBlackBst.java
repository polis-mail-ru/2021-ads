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
    private Value removedValue;

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

    private Value get(Node x, Key key) {
        if (x == null) {
            return null;
        }
        int resultOfCompare = key.compareTo(x.key);
        if (resultOfCompare < 0) {
            return get(x.left, key);
        }
        if (resultOfCompare > 0) {
            return get(x.right, key);
        }
        return x.value;
    }

    @Override
    public void put(@NotNull Key key, @NotNull Value value) {
        root = put(root, key, value);
        root.color = BLACK;
    }

    private Node put(Node x, Key key, Value value) {
        if (x == null) {
            size++;
            return new Node(key, value, RED);
        }
        int resultOfCompare = key.compareTo(x.key);
        if (resultOfCompare < 0) {
            x.left = put(x.left, key, value);
        } else if (resultOfCompare > 0) {
            x.right = put(x.right, key, value);
        } else  {
            x.value = value;
        }
        return fixUp(x);
    }

    private Node fixUp(Node x) {
        if (isRed(x.right) && !isRed(x.left)) {
            x = rotateLeft(x);
        }
        if (isRed(x.left) && isRed(x.left.left)) {
            x = rotateRight(x);
        }
        if (isRed(x.left) && isRed(x.right)) {
            flipColors(x);
        }
        return x;
    }

    private boolean isRed(Node x) {
        return x != null && x.color == RED;
    }

    private Node rotateLeft(Node x) {
        Node y = x.right;
        x.right = y.left;
        y.left = x;
        y.color = x.color;
        x.color = RED;
        return y;
    }

    private Node rotateRight(Node x) {
        Node y = x.left;
        x.left = y.right;
        y.right = x;
        y.color = x.color;
        x.color = RED;
        return y;
    }

    private Node flipColors(Node x) {
        x.color = !x.color;
        x.left.color = !x.left.color;
        x.right.color = !x.right.color;
        return x;
    }

    @Nullable
    @Override
    public Value remove(@NotNull Key key) {
        removedValue = null;
        root = delete(root, key);
        if (removedValue != null) {
            size--;
        }
        return removedValue;
    }

    private Node delete(Node x, Key key) {
        if (x == null) {
            return null;
        }
        if (key.compareTo(x.key) < 0) { // left delete
            if (x.left != null) {
               if (!isRed(x.left) && !isRed(x.left.left)) {
                   x = moveRedLeft(x);
               }
               x.left = delete(x.left, key);
               return fixUp(x);
            }
        }
        // right delete
        if (isRed(x.left)) {
            x = rotateRight(x);
            x.right = delete(x.right, key);
            return fixUp(x);
        } else if (x.key == key && x.right == null) {
            removedValue = x.value;
            return null;
        } else {
            if (x.right != null && !isRed(x.right) && !isRed(x.right.left)) {
                x = moveRedRight(x);
            }
            if (x.key == key) {
                removedValue = x.value;
                Node min = min(x.right);
                x.key = min.key;
                x.value = min.value;
                x.right = deleteMin(x.right);
            } else {
                x.right = delete(x.right, key);
            }
        }
        return fixUp(x);
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

    @Nullable
    @Override
    public Key min() {
        return (root != null) ? min(root).key : null;
    }

    private Node min(Node x) {
        if (x.left == null) {
            return x;
        }
        return min(x.left);
    }

    @Nullable
    @Override
    public Value minValue() {
        return (root != null) ? min(root).value : null;
    }

    @Nullable
    @Override
    public Key max() {
        return (root != null) ? max(root).key : null;
    }

    private Node max(Node x) {
        if (x.right == null) {
            return x;
        }
        return max(x.right);
    }

    @Nullable
    @Override
    public Value maxValue() {
        return (root != null) ? max(root).value : null;
    }

    @Nullable
    @Override
    public Key floor(@NotNull Key key) {
        Node result = floor(root, key);
        return (result != null) ? result.key : null;
    }

    private Node floor(Node x, Key key) {
        if (x == null) {
            return null;
        }
        int resultOfCompare = x.key.compareTo(key);
        if (resultOfCompare == 0) {
            return x;
        }
        if (resultOfCompare > 0) {
            return floor(x.left, key);
        }
        Node result = floor(x.right, key);
        if (result == null || key.compareTo(result.key) < 0) {
            return x;
        }
        return result;
    }

    @Nullable
    @Override
    public Key ceil(@NotNull Key key) {
        Node result = ceil(root, key);
        return (result != null) ? result.key : null;
    }

    private Node ceil(Node x, Key key) {
        if (x == null) {
            return null;
        }
        int resultOfCompare = x.key.compareTo(key);
        if (resultOfCompare == 0) {
            return x;
        }
        if (resultOfCompare < 0) {
            return ceil(x.right, key);
        }
        Node result = ceil(x.left, key);
        if (result == null || key.compareTo(result.key) > 0) {
            return x;
        }
        return result;
    }

    @Override
    public int size() {
        return size;
    }
}
