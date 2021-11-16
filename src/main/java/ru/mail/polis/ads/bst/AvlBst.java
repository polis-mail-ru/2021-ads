package ru.mail.polis.ads.bst;

import org.jetbrains.annotations.NotNull;

public class AvlBst<Key extends Comparable<Key>, Value>
        implements Bst<Key, Value> {

    private class Node {
        Key key;
        Value value;
        Node left;
        Node right;
        int height;

        Node(Key key, Value value, int height) {
            this.key = key;
            this.value = value;
            this.height = height;
        }
    }

    private Node root;
    private int size;

    @Override
    public Value get(@NotNull Key key) {
        Node node = get(root, key);
        return node == null ? null : node.value;
    }

    @Override
    public void put(@NotNull Key key, @NotNull Value value) {
        root = put(root, key, value);
    }

    @Override
    public Value remove(@NotNull Key key) {
        Node node = get(root, key);
        root = remove(root, key);
        return node == null ? null : node.value;
    }

    @Override
    public Key min() {
        Node min = min(root);
        return min == null ? null : min.key;
    }

    @Override
    public Value minValue() {
        Node min = min(root);
        return min == null ? null : min.value;
    }

    @Override
    public Key max() {
        Node max = max(root);
        return max == null ? null : max.key;
    }

    @Override
    public Value maxValue() {
        Node max = max(root);
        return max == null ? null : max.value;
    }

    @Override
    public Key floor(@NotNull Key key) {
        Node node = floor(root, key);
        return node == null ? null : node.key;
    }

    @Override
    public Key ceil(@NotNull Key key) {
        Node node = ceil(root, key);
        return node == null ? null : node.key;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public int height() {
        return height(root);
    }

    private Node get(Node node, Key key) {
        if (node != null) {
            if (key.compareTo(node.key) < 0) {
                return get(node.left, key);
            }
            if (key.compareTo(node.key) > 0) {
                return get(node.right, key);
            }
        }
        return node;
    }

    private Node put(Node node, Key key, Value value) {
        if (node == null) {
            ++size;
            return new Node(key, value, 1);
        }
        if (key.compareTo(node.key) < 0) {
            node.left = put(node.left, key, value);
        } else if (key.compareTo(node.key) > 0) {
            node.right = put(node.right, key, value);
        } else {
            node.value = value;
        }
        fixHeight(node);
        return balance(node);
    }

    private Node balance(Node node) {
        if (factor(node) == 2) {
            if (factor(node.left) < 0) {
                node.left = rotateLeft(node.left);
            }
            return rotateRight(node);
        }
        if (factor(node) == -2) {
            if (factor(node.right) > 0) {
                node.right = rotateRight(node.right);
            }
            return rotateLeft(node);
        }
        return node;
    }

    private Node rotateRight(Node node) {
        Node left = node.left;
        node.left = left.right;
        left.right = node;
        fixHeight(node);
        fixHeight(left);
        return left;
    }

    private Node rotateLeft(Node node) {
        Node right = node.right;
        node.right = right.left;
        right.left = node;
        fixHeight(node);
        fixHeight(right);
        return right;
    }

    private int factor(Node node) {
        return height(node.left) - height(node.right);
    }

    private void fixHeight(Node node) {
        node.height = 1 + Math.max(height(node.left), height(node.right));
    }

    private int height(Node node) {
        return node == null ? 0 : node.height;
    }

    private Node remove(Node node, Key key) {
        if (node != null) {
            if (key.compareTo(node.key) < 0) {
                node.left = remove(node.left, key);
            } else if (key.compareTo(node.key) > 0) {
                node.right = remove(node.right, key);
            } else {
                node = innerDelete(node);
            }
        }
        return node;
    }

    private Node innerDelete(Node node) {
        --size;
        if (node.right == null) {
            return node.left;
        }
        if (node.left == null) {
            return node.right;
        }
        Node temp = node;
        node = min(temp.right);
        node.right = deleteMin(temp.right);
        node.left = temp.left;
        return node;
    }

    private Node deleteMin(Node node) {
        if (node.left == null) {
            return node.right;
        }
        node.left = deleteMin(node.left);
        return node;
    }

    private Node min(Node node) {
        while (node != null && node.left != null) {
            node = node.left;
        }
        return node;
    }

    private Node max(Node node) {
        while (node != null && node.right != null) {
            node = node.right;
        }
        return node;
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
        Node greater = ceil(node.left, key);
        return greater == null ? node : greater;
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
        Node lesser = floor(node.right, key);
        return lesser == null ? node : lesser;
    }
}
