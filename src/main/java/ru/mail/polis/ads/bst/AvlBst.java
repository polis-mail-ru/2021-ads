package ru.mail.polis.ads.bst;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * AVL implementation of binary search tree.
 */
public class AvlBst<Key extends Comparable<Key>, Value>
        implements Bst<Key, Value> {

    private int size = 0;
    private Node root = null;

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

        public Node changeSon(Node oldNode, Node newNode) {
            if (left != null && left.equals(oldNode)) {
                left = newNode;
                return oldNode;
            }
            if (right != null && right.equals(oldNode)) {
                right = newNode;
                return oldNode;
            }
            throw new RuntimeException("No such son");
        }
    }

    // Return trace to node, if node with such key not exist - return possible parent of node where it should stay
    @NotNull
    private Deque<Node> traceToNode(@NotNull Key key) {
        if (root == null) {
            return new ArrayDeque<>(0);
        }

        Deque<Node> deque = new ArrayDeque<>(getHeight(root));
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

    // Realization without recursion
    @Override
    public void put(@NotNull Key key, @NotNull Value value) {
//        root = putRecursion(root, key, value);
        if (root == null) {
            size++;
            root = new Node(key, value, 1);
            return;
        }

        Deque<Node> deque = traceToNode(key);
        Node current = deque.getFirst();
        if (key.compareTo(current.key) == 0) {
            current.value = value;
        } else if (key.compareTo(current.key) < 0) {
            size++;
            current.left = new Node(key, value, 1);
        } else if (key.compareTo(current.key) > 0) {
            size++;
            current.right = new Node(key, value, 1);
        }

        balanceBranch(deque);
    }

    // Realization with recursion
    @Deprecated
    private @NotNull Node putRecursion(Node x, @NotNull Key key, @NotNull Value value) {
        if (x == null) {
            size++;
            return new Node(key, value, 1);
        }
        if (key.compareTo(x.key) < 0) {
            x.left = putRecursion(x.left, key, value);
        } else if (key.compareTo(x.key) > 0) {
            x.right = putRecursion(x.right, key, value);
        } else {
            x.value = value;
        }
        fixHeight(x);
        return balance(x);
    }

    // Deletes node with the lowest key in tree with root input.
    // Returns deleted node
    // (Be careful with sons of deleted element!)
    // After this operation tree is unbalanced, be careful
    private Node deleteMin(Node input) {
        Deque<Node> deque;

        if (input.left == null) {
            // We need to delete input because it is the lowest one
            // First, find parent of this element
            deque = traceToNode(input.key);
        } else {
            // Moving from input to nodeToDelete. Finding it
            deque = new ArrayDeque<>(getHeight(input));

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
        balanceBranch(deque);
        return nodeToDelete;
    }

    // Realization with recursion
    @Deprecated
    private Node deleteMinRecursion(Node x) {
        if (x.left == null) {
            return x.right;
        }
        x.left = deleteMinRecursion(x.left);
        fixHeight(x);
        x = balance(x);
        return x;
    }

    private Node innerDelete(Node x) {
        if (x.right == null) {
            size--;
            return x.left;
        }
        if (x.left == null) {
            size--;
            return x.right;
        }
        Node nodeToDelete = x;
        x = deleteMin(nodeToDelete.right);
        nodeToDelete.key = x.key;
        nodeToDelete.value = x.value;
        fixHeight(nodeToDelete);
        return balance(nodeToDelete);
    }

    @Deprecated
    private @Nullable Node deleteRecursive(Node x, Key key) {
        if (x == null) {
            return null;
        }
        if (key.compareTo(x.key) < 0) {
            x.left = deleteRecursive(x.left, key);
        }
        if (key.compareTo(x.key) > 0) {
            x.right = deleteRecursive(x.right, key);
        }
        if (key.compareTo(x.key) == 0) {
            x = innerDelete(x);
            if (x == null) {
                return null;
            }
        }
        fixHeight(x);
        return balance(x);
    }

    @Override
    public Value remove(@NotNull Key key) {
        // Old realization
//        if (root == null) {
//            return null;
//        }
//        Value result = get(key);
//        if (result == null) {
//            return null;
//        }
//        root = deleteRecursive(root, key);
//        if (root != null) {
//            balance(root);
//        }
//        return result;


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
                return ans;
            }
            if (root.right == null) {
                root = root.left;
                size--;
                return ans;
            }
            Node newNode = deleteMin(nodeToDelete.right);
            nodeToDelete.key = newNode.key;
            nodeToDelete.value = newNode.value;
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
                parentOfNodeToDelete.changeSon(nodeToDelete, newNode);
            }
        }
        return ans;
    }

    private @NotNull Node min(@NotNull Node x) {
        while (x.left != null) {
            x = x.left;
        }
        return x;
    }

    @Override
    public Key min() {
        if (root == null) {
            return null;
        }
        return min(root).key;
    }

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

    @Override
    public Key max() {
        if (root == null) {
            return null;
        }
        return max(root).key;
    }

    @Override
    public Value maxValue() {
        if (root == null) {
            return null;
        }
        return max(root).value;
    }

    @Override
    public Key floor(@NotNull Key key) {
        if (root == null) {
            return null;
        }
        Deque<Node> deque = traceToNode(key);
        if (deque.getFirst().key.equals(key)) {
            return key;
        }
        for (Node current : deque) {
            if (current.key.compareTo(key) < 0) {
                return current.key;
            }
        }
        return null;
    }

    @Override
    public Key ceil(@NotNull Key key) {
        if (root == null) {
            return null;
        }
        Deque<Node> deque = traceToNode(key);
        if (deque.getFirst().key.equals(key)) {
            return key;
        }
        for (Node current : deque) {
            if (current.key.compareTo(key) > 0) {
                return current.key;
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
        return getHeight(root);
    }

    private int getHeight(@Nullable Node x) {
        return x == null ? 0 : x.height;
    }

    private void fixHeight(@NotNull Node x) {
        x.height = Math.max(getHeight(x.left), getHeight(x.right)) + 1;
    }

    private @NotNull Node rotateLeft(@NotNull Node nodeA) {
        Node nodeB = nodeA.right;
        nodeA.right = nodeB.left;
        nodeB.left = nodeA;
        fixHeight(nodeA);
        fixHeight(nodeB);
        return nodeB;
    }

    private @NotNull Node rotateRight(@NotNull Node nodeA) {
        Node nodeB = nodeA.left;
        nodeA.left = nodeB.right;
        nodeB.right = nodeA;
        fixHeight(nodeA);
        fixHeight(nodeB);
        return nodeB;
    }

    private int factor(@NotNull Node x) {
        return getHeight(x.left) - getHeight(x.right);
    }

    // Returns Node - useful for recursive methods
    @Deprecated
    private @NotNull Node balance(@NotNull Node x) {
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

    // void method - useful for non-recursive methods
    private void balance(@NotNull Node node, @NotNull Node parentNode) {
        boolean hasSmthChanged = false;
        if (factor(node) == 2) {
            hasSmthChanged = true;
            if (factor(node.left) < 0) {
                node.left = rotateLeft(node.left);
            }
            node = rotateRight(node);
        } else if (factor(node) == -2) {
            hasSmthChanged = true;
            if (factor(node.right) > 0) {
                node.right = rotateRight(node.right);
            }
            node = rotateLeft(node);
        }

        if (hasSmthChanged) {
            if (parentNode.key.compareTo(node.key) > 0) {
                parentNode.left = node;
            } else if (parentNode.key.compareTo(node.key) < 0) {
                parentNode.right = node;
            }
        }
    }

    private void balanceROOT() {
        Node x = root;
        if (factor(x) == 2) {
            if (factor(x.left) < 0) {
                x.left = rotateLeft(x.left);
            }
            x = rotateRight(x);
        } else if (factor(x) == -2) {
            if (factor(x.right) > 0) {
                x.right = rotateRight(x.right);
            }
            x = rotateLeft(x);
        }
        root = x;
    }

    private void balanceBranch(Deque<Node> deque) {
        while (deque.size() > 1) {
            fixHeight(deque.getFirst());
            Node prev = deque.removeFirst();
            balance(prev, deque.getFirst());
        }
        fixHeight(root);
        balanceROOT();
    }
}
