package ru.mail.polis.ads.bst;

import org.jetbrains.annotations.NotNull;

/**
 * AVL implementation of binary search tree.
 */
public class AvlBst<Key extends Comparable<Key>, Value>
        implements Bst<Key, Value> {

    private Node root;
    private int size;

    private class Node {
        Key key;
        Value value;
        Node left;
        Node right;
        int height = 1;

        public Node(Key key, Value value) {
            this.key = key;
            this.value = value;
        }

    }

    @Override
    public Value get(@NotNull Key key) {
        Node res = get(root, key);
        return res == null ? null : res.value;
    }

    @Override
    public void put(@NotNull Key key, @NotNull Value value) {
        root = put(root, key, value);
    }

    @Override
    public Value remove(@NotNull Key key) {
        Node nodeToRemove = get(root, key);
        root = remove(root, key);
        return nodeToRemove == null ? null : nodeToRemove.value;
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
        Node res = floor(root, key);
        return res == null ? null : res.key;
    }

    @Override
    public Key ceil(@NotNull Key key) {
        Node res = ceil(root, key);
        return res == null ? null : res.key;
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
            return new Node(key, value);
        }
        if (key.compareTo(node.key) < 0) {
            node.left = put(node.left, key, value);
        } else if (key.compareTo(node.key) > 0) {
            node.right = put(node.right, key, value);
        } else {
            node.value = value;
        }
        fixHeight(node);
        node = balance(node);
        return node;
    }

    private Node remove(Node node, Key key) {
        if (node == null) {
            return null;
        }
        if (key.compareTo(node.key) < 0) {
            node.left = remove(node.left, key);
        } else if (key.compareTo(node.key) > 0) {
            node.right = remove(node.right, key);
        } else {
            size--;
            node = innerRemove(node);
        }
        fixHeight(node);
        node = balance(node);
        return node;
    }

    private Node innerRemove(Node node) {
        if (node.left == null) {
            return node.right;
        }
        if (node.right == null) {
            return node.left;
        }
        Node t = node;
        node = min(t.right);
        node.right = deleteMin(t.right);
        node.left = t.left;
        return node;
    }

    private Node deleteMin(Node node) {
        return min(node).right;
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

    private int height(Node node) {
        return node == null ? 0 : node.height;
    }

    private void fixHeight(Node node) {
        if (node == null) {
            return;
        }
        node.height = 1 + Math.max(height(node.left), height(node.right));
    }

    private int factor(Node node) {
        return node == null ? 0 : height(node.left) - height(node.right);
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

    private Node rotateRight(Node rightRotatingNode) {
        Node leftChild = rightRotatingNode.left;
        rightRotatingNode.left = leftChild.right;
        leftChild.right = rightRotatingNode;
        fixHeight(rightRotatingNode);
        fixHeight(leftChild);
        return leftChild;
    }

    private Node rotateLeft(Node leftRotatingNode) {
        Node rightChild = leftRotatingNode.right;
        leftRotatingNode.right = rightChild.left;
        rightChild.left = leftRotatingNode;
        fixHeight(leftRotatingNode);
        fixHeight(rightChild);
        return rightChild;
    }

}
