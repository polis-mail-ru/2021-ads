package ru.mail.polis.ads.bst;

import org.jetbrains.annotations.NotNull;

/**
 * AVL implementation of binary search tree.
 */
public class AvlBst<Key extends Comparable<Key>, Value> implements Bst<Key, Value> {
    
    private Node root;
    
    private int size = 0;

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
    
    private int height(Node node) {
        return node == null ? 0 : node.height;
    }

    private void fixHeight(Node node) {
        if(node == null) {
            return;
        }
        node.height = 1 + Math.max(height(node.left), height(node.right));
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

    private Node rotateRight(Node point) {
        Node temp = point.left;
        point.left = temp.right;
        temp.right = point;
        fixHeight(point);
        fixHeight(temp);
        return temp;
    }

    private Node rotateLeft(Node point) {
        Node temp = point.right;
        point.right = temp.left;
        temp.left = point;
        fixHeight(point);
        fixHeight(temp);
        return temp;
    }

    private int factor(Node node) {
        return height(node.left) - height(node.right);
    }

    private Node balance(Node node) {
        if(node == null) {
            return null;
        }
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

    private Value get(Node node, @NotNull Key key) {
        if (node == null) {
            return null;
        }
        int compare = key.compareTo(node.key);
        if (compare < 0) {
            return get(node.left, key);
        }
        if (compare > 0) {
            return get(node.right, key);
        }
        return node.value;
    }

    @Override
    public Value get(@NotNull Key key) {
        return get(this.root, key);
    }

    private Node put(Node node, @NotNull Key key, Value value) {
        if (node == null) {
            ++this.size;
            return new Node(key, value, 1);
        }
        int compare = key.compareTo(node.key);
        if (compare < 0) {
            node.left = put(node.left, key, value);
        } else if (compare > 0) {
            node.right = put(node.right, key, value);
        } else {
            node.value = value;
        }
        fixHeight(node);
        return balance(node);
    }

    @Override
    public void put(@NotNull Key key, @NotNull Value value) {
        this.root = put(this.root, key, value);
    }

    private Node deleteMin(Node node) {
        if (node.left == null) {
            return node.right;
        }
        node.left = deleteMin(node.left);
        return node;
    }

    private Node innerRemove(Node node) {
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

    private Node remove(Node node, @NotNull Key key) {
        if (node == null) {
            return null;
        }
        int compare = key.compareTo(node.key);
        if (compare < 0) {
            node.left = remove(node.left, key);
        } else if (compare > 0) {
            node.right = remove(node.right, key);
        } else {
            node = innerRemove(node);
            --this.size;
        }
        fixHeight(node);
        return balance(node);
    }

    @Override
    public Value remove(@NotNull Key key) {
        Value value = get(key);
        if (value == null) {
            return null;
        }
        this.root = remove(this.root, key);
        return value;
    }

    @Override
    public Key min() {
        Node node = min(this.root);
        if (node == null) {
            return null;
        }
        return node.key;
    }

    @Override
    public Value minValue() {
        Node node = min(this.root);
        if (node == null) {
            return null;
        }
        return node.value;
    }

    @Override
    public Key max() {
        Node node = max(this.root);
        if (node == null) {
            return null;
        }
        return node.key;
    }

    @Override
    public Value maxValue() {
        Node node = max(this.root);
        if (node == null) {
            return null;
        }
        return node.value;
    }

    private Node floor(Node node, Key key) {
        if (node == null) {
            return null;
        }
        int compare = key.compareTo(node.key);
        if (compare == 0) {
            return node;
        }
        if (compare < 0) {
            return floor(node.left, key);
        }
        Node subNode = floor(node.right, key);
        if (subNode == null) {
            return node;
        }
        return key.compareTo(subNode.key) < 0 ? node : subNode;
    }

    @Override
    public Key floor(@NotNull Key key) {
        Node node = floor(this.root, key);
        if (node == null) {
            return null;
        }
        return node.key;
    }

    private Node ceil(Node node, Key key) {
        if (node == null) {
            return null;
        }
        int compare = key.compareTo(node.key);
        if (compare == 0) {
            return node;
        }
        if (compare > 0) {
            return ceil(node.right, key);
        }
        Node subNode = ceil(node.left, key);
        if (subNode == null) {
            return node;
        }
        return key.compareTo(subNode.key) > 0 ? node : subNode;
    }

    @Override
    public Key ceil(@NotNull Key key) {
        Node node = ceil(this.root, key);
        if (node == null) {
            return null;
        }
        return node.key;
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public int height() {
        return height(this.root);
    }
}