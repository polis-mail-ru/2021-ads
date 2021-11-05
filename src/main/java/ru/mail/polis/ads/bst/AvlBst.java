package ru.mail.polis.ads.bst;

import org.jetbrains.annotations.NotNull;

/**
 * AVL implementation of binary search tree.
 */
public class AvlBst<Key extends Comparable<Key>, Value>
        implements Bst<Key, Value> {

    private Node root = null;
    private int size;
    private Node justNowDeleted;

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
        root = delete(root, key);
        if (justNowDeleted != null) {
            size--;
            Value delVal = justNowDeleted.value;
            justNowDeleted = null;

            return delVal;
        }

        return null;
    }

    @Override
    public Key min() {
        return root == null ? null : getMinNode(root).key;
    }

    @Override
    public Value minValue() {
        return root == null ? null : getMinNode(root).value;
    }

    @Override
    public Key max() {
        return root == null ? null : getMaxNode(root).key;
    }

    @Override
    public Value maxValue() {
        return root == null ? null : getMaxNode(root).value;
    }

    @Override
    public Key floor(@NotNull Key key) {
        Node flNode = getFloor(root, key);
        return flNode != null ? flNode.key : null;
    }

    @Override
    public Key ceil(@NotNull Key key) {
        Node clNode = getCeil(root, key);
        return clNode != null ? clNode.key : null;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public int height() {
        return getHeight(root);
    }

    private Node put(Node node, Key key, Value value) {
        if (node == null) {
            size++;
            return new Node(key, value, 1);
        }

        int compareRes = key.compareTo(node.key);
        if (compareRes < 0) {
            node.left = put(node.left, key, value);
        } else if (compareRes > 0) {
            node.right = put(node.right, key, value);
        } else {
            node.value = value;
        }

        fixHeight(node);
        return balance(node);
    }

    Node balance(Node node) {
        if (getFactor(node) == 2) {
            if (getFactor(node.left) < 0) {
                node.left = rotateLeft(node.left);
            }
            return rotateRight(node);
        }

        if (getFactor(node) == -2) {
            if (getFactor(node.right) > 0) {
                node.right = rotateRight(node.right);
            }
            return rotateLeft(node);
        }

        return node;
    }

    private void fixHeight(Node node) {
        node.height = 1 + Math.max(getHeight(node.left), getHeight(node.right));
    }

    private int getFactor(Node node) {
        return getHeight(node.left) - getHeight(node.right);
    }

    private int getHeight(Node node) {
        return node == null ? 0 : node.height;
    }

    Node rotateLeft(Node node) {
        Node rNode = node.right;
        node.right = rNode.left;
        rNode.left = node;
        fixHeight(node);
        fixHeight(rNode);
        return rNode;
    }

    Node rotateRight(Node node) {
        Node lNode = node.left;
        node.left = lNode.right;
        lNode.right = node;
        fixHeight(node);
        fixHeight(lNode);
        return lNode;
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

    private Node getMinNode(Node node) {
        return node.left == null ? node : getMinNode(node.left);
    }

    private Node getMaxNode(Node node) {
        return node.right == null ? node : getMaxNode(node.right);
    }

    private Node delete(Node node, Key key) {
        if (node == null) {
            return null;
        }

        int compareRes = key.compareTo(node.key);
        if (compareRes < 0) {
            node.left = delete(node.left, key);
        } else if (compareRes > 0) {
            node.right = delete(node.right, key);
        } else {
            justNowDeleted = node;
            node = innerDelete(node);
        }

        return node;
    }

    private Node innerDelete(Node node) {
        if (node.right == null) {
            return node.left;
        }
        if (node.left == null) {
            return node.right;
        }

        Node tempNode = node;
        node = getMinNode(node.right);
        node.right = deleteMin(tempNode.right);
        node.left = tempNode.left;

        return node;
    }

    private Node deleteMin(Node node) {
        if (node.left == null) {
            return node.right;
        }
        node.left = deleteMin(node.left);
        return node;
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
