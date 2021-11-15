package ru.mail.polis.ads.bst;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * LLRB implementation of binary search tree.
 */
public class RedBlackBst<Key extends Comparable<Key>, Value>
        implements Bst<Key, Value> {

    private static final boolean BLACK = false;
    private static final boolean RED = true;
    private int size = 0;
    private Node root;

    private class Node {
        Key key;
        Value value;
        Node left;
        Node right;
        boolean color;

        public Node(Key key, Value value, boolean color) {
            this.key = key;
            this.value = value;
            this.color = color;
        }

        public void changeSon(Node oldNode, Node newNode) {
            if (left != null && left.equals(oldNode)) {
                left = newNode;
                return;
            }
            if (right != null && right.equals(oldNode)) {
                right = newNode;
                return;
            }
            throw new RuntimeException("No such son");
        }
    }

    // Return trace to node, if node with such key not exist - return possible parent of node where it might stay
    private @NotNull Deque<Node> traceToNode(@NotNull Key key) {
        if (root == null) {
            return new ArrayDeque<>(0);
        }

        Deque<Node> deque = new ArrayDeque<>();
        Node currentNode = root;
        do {
            deque.addFirst(currentNode);
            if (key.compareTo(currentNode.key) < 0) {
                currentNode = currentNode.left;
            } else if (key.compareTo(currentNode.key) > 0) {
                currentNode = currentNode.right;
            } else if (key.compareTo(currentNode.key) == 0) {
                break;
            }
        } while (currentNode != null);
        return deque;
    }

    @Nullable
    @Override
    public Value get(@NotNull Key key) {
        Node currentNode = root;
        while (currentNode != null) {
            if (currentNode.key.compareTo(key) == 0) {
                return currentNode.value;
            }
            if (currentNode.key.compareTo(key) < 0) {
                currentNode = currentNode.right;
            } else {
                currentNode = currentNode.left;
            }
        }
        return currentNode != null ? currentNode.value : null;
    }

    @Override
    public void put(@NotNull Key key, @NotNull Value value) {
        if (root == null) {
            size++;
            root = new Node(key, value, RED);
            return;
        }

        Deque<Node> deque = traceToNode(key);
        Node current = deque.getFirst();
        if (key.compareTo(current.key) == 0) {
            current.value = value;
        } else if (key.compareTo(current.key) < 0) {
            size++;
            current.left = new Node(key, value, RED);
        } else if (key.compareTo(current.key) > 0) {
            size++;
            current.right = new Node(key, value, RED);
        }
        fixBranch(deque);
        root.color = BLACK;
    }

    private Node fix(Node node) {
        if (!isRed(node.left) && isRed(node.right)) {
            node = rotateLeft(node);
        }
        if (isRed(node.left) && isRed(node.left.left)) {
            node = rotateRight(node);
        }
        if (isRed(node.left) && isRed(node.right)) {
            flipColours(node);
        }
        return node;
    }

    private void fix(Node input, Node parentNode) {
        Node node = input;
        if (!isRed(node.left) && isRed(node.right)) {
            node = rotateLeft(node);
        }
        if (isRed(node.left) && isRed(node.left.left)) {
            node = rotateRight(node);
        }
        if (isRed(node.left) && isRed(node.right)) {
            flipColours(node);
        }
        parentNode.changeSon(input, node);
    }

    private void fixROOT() {
        Node node = root;
        if (!isRed(node.left) && isRed(node.right)) {
            node = rotateLeft(node);
        }
        if (isRed(node.left) && isRed(node.left.left)) {
            node = rotateRight(node);
        }
        if (isRed(node.left) && isRed(node.right)) {
            flipColours(node);
        }
        root = node;
    }

    private void fixBranch(Deque<Node> deque) {
        if (deque.isEmpty()) {
            throw new NullPointerException("Trace is empty, when trying balancing branch. Usually it is because root is null");
        }
        while (deque.size() > 1) {
            Node prev = deque.removeFirst();
            fix(prev, deque.getFirst());
        }
        fixROOT();
    }

    // Deletes node with the lowest key in tree with root input.
    // Returns deleted node or null if root is empty
    // (Be careful with sons of deleted element!)
    // After this operation tree is balanced, be careful
    private @NotNull Node deleteMin(@NotNull Node input) {
        if (root == null) {
            throw new NullPointerException("Root is null, when deleting min");
        }
        Deque<Node> deque;

        if (input.left == null) {
            // We need to delete input because it is the lowest one
            // First, find parent of this element
            deque = traceToNode(input.key);
        } else {
            // Moving from input to nodeToDelete. Trying to find the lowest one
            deque = new ArrayDeque<>();

            Node current = input;
            while (current.left != null) {
                deque.addFirst(current);
                current = current.left;
            }
        }
        Node nodeToDelete = deque.removeFirst();
        Node parentOfNodeToDelete = deque.getFirst();
        parentOfNodeToDelete.changeSon(nodeToDelete, nodeToDelete.right);
        size--;
        fixBranch(deque);
        return nodeToDelete;
    }

    @Nullable
    @Override
    public Value remove(@NotNull Key key) {
        if (root == null) {
            return null;
        }
        Deque<Node> deque = traceToNode(key);
        Node nodeToDelete = deque.removeFirst();
        if (!nodeToDelete.key.equals(key)) {
            // No key with such element
            return null;
        }
        Value ans = nodeToDelete.value;
        if(deque.isEmpty()) {
            // Removing root
            if(!root.equals(nodeToDelete)) {
                throw new RuntimeException("After tracing size of deque was 1, but it wasn't root");
            }
            if (root.left == null) {
                root = root.right;
                size--;
            } else if (root.right == null) {
                root = root.left;
                size--;
            } else {
                Node newNode = deleteMin(root.right);
                // nodeToDelete equal to root, but I can't write root here
                nodeToDelete.key = newNode.key;
                // Question: if I write "root" instead of "nodeToDelete", "ans" will change to, because "ans" is only
                // shallow copy of root.value. So question: how can we make deep copy of generic?
                nodeToDelete.value = newNode.value;
            }
        } else {
            Node parentOfNodeToDelete = deque.getFirst();
            if (nodeToDelete.left == null) {
                size--;
                parentOfNodeToDelete.changeSon(nodeToDelete, nodeToDelete.right);
            } else if (nodeToDelete.right == null) {
                size--;
                parentOfNodeToDelete.changeSon(nodeToDelete, nodeToDelete.left);
            } else {
                Node newNode = deleteMin(nodeToDelete.right);
                newNode.left = nodeToDelete.left;
                newNode.right = nodeToDelete.right;
                newNode.color = nodeToDelete.color;
                parentOfNodeToDelete.changeSon(nodeToDelete, newNode);
            }
        }
        return ans;
    }

    private boolean isRed(Node node) {
        return node != null && node.color;
    }

    private Node rotateRight(Node y) {
        Node x = y.left;
        y.left = x.right;
        x.right = y;
        x.color = y.color;
        y.color = RED;
        return x;
    }

    private Node rotateLeft(Node x) {
        Node y = x.right;
        x.right = y.left;
        y.left = x;
        y.color = x.color;
        x.color = RED;
        return y;
    }

    private Node moveRedLeft(Node x) {
        flipColours(x);
        if (isRed(x.right.left)) {
            x.right = rotateRight(x.right);
            x = rotateLeft(x);
            flipColours(x);
        }
        return x;
    }

    private Node moveRedRight(Node x) {
        flipColours(x);
        if (isRed(x.left.left)) {
            x = rotateRight(x);
            flipColours(x);
        }
        return x;
    }

    private void flipColours(Node node) {
        node.color = !node.color;
        node.left.color = !node.left.color;
        node.right.color = !node.right.color;
    }

    private @NotNull Node min(@NotNull Node x) {
        while (x.left != null) {
            x = x.left;
        }
        return x;
    }

    @Nullable
    @Override
    public Key min() {
        if (root == null) {
            return null;
        }
        return min(root).key;
    }

    @Nullable
    @Override
    public Value minValue() {
        if (root == null) {
            return null;
        }
        return min(root).value;
    }

    private @NotNull Node max(@NotNull Node x) {
        while (x.right != null) {
            x = x.right;
        }
        return x;
    }

    @Nullable
    @Override
    public Key max() {
        if (root == null) {
            return null;
        }
        return max(root).key;
    }

    @Nullable
    @Override
    public Value maxValue() {
        if (root == null) {
            return null;
        }
        return max(root).value;
    }

    @Nullable
    @Override
    public Key floor(@NotNull Key key) {
        if (root == null) {
            return null;
        }
        Deque<Node> deque = traceToNode(key);
        for (Node current : deque) {
            if (current.key.compareTo(key) <= 0) {
                return current.key;
            }
        }
        return null;
    }

    @Nullable
    @Override
    public Key ceil(@NotNull Key key) {
        if (root == null) {
            return null;
        }
        Deque<Node> deque = traceToNode(key);
        for (Node current : deque) {
            if (current.key.compareTo(key) >= 0) {
                return current.key;
            }
        }
        return null;
    }

    @Override
    public int size() {
        return size;
    }
}
