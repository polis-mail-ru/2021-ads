package ru.mail.polis.ads.bst;

import org.jetbrains.annotations.NotNull;

/**
 * AVL implementation of binary search tree.
 */
public class AvlBst<Key extends Comparable<Key>, Value>
        implements Bst<Key, Value> {

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


        public void setHeight(int height) {
            this.height = height;
        }

        public void setLeft(Node left) {
            this.left = left;
        }

        public void setRight(Node right) {
            this.right = right;
        }

        public int getHeight() {
            return height;
        }

        public Node getLeft() {
            return left;
        }

        public Node getRight() {
            return right;
        }

        public Key getKey() {
            return key;
        }

        public Value getValue() {
            return value;
        }

        public void setValue(Value value) {
            this.value = value;
        }
    }

    private Node root;
    private int size = 0;

    @Override
    public Value get(@NotNull Key key) {
        Node node = findNode(key);
        return (node == null) ? null : node.getValue();
    }

    private Node findNode(Key key) {
        Node current = root;
        while (true) {
            if (current == null) {
                return null;
            }
            int result = current.getKey().compareTo(key);
            if (result == 0) {
                return current;
            }
            if (result > 0) {
                current = current.getLeft();
            } else {
                current = current.getRight();
            }
        }
    }

    @Override
    public void put(@NotNull Key key, @NotNull Value value) {
        root = put(root, key, value);
        fixHeight(root);
    }

    private Node put(Node current, Key key, Value value) {
        if (current == null) {
            size++;
            return new Node(key, value, 1);
        }
        if (current.getKey().compareTo(key) > 0) {
            current.setLeft(put(current.getLeft(), key, value));
        } else if (current.getKey().compareTo(key) < 0) {
            current.setRight(put(current.getRight(), key, value));
        } else {
            current.setValue(value);
        }
        fixHeight(current);
        return balance(current);
    }


    private Node balance(Node x) {
        int factor = factor(x);
        if (factor == 2) {
            if (factor(x.getLeft()) < 0) {
                rotateLeft(x.getLeft());
            }
            return rotateRight(x);
        } else if (factor == -2) {
            if (factor(x.getRight()) > 0) {
                rotateRight(x.getRight());
            }
            return rotateLeft(x);
        }
        return x;
    }

    private Node rotateLeft(Node x) {
        Node oldRight = x.getRight();
        x.setRight(oldRight.getLeft());
        oldRight.setLeft(x);
        fixHeight(oldRight.getLeft());
        fixHeight(oldRight);
        return oldRight;
    }

    private Node rotateRight(Node x) {
        Node oldLeft = x.getLeft();
        x.setLeft(oldLeft.getRight());
        oldLeft.setRight(x);
        fixHeight(oldLeft.getRight());
        fixHeight(oldLeft);
        return oldLeft;
    }

    private int factor(Node x) {
        int left = height(x.getLeft());
        int right = height(x.getRight());
        return left - right;
    }

    private void fixHeight(Node x) {
        if (x == null) {
            return;
        }
        int left = height(x.getLeft());
        int right = height(x.getRight());
        x.setHeight(Math.max(left, right) + 1);
    }

    @Override
    public Value remove(@NotNull Key key) {
        Value toDelete = get(key);
        if (toDelete == null) {
            return null;
        }
        root = delete(root, key);
        size--;
        return toDelete;
    }

    @Override
    public Key min() {
        Node min = findMin(root);
        return (min == null) ? null : min.getKey();
    }

    private Node findMin(Node start) {
        if (start == null) {
            return null;
        }
        Node current = start;
        while (current.getLeft() != null) {
            current = current.getLeft();
        }
        return current;
    }

    private Node delete(Node start, Key key) {
        if (start == null) {
            return null;
        }
        int result = start.getKey().compareTo(key);
        if (result > 0) {
            start.setLeft(delete(start.getLeft(), key));
        } else if (result < 0) {
            start.setRight(delete(start.getRight(), key));
        } else {
            start = innerDelete(start);
        }
        fixHeight(start);
        return start;
    }

    private Node innerDelete(Node x) {
        if (x.getRight() == null) {
            return x.getLeft();
        }
        if (x.getLeft() == null) {
            return x.getRight();
        }
        Node t = x;
        x = findMin(t.getRight());
        x.setRight(deleteMin(t.right));
        x.setLeft(t.getLeft());
        return x;
    }

    private Node deleteMin(Node start) {
        if (start.getLeft() == null) {
            return start.getRight();
        }
        start.setLeft(deleteMin(start.getLeft()));
        fixHeight(start);
        return start;
    }

    private Node findMax(Node start) {
        if (start == null) {
            return null;
        }
        Node current = start;
        while (current.getRight() != null) {
            current = current.getRight();
        }
        return current;
    }

    @Override
    public Value minValue() {
        Node min = findMin(root);
        return (min == null) ? null : min.getValue();
    }


    @Override
    public Key max() {
        Node max = findMax(root);
        return (max == null) ? null : max.getKey();
    }

    @Override
    public Value maxValue() {
        Node max = findMax(root);
        return (max == null) ? null : max.getValue();
    }

    @Override
    public Key floor(@NotNull Key key) {
        Node floor = floor(root, key);
        return (floor == null) ? null : floor.getKey();
    }

    private Node floor(Node start, Key key) {
        if (start == null) {
            return null;
        }
        int result = start.getKey().compareTo(key);
        if (result > 0) {
            Node next = floor(start.getLeft(), key);
            if (next == null) {
                return (start.getKey().compareTo(key) < 0) ? start : null;
            }
            return next;
        }
        if (result < 0) {
            Node next = floor(start.getRight(), key);
            if (next == null) {
                return (start.getKey().compareTo(key) < 0) ? start : null;
            }
            return next;
        }
        return start;
    }

    @Override
    public Key ceil(@NotNull Key key) {
        Node node = ceil(root, key);
        return (node == null) ? null : node.getKey();
    }

    private Node ceil(Node start, Key key) {
        if (start == null) {
            return null;
        }
        int result = start.getKey().compareTo(key);
        if (result > 0) {
            Node next = ceil(start.getLeft(), key);
            if (next == null) {
                return (start.getKey().compareTo(key) > 0) ? start : null;
            }
            return next;
        }
        if (result < 0) {
            Node next = ceil(start.getRight(), key);
            if (next == null) {
                return (start.getKey().compareTo(key) > 0) ? start : null;
            }
            return next;
        }
        return start;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public int height() {
        return height(root);
    }

    private int height(Node x) {
        return (x == null) ? 0 : x.getHeight();
    }
}
