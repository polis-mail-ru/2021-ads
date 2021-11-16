package ru.mail.polis.ads.bst;

import org.jetbrains.annotations.NotNull;

/**
 * AVL implementation of binary search tree.
 */
public class AvlBst<Key extends Comparable<Key>, Value>
        implements Bst<Key, Value> {
    private Node root = null;

    @Override
    public Value get(@NotNull Key key) {
        return get(root, key);
    }

    @Override
    public void put(@NotNull Key key, @NotNull Value value) {
        root = put(root, key, value);
    }

    @Override
    public Value remove(@NotNull Key key) {
        Value result = get(root, key);
        root = result == null ? root : delete(root, key);
        return result;
    }

    @Override
    public Key min() {
        return root == null ? null : min(root).key;
    }

    @Override
    public Value minValue() {
        return root == null ? null : min(root).value;
    }

    @Override
    public Key max() {
        return root == null ? null : max(root).key;
    }

    @Override
    public Value maxValue() {
        return root == null ? null : max(root).value;
    }

    @Override
    public Key floor(@NotNull Key key) {
        Node temp = root == null ? null : floor(root, key);
        return temp == null ? null : temp.key;
    }

    @Override
    public Key ceil(@NotNull Key key) {
        Node temp = root == null ? null : ceil(root, key);
        return temp == null ? null : temp.key;
    }

    @Override
    public int size() {
        return size(root);
    }

    @Override
    public int height() {
        return recHeight(root);
    }


    private int size(Node x) {
        if (x == null) {
            return 0;
        }
        return 1 + size(x.left) + size(x.right);
    }

    private int recHeight(Node x) {
        if (x == null) {
            return 0;
        }
        return 1 + Math.max(recHeight(x.right), recHeight(x.left));
    }

    private Value get(Node x, Key key) {
        if (x == null) {
            return null;
        }
        if (key.compareTo(x.key) < 0) {
            return get(x.left, key);
        }
        if (key.compareTo(x.key) > 0) {
            return get(x.right, key);
        }
        return x.value;
    }

    private Node put(Node x, Key key, Value value) {
        if (x == null) {
            return new Node(key, value, 1);
        }
        if (key.compareTo(x.key) < 0) {
            x.left = put(x.left, key, value);
        } else if (key.compareTo(x.key) > 0) {
            x.right = put(x.right, key, value);
        } else {
            x.value = value;
        }
        fixHeight(x);
        x = balance(x);
        return x;
    }

    private int height(Node x) {
        return x == null ? 0 : x.height;
    }

    private void fixHeight(Node x) {
        x.height = 1 + Math.max(height(x.left), height(x.right));
    }

    private Node rotateRight(Node y) {
        Node x = y.left;
        y.left = x.right;
        x.right = y;
        fixHeight(y);
        fixHeight(x);
        return x;
    }

    private Node rotateLeft(Node x) {
        Node y = x.right;
        x.right = y.left;
        y.left = x;
        fixHeight(x);
        fixHeight(y);
        return y;
    }

    private int factor(Node x) {
        return height(x.left) - height(x.right);
    }

    private Node balance(Node x) {
        if (factor(x) == 2) {
            if (factor(x.left) < 0) {
                x.left = rotateLeft(x.left);
            }
            return rotateRight(x);
        }
        if (factor(x) == -2) {
            if (factor(x.right) > 0) {
                x.right = rotateRight(x.right);
            }
            return rotateLeft(x);
        }
        return x;
    }

    private Node delete(Node x, Key key) {
        if (x == null) {
            return null;
        }
        if (key.compareTo(x.key) < 0) {
            x.left = delete(x.left, key);
        }
        if (key.compareTo(x.key) > 0) {
            x.right = delete(x.right, key);
        }
        if (key.equals(x.key)) {
            x = innerDelete(x);
        }
        if (x != null) {
            fixHeight(x);
            x = balance(x);
        }
        return x;
    }

    private Node deleteMin(Node x) {
        if (x.left == null) {
            return x.right;
        }
        x.left = deleteMin(x.left);
        return x;
    }

    private Node innerDelete(Node x) {
        if (x.right == null) {
            return x.left;
        }
        if (x.left == null) {
            return x.right;
        }
        Node t = x;
        x = min(t.right);
        x.right = deleteMin(t.right);
        x.left = t.left;
        return x;
    }

    private Node min(Node value) {
        if (value.left == null) {
            return value;
        }
        return min(value.left);
    }

    private Node max(Node value) {
        if (value.right == null) {
            return value;
        }
        return max(value.right);
    }

    private Node ceil(Node node, Key key) {
        if (node.key.compareTo(key) < 0) {
            return node.right == null ? null : ceil(node.right, key);
        }
        if (node.key.compareTo(key) == 0 || node.left == null) {
            return node;
        }
        Node result = ceil(node.left, key);
        if (result == null || node.key.compareTo(result.key) < 0) {
            return node;
        }
        return result;
    }

    private Node floor(Node node, Key key) {
        if (node.key.compareTo(key) > 0) {
            return node.left == null ? null : floor(node.left, key);
        }
        if (node.key.compareTo(key) == 0 || node.right == null) {
            return node;
        }
        Node result = floor(node.right, key);
        if (result == null || node.key.compareTo(result.key) > 0) {
            return node;
        }
        return result;
    }

    private class Node {
        Key key;
        Value value;
        Node left;
        Node right;
        int height;

        public Node(Key key, Value value, int height) {
            this.key = key;
            this.value = value;
            this.height = height;
        }
    }
}
