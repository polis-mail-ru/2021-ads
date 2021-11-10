package ru.mail.polis.ads.bst;

import org.jetbrains.annotations.NotNull;

/**
 * AVL implementation of binary search tree.
 */
public class AvlBst<Key extends Comparable<Key>, Value>
        implements Bst<Key, Value> {

    private Node root;
    private int size = 0;

    private class Node {
        Key key;
        Value value;
        Node left;
        Node right;
        int height;

        Node(Key key, Value value, int height) {
            this.key = key;
            this.value = value;
            this.left = null;
            this.right = null;
            this.height = height;
        }
    }

    @Override
    public Value get(@NotNull Key key) {
        Node keyNode = get(root, key);
        return keyNode == null ? null : keyNode.value;
    }

    @Override
    public void put(@NotNull Key key, @NotNull Value value) {
        root = put(root, key, value);
    }

    @Override
    public Value remove(@NotNull Key key) {
        Node removedNode = get(root, key);
        if (removedNode == null) {
            return null;
        }

        root = remove(root, key);

        return removedNode.value;
    }

    @Override
    public Key min() {
        if (size == 0) {
            return null;
        }

        return min(root).key;
    }

    @Override
    public Value minValue() {
        if (size == 0) {
            return null;
        }

        return min(root).value;
    }

    @Override
    public Key max() {
        if (size == 0) {
            return null;
        }

        return max(root).key;
    }

    @Override
    public Value maxValue() {
        if (size == 0) {
            return null;
        }

        return max(root).value;
    }

    @Override
    public Key floor(@NotNull Key key) {
        Node resultNode = floor(root, key);
        return resultNode == null ? null : resultNode.key;
    }

    @Override
    public Key ceil(@NotNull Key key) {
        Node resultNode = ceil(root, key);
        return resultNode == null ? null : resultNode.key;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public int height() {
        return root == null ? 0 : root.height;
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
            return new Node(key, value, 1);
        }

        if (node.key.compareTo(key) > 0) {
            node.left = put(node.left, key, value);
        } else if (node.key.compareTo(key) < 0) {
            node.right = put(node.right, key, value);
        } else {
            node.value = value;
        }

        fixHeight(node);
        return balance(node);
    }

    private Node remove(Node node, Key key) {
        if (node == null) {
            return null;
        }

        if (node.key.compareTo(key) > 0) {
            node.left = remove(node.left, key);
        } else if (node.key.compareTo(key) < 0) {
            node.right = remove(node.right, key);
        } else {
            Node removedNode = node;
            size--;

            if (node.right == null) {
                return node.left;
            }
            if (node.left == null) {
                return node.right;
            }
            node = min(removedNode.right);
            node.right = removeMin(removedNode.right);
            node.left = removedNode.left;
        }

        fixHeight(node);
        return balance(node);
    }

    private Node removeMin(Node node) {
        if (node.left == null) {
            return node.right;
        }

        node.left = removeMin(node.left);
        return node;
    }

    private Node balance(Node node) {
        if (factor(node) == 2) {
            if (factor(node.left) < 0) {
                rotateLeft(node.left);
            }
            return rotateRight(node);
        }
        if (factor(node) == -2) {
            if (factor(node.right) > 0) {
                rotateRight(node.right);
            }
            return rotateLeft(node);
        }

        return node;
    }

    private Node rotateLeft(Node node) {
        Node oldRight = node.right;

        node.right = oldRight.left;
        oldRight.left = node;

        fixHeight(oldRight.left);
        fixHeight(oldRight);

        return oldRight;
    }

    private Node rotateRight(Node node) {
        Node oldLeft = node.left;

        node.left = oldLeft.right;
        oldLeft.right = node;

        fixHeight(oldLeft.right);
        fixHeight(oldLeft);

        return oldLeft;
    }

    private int factor(Node node) {
        int leftHeight = node.left == null ? 0 : node.left.height;
        int rightHeight = node.right == null ? 0 : node.right.height;

        return leftHeight - rightHeight;
    }

    private void fixHeight(Node node) {
        if (node == null) {
            return;
        }

        int leftHeight = node.left == null ? 0 : node.left.height;
        int rightHeight = node.right == null ? 0 : node.right.height;

        node.height = Math.max(leftHeight, rightHeight) + 1;
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
