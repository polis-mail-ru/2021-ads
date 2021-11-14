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

    @Override
    public void put(@NotNull Key key, @NotNull Value value) {
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
                newNode.height = nodeToDelete.height;
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
        for (Node current : deque) {
            if (current.key.compareTo(key) <= 0) {
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

    private void balance(@NotNull Node inputNode, @NotNull Node parentNode) {
        Node currentNode = inputNode;
        boolean hasSmthChanged = false;
        if (factor(currentNode) == 2) {
            hasSmthChanged = true;
            if (factor(currentNode.left) < 0) {
                currentNode.left = rotateLeft(currentNode.left);
            }
            currentNode = rotateRight(currentNode);
        } else if (factor(currentNode) == -2) {
            hasSmthChanged = true;
            if (factor(currentNode.right) > 0) {
                currentNode.right = rotateRight(currentNode.right);
            }
            currentNode = rotateLeft(currentNode);
        }

        if (hasSmthChanged) {
            parentNode.changeSon(inputNode, currentNode);
        }
    }

    private void balanceROOT() {
        if (factor(root) == 2) {
            if (factor(root.left) < 0) {
                root.left = rotateLeft(root.left);
            }
            root = rotateRight(root);
        } else if (factor(root) == -2) {
            if (factor(root.right) > 0) {
                root.right = rotateRight(root.right);
            }
            root = rotateLeft(root);
        }
    }

    // Balancing trace. Deque contains nodes from one of nodes to root
    // Deque must be not empty
    private void balanceBranch(Deque<Node> deque) {
        if (deque.isEmpty()) {
            throw new NullPointerException("Trace is empty, when trying balancing branch. Usually it is because root is null");
        }
        while (deque.size() > 1) {
            fixHeight(deque.getFirst());
            Node prev = deque.removeFirst();
            balance(prev, deque.getFirst());
        }
        fixHeight(root);
        balanceROOT();
    }
}
