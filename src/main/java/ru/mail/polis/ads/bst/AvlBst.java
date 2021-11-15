package ru.mail.polis.ads.bst;

import org.jetbrains.annotations.NotNull;

/**
 * AVL implementation of binary search tree.
 */
public class AvlBst<Key extends Comparable<Key>, Value>
        implements Bst<Key, Value> {

    private Node root = null;
    private Value deleted = null;
    private int size;

    private class Node {
        Key key;
        Value value;
        Node left;
        Node right;
        int height;

        Node(Key key, Value value, Node left, Node right, int height) {
            this.key = key;
            this.value = value;
            this.left = left;
            this.right = right;
            this.height = height;
        }
    }

    @Override
    public Value get(@NotNull Key key) {
        Node current = root;
        int compare;
        while (current != null) {
            compare = key.compareTo(current.key);
            if (compare > 0) {
                current = current.right;
            } else if (compare < 0) {
                current = current.left;
            } else {
                return current.value;
            }
        }
        return null;
    }

    @Override
    public void put(@NotNull Key key, @NotNull Value value) {
        root = putElement(root, key, value);
    }

    @Override
    public Value remove(@NotNull Key key) {
        root = removeElement(root, key);
        return deleted;
    }

    @Override
    public Key min() {
        if (root == null) {
            return null;
        }
        return getMin(root).key;
    }

    @Override
    public Value minValue() {
        if (root == null) {
            return null;
        }
        return getMin(root).value;
    }

    @Override
    public Key max() {
        if (root == null) {
            return null;
        }
        return getMax(root).key;
    }

    @Override
    public Value maxValue() {
        if (root == null) {
            return null;
        }
        return getMax(root).value;
    }

    @Override
    public Key floor(@NotNull Key key) {
        Node result = findFloor(root, key);
        return result == null ? null : result.key;
    }

    @Override
    public Key ceil(@NotNull Key key) {
        Node result = findCeil(root, key);
        return result == null ? null : result.key;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public int height() {
        return getHeight(root);
    }

    private Node getMin(Node start) {
        Node current = start;
        while (current.left != null) {
            current = current.left;
        }
        return current;
    }

    // <=
    private Node findFloor(Node start, Key key) {
        if (start == null) {
            return null;
        }

        int compare = key.compareTo(start.key);
        if (compare == 0) {
            return start;
        }
        if (compare < 0) {
            return findFloor(start.left, key);
        }

        Node part = findFloor(start.right, key);
        return part == null ? start : part;
    }

    private Node findCeil(Node start, Key key) {
        if (start == null) {
            return null;
        }

        int compare = key.compareTo(start.key);
        if (compare == 0) {
            return start;
        }

        if (compare > 0) {
            return findCeil(start.right, key);
        }

        Node part = findCeil(start.left, key);
        return part == null ? start : part;
    }

    private Node getMax(Node start) {
        Node current = start;
        while (current.right != null) {
            current = current.right;
        }
        return current;
    }

    private int getHeight(Node node) {
        return node == null ? 0 : node.height;
    }

    private void fixHeight(Node node) {
        node.height = 1 + Math.max(getHeight(node.left), getHeight(node.right));
    }

    private Node putElement(Node node, Key key, Value value) {
        if (node == null) {
            size++;
            return new Node(key, value, null, null, 1);
        }

        int compare = key.compareTo(node.key);
        if (compare > 0) {
            node.right = putElement(node.right, key, value);
        } else if (compare < 0) {
            node.left = putElement(node.left, key, value);
        } else {
            node.value = value;
        }

        fixHeight(node);
        return balance(node);
    }

    private Node balance(Node node) {
        if (checkHeights(node) == 2) {
            if (checkHeights(node.left) < 0) {
                node.left = rotateLeft(node.left);
            }
            return rotateRight(node);
        }

        if (checkHeights(node) == 2) {
            if (checkHeights(node.right) > 0) {
                node.right = rotateRight(node.right);
            }
            return rotateLeft(node);
        }
        return node;
    }

    private int checkHeights(Node node) {
        return getHeight(node.left) - getHeight(node.right);
    }

    private Node rotateRight(Node node) {
        Node changer = node.left;
        node.left = changer.right;
        changer.right = node;
        fixHeight(changer);
        fixHeight(node);
        return changer;
    }

    private Node rotateLeft(Node node) {
        Node changer = node.left;
        node.right = changer.right;
        changer.left = node;
        fixHeight(changer);
        fixHeight(node);
        return changer;
    }

    private Node removeElement(Node node, Key key) {
        if (node == null) {
            deleted = null;
            return null;
        }

        int compare = key.compareTo(node.key);
        if (compare < 0) {
            node.left = removeElement(node.left, key);
        } else if (compare > 0) {
            node.right = removeElement(node.right, key);
        } else {
            deleted = node.value;
            node = inRemove(node);
            size--;
        }
        return node;
    }

    private Node inRemove(Node node) {
        if (node.right == null) {
            return node.left;
        }

        if (node.left == null) {
            return node.right;
        }

        Node current = node;
        node = getMin(node.right);
        node.right = changeMinLeft(current.right);
        node.left = current.left;
        return node;
    }

    private Node changeMinLeft(Node node) {
        if (node.left == null) {
            return node.right;
        }
        node.left = changeMinLeft(node.left);
        return node;
    }
}
