package ru.mail.polis.ads.bst;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * AVL implementation of binary search tree.
 */

// У АВЛ дерева есть баланс высоты
// Разница высот поддеревьев не должна отличаться более, чем на единицу
public class AvlBst<Key extends Comparable<Key>, Value> implements Bst<Key, Value> {

    private static final int LEFT_DISBALANCE = 2;
    private static final int RIGHT_DISBALANCE = -2;
    private int size = 0;
    private Node root = null;
    private Node min = null;
    private Node max = null;

    private Node deletedNode = null;

    @Override
    public Value get(@NotNull Key key) {
        if (isEmpty()) {
            return null;
        }

        return get(root, key);
    }

    private Value get(Node node, @NotNull Key key) {
        if (node == null) {
            return null;
        }

        int comparisonResult = node.key.compareTo(key);

        boolean equals = comparisonResult == 0;
        if (equals) {
            return node.value;
        }

        boolean lessThanCurrent = comparisonResult > 0;

        return lessThanCurrent ? get(node.left, key) : get(node.right, key);
    }

    @Override
    public void put(@NotNull Key key, @NotNull Value value) {
        if (isEmpty()) {
            root = new Node(key, value);
            size++;
            setNewMinOrMaxIfTrue(root);
        } else {
            root = put(root, key, value);
        }

        root = balanceNode(root);
    }

    private Node put(@NotNull Node node, @NotNull Key key, @NotNull Value value) {
        final int comparisonResult = node.key.compareTo(key);

        boolean equals = comparisonResult == 0;
        if (equals) {
            node.value = value;
            return node;
        }

        boolean lessThanCurrent = comparisonResult > 0;
        if (lessThanCurrent) {
            if (node.left == null) {
                node.left = new Node(key, value);
                setNewMinOrMaxIfTrue(node.left);
                size++;
            } else {
                node.left = put(node.left, key, value);
            }
        }

        boolean biggerThanCurrent = comparisonResult < 0;
        if (biggerThanCurrent) {
            if (node.right == null) {
                node.right = new Node(key, value);
                setNewMinOrMaxIfTrue(node.right);
                size++;
            } else {
                node.right = put(node.right, key, value);
            }
        }

        fixHeight(node);
        node = balanceNode(node);

        return node;
    }

    private Node balanceNode(Node node) {
        int factor = getFactor(node);

        if (factor == LEFT_DISBALANCE) {
            if (getFactor(node.left) < 0) {
                node.left = rotateLeft(node.left);
            }

            return rotateRight(node);
        }

        if (factor == RIGHT_DISBALANCE) {
            if (getFactor(node.right) > 0) {
                node.right = rotateRight(node.right);
            }

            return rotateLeft(node);
        }

        return node;
    }

    private Node rotateLeft(@NotNull Node node) {
        Node rightOfCurrent = node.right;
        node.right = rightOfCurrent.left;
        rightOfCurrent.left = node;

        fixHeight(node);
        fixHeight(rightOfCurrent);

        return rightOfCurrent;
    }

    private Node rotateRight(@NotNull Node node) {
        Node leftOrCurrent = node.left;
        node.left = leftOrCurrent.right;
        leftOrCurrent.right = node;

        fixHeight(node);
        fixHeight(leftOrCurrent);

        return leftOrCurrent;
    }

    private int getFactor(Node node) {
        return height(node.left) - height(node.right);
    }

    private void fixHeight(Node node) {
        node.height = Math.max(height(node.left), height(node.right)) + 1;
    }

    private int height(Node node) {
        return node == null ? 0 : node.height;
    }

    @Override
    public Value remove(@NotNull Key key) {
        if (isEmpty()) {
            return null;
        }

        root = delete(root, key);

        Value v = null;
        if (deletedNode != null) {
            v = deletedNode.value;

            if (min != null && deletedNode.key.equals(min.key)) {
                min = null;
            }

            if (min != null && deletedNode.key.equals(max.key)) {
                max = null;
            }

            deletedNode = null;
            size--;
        }

        return v;
    }

    public Node delete(@Nullable Node node, @NotNull Key key) {
        if (node == null) {
            return null;
        }

        if (node.key.equals(key)) {
            deletedNode = node;
            return innerDelete(node);
        }

        if (node.key.compareTo(key) > 0) {
            node.left = delete(node.left, key);
        }

        if (node.key.compareTo(key) < 0) {
            node.right = delete(node.right, key);
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
        node = min(node.right);
        node.right = deleteMin(tempNode.right);
        node.left = tempNode.left;

        return node;
    }

    @Override
    public Key min() {
        if (isEmpty()) {
            return null;
        }

        if (min == null) {
            updateMin();
        }

        return min.key;
    }

    @Override
    public Value minValue() {
        if (isEmpty()) {
            return null;
        }

        if (min == null) {
            updateMin();
        }

        return min.value;
    }

    @Override
    public Key max() {
        if (isEmpty()) {
            return null;
        }

        if (max == null) {
            updateMax();
        }

        return max.key;
    }

    @Override
    public Value maxValue() {
        if (isEmpty()) {
            return null;
        }

        if (max == null) {
            updateMax();
        }

        return max.value;
    }

    @Override
    public Key floor(@NotNull Key key) {
        if (isEmpty()) {
            return null;
        }

        Node curr = root;
        while (curr != null) {
            int comparisonResult = curr.key.compareTo(key);
            boolean equals = comparisonResult == 0;
            if (equals) {
                return curr.key;
            }

            boolean lessThanCurrent = comparisonResult > 0;
            if (lessThanCurrent) {
                curr = curr.left;
            }

            boolean biggerThanCurrent = comparisonResult < 0;
            if (biggerThanCurrent) {
                boolean currIsMaxLessThanKey = curr.right == null || curr.right.key.compareTo(key) > 0;
                if (currIsMaxLessThanKey) {
                    return curr.key;
                }

                curr = curr.right;
            }
        }

        return null;
    }

    @Override
    public Key ceil(@NotNull Key key) {
        if (isEmpty()) {
            return null;
        }

        Node curr = root;
        while (curr != null) {
            int comparisonResult = curr.key.compareTo(key);
            boolean equals = comparisonResult == 0;
            if (equals) {
                return curr.key;
            }

            boolean lessThanCurrent = comparisonResult > 0;
            if (lessThanCurrent) {
                boolean currIsMinBiggerThanKey = curr.left == null || (curr.left.right != null && curr.left.right.key.compareTo(key) < 0);
                if (currIsMinBiggerThanKey) {
                    return curr.key;
                }

                curr = curr.left;
            }

            boolean biggerThanCurrent = comparisonResult < 0;
            if (biggerThanCurrent) {
                curr = curr.right;
            }
        }

        return null;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public int height() {
        return height(root);
    }

    private Node deleteMin(Node node) {
        if (node == null) {
            return null;
        }

        Node curr = node;
        while (curr.left != null && curr.left.left != null) {
            curr = curr.left;
        }

        curr.left = curr.left == null ? null : curr.left.right;

        return curr.left;
    }

    private Node min(Node node) {
        if (node == null) {
            return null;
        }

        return node.left == null ? node : min(node.left);
    }

    private void updateMax() {
        Node curr = root;
        while (curr.right != null) {
            curr = curr.right;
        }

        max = curr;
    }

    private void updateMin() {
        Node curr = root;
        while (curr.left != null) {
            curr = curr.left;
        }

        min = curr;
    }

    private void setNewMinOrMaxIfTrue(Node addedNode) {
        setMinIfLess(addedNode);
        setMaxIfBigger(addedNode);
    }

    private void setMinIfLess(Node addedNode) {
        if (min == null || min.key.compareTo(addedNode.key) > 0) {
            min = addedNode;
        }
    }

    private void setMaxIfBigger(Node addedNode) {
        if (max == null || max.key.compareTo(addedNode.key) < 0) {
            max = addedNode;
        }
    }

    private class Node {
        @NotNull
        Key key;

        @NotNull
        Value value;
        Node left;
        Node right;
        int height;

        public Node(@NotNull Key key, @NotNull Value value) {
            this.key = key;
            this.value = value;
            this.height = 1;
        }
    }
}
