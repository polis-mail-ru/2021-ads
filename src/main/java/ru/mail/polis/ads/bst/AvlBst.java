package ru.mail.polis.ads.bst;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * AVL implementation of binary search tree.
 */
public class AvlBst<Key extends Comparable<Key>, Value>
        implements Bst<Key, Value> {

    private Node root = null;
    private int size = 0;

    private class Node {
        Key key;
        Value value;
        Node left;
        Node right;
        int height;

        Node(Key key, Value value) {
            this.key = key;
            this.value = value;
            this.height = 1;
        }
    }

    @Override
    public Value get(@NotNull Key key) {
        Node current = root;
        while (current != null && current.key.compareTo(key) != 0) {
            if (key.compareTo(current.key) > 0) {
                current = current.right;
            } else {
                current = current.left;
            }
        }
        return current != null ? current.value : null;
    }

    @Override
    public void put(@NotNull Key key, @NotNull Value value) {
        root = put(root, key, value);
        size++;
    }

    @Override
    public Value remove(@NotNull Key key) {
        Value removed = delete(root, key);
        if (removed != null) {
            size--;
            return removed;
        }
        return null;
    }

    @Override
    public Key min() {
        Node min = min(root);
        return (min != null) ? min.key : null;
    }

    @Override
    public Value minValue() {
        Node min = min(root);
        return (min != null) ? min.value : null;
    }

    @Override
    public Key max() {
        Node max = max(root);
        return (max != null) ? max.key : null;
    }

    @Override
    public Value maxValue() {
        Node max = max(root);
        return (max != null) ? max.value : null;
    }

    @Override
    public Key floor(@NotNull Key key) {
        return floor(root, key);
    }

    @Override
    public Key ceil(@NotNull Key key) {
        return ceil(root, key);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public int height() {
        return height(root);
    }

    private Node put(Node x, Key key, Value value) {
        if (x == null) return new Node(key, value);
        if (key.compareTo(x.key) < 0) {
            x.left = put(x.left, key, value);
        } else if (key.compareTo(x.key) > 0) {
            x.right = put(x.right, key, value);
        } else {
            x.value = value;
            size--;
        }
        fixHeight(x);
        x = balance(x);
        return x;
    }

    private int height(Node x) {
        return x == null ? 0 : x.height;
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

    void fixHeight(@NotNull Node x) {
        x.height = 1 + Math.max(height(x.left), height(x.right));
    }

    private int factor(@NotNull Node x) {
        return height(x.left) - height(x.right);
    }

    private @NotNull Node rotateRight(@NotNull Node x) {
        Node temp = x.left;
        x.left = temp.right;
        temp.right = x;
        fixHeight(x);
        fixHeight(temp);
        return temp;
    }

    Node rotateLeft(@NotNull Node x) {
        Node temp = x.right;
        x.right = temp.left;
        temp.left = x;
        fixHeight(x);
        fixHeight(temp);
        return temp;
    }

    private Value delete(Node x, Key key) {
        if (x == null) {
            return null;
        }
        if (key == root.key) {
            Value deleted = root.value;
            root = innerDelete(root);
            return deleted;
        }
        Node current = x;
        Node parent = current;
        while (current != null) {
            if (key.compareTo(current.key) < 0) {
                parent = current;
                current = current.left;
                continue;
            }
            if (key.compareTo(current.key) > 0) {
                parent = current;
                current = current.right;
                continue;
            }
            if (key == current.key) {
                Value deleted = current.value;
                if (parent.left != null && parent.left.key == current.key) {
                    parent.left = innerDelete(current);
                } else {
                    parent.right = innerDelete(current);
                }
                return deleted;
            }
        }
        return null;
    }

    private Node innerDelete(@NotNull Node x) {
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

    private Node deleteMin(@NotNull Node x) {
        if (x.left == null) {
            return x.right;
        }
        x.left = deleteMin(x.left);
        return x;
    }

    private Node min(Node x) {
        if (x == null) {
            return null;
        }
        Node current = x;
        while (current.left != null) {
            current = current.left;
        }
        return current;
    }

    private Node max(Node x) {
        if (x == null) {
            return null;
        }
        Node current = x;
        while (current.right != null) {
            current = current.right;
        }
        return current;
    }

    private Key floor(Node x, Key key) {
        if (x == null) {
            return null;
        }
        if (x.key == key) {
            return x.key;
        }
        Key left = null;
        Key right = null;
        if (x.key.compareTo(key) > 0) {
            left = floor(x.left, key);
        }
        if (x.key.compareTo(key) < 0) {
            right = floor(x.right, key);
        }
        if (left == key || right == key) {
            return key;
        }
        if (right == null) {
            if (left == null) {
                return (x.key.compareTo(key) < 0) ? x.key : null;
            }
            return MaxFloor(left, x.key, key);
        }
        return MaxFloor(right, x.key, key);
    }

    private @Nullable Key MaxFloor(@NotNull Key first, Key second, Key floor) {
        Key max = (first.compareTo(floor) < 0) ? first
                : (second.compareTo(floor) < 0) ? second
                : null;
        if (max == null) {
            return null;
        }
        return findKeyByCondition(first, second, max
                , first.compareTo(max) > 0
                , first.compareTo(floor) < 0
                , second.compareTo(max) > 0
                , second.compareTo(floor) < 0);
    }

    private Key ceil(Node x, Key key) {
        if (x == null) {
            return null;
        }
        if (x.key == key) {
            return x.key;
        }
        Key left = null;
        Key right = null;
        if (x.key.compareTo(key) > 0) {
            left = ceil(x.left, key);
        }
        if (x.key.compareTo(key) < 0) {
            right = ceil(x.right, key);
        }
        if (left == key || right == key) {
            return key;
        }
        if (right == null) {
            if (left == null) {
                return (x.key.compareTo(key) > 0) ? x.key : null;
            }
            return minCeil(left, x.key, key);
        }
        return minCeil(right, x.key, key);
    }

    private @Nullable Key minCeil(@NotNull Key first, Key second, Key floor) {
        Key min = (first.compareTo(floor) > 0) ? first
                : (second.compareTo(floor) > 0) ? second
                : null;
        if (min == null) {
            return null;
        }
        return findKeyByCondition(first, second, min
                , first.compareTo(min) < 0
                , first.compareTo(floor) > 0
                , second.compareTo(min) < 0
                , second.compareTo(floor) > 0);
    }

    private Key findKeyByCondition(Key first, Key second, Key key, boolean b1, boolean b2, boolean b3, boolean b4) {
        if (b1 && b2) {
            key = first;
        }
        if (b3 && b4) {
            key = second;
        }
        return key;
    }

}
