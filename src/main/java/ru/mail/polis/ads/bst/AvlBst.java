package ru.mail.polis.ads.bst;

import org.jetbrains.annotations.NotNull;

/**
 * AVL implementation of binary search tree.
 */

public class AvlBst<Key extends Comparable<Key>, Value> implements Bst<Key, Value> {

    private Node root;
    private int size;

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

    @Override
    public Value get(@NotNull Key key) {
        return get(root, key);
    }

    private Value get(Node node, Key key) {
        if (node == null) {
            return null;
        }
        if (key.compareTo(node.key) < 0) {
            return get(node.left, key);
        }
        if (key.compareTo(node.key) > 0) {
            return get(node.right, key);
        }
        return node.value;
    }



    @Override
    public void put(@NotNull Key key, @NotNull Value value) {
        root = put(root, key, value);
    }

    private Node put(Node node, @NotNull Key key, @NotNull Value value) {
        if (node == null) {
            size++;
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
        node = balance(node);
        return node;
    }

    @Override
    public Value remove(@NotNull Key key) {
        Value rez = get(root, key);
        if (rez == null) {
            return null;
        }
        root = remove(root, key);
        return rez;
    }

    private Node remove(Node node, Key key) {
        if (node == null) {
            return null;
        }
        if (key.compareTo(node.key) < 0) {
            node.left = remove(node.left, key);
        }
        if (key.compareTo(node.key) > 0) {
            node.right = remove(node.right, key);
        }
        if (key.compareTo(node.key) == 0) {
            size--;
            if (node.right == null) {
                return node.left;
            }
            if (node.left == null) {
                return node.right;
            }
            Node t = node;
            node = findMin(t.right);
            node.right = removeMin(t.right);
            node.left = t.left;
            fixHeight(node);
            node = balance(node);
        }
        fixHeight(node);
        return balance(node);
    }


    private Node findMin(Node node) {
        if (node == null) {
            return null;
        }
        return node.left != null ? findMin(node.left) : node;
    }

    private Node findMax(Node node) {
        if (node == null) {
            return null;
        }
        return node.right != null ? findMax(node.right) : node;
    }


    private Node removeMin(Node node) {
        if (node.left == null) {
            return node.right;
        }
        node.left = removeMin(node.left);
        return balance(node);
    }



    @Override
    public Key min() {
        Node min = findMin(root);
        return min == null ? null : min.key;
    }


    @Override
    public Value minValue() {
        Node min = findMin(root);
        return min == null ? null : min.value;
    }

    @Override
    public Key max() {
        Node max = findMax(root);
        return max == null ? null : max.key;
    }

    @Override
    public Value maxValue() {
        Node max = findMax(root);
        return max == null ? null : max.value;
    }

    @Override
    public Key floor(@NotNull Key key) {
        //наибольшее целое число, которое меньше или равно аргументу
        Node floor = floor(root, key);
        if (floor == null) {
            return null;
        }
        return floor.key;
    }

    private Node floor(Node node, Key key) {
        if (node == null) {
            return null;
        }
        if (key.compareTo(node.key) == 0) {
            return node;
        }
        if (key.compareTo(node.key) < 0) {
            return floor(node.left, key);
        }
        Node temp = floor(node.right, key);
        return (temp == null || temp.key.compareTo(key) > 0) ? node : temp;
    }

    @Override
    public Key ceil(@NotNull Key key) {
        //наименьшее целое число, которое больше или равно аргументу
        Node ceil = ceil(root, key);
        if (ceil == null) {
            return null;
        }
        return ceil.key;
    }

    private Node ceil(Node node, Key key) {
        if (node == null) {
            return null;
        }
        if (key.compareTo(node.key) == 0) {
            return node;
        }
        if (key.compareTo(node.key) > 0) {
            return ceil(node.right, key);
        }
        Node temp = ceil(node.left, key);
        return (temp == null || temp.key.compareTo(key) < 0) ? node : temp;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public int height() {
        return height(root);
    }

    public int height(Node node) {
        return node != null ? node.height : 0;
    }

    private void fixHeight(Node node) {
        if (node == null) {
            return;
        }
        node.height = (Math.max(height(node.left), height(node.right))) + 1;
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

    private int balanceFactor(Node x) {
        return x == null ? 0 : height(x.left) - height(x.right);
    }

    private Node balance(Node x) {
        if (x == null) {
            return null;
        }
        //fixHeight(x);
        if (balanceFactor(x) == 2) {
            if (balanceFactor(x.left) < 0) {
                x.left = rotateLeft(x.left);
            }
            return rotateRight(x);
        }
        if (balanceFactor(x) == -2) {
            if (balanceFactor(x.right) > 0) {
                x.right = rotateRight(x.right);
            }
            return rotateLeft(x);
        }
        return x;
    }
}
