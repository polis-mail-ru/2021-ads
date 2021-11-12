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
    }

    // Return trace to node, if no node with such key - return parent, where this node should stay
    @NotNull
    private Deque<Node> traceToNode(@NotNull Key key) {
        if (root == null) {
            return new ArrayDeque<>(0);
        }

        Deque<Node> deque = new ArrayDeque<>(getHeight(root));
        deque.addFirst(root);
        Node current = deque.getFirst();
        while (true) {
            if (key.compareTo(current.key) < 0) {
                current = current.left;
            } else if (key.compareTo(current.key) > 0) {
                current = current.right;
            } else if (key.compareTo(current.key) == 0) {
                break;
            }

            if (current == null) {
                return deque;
            }
            deque.addFirst(current);
            current = deque.getFirst();
        }
        return deque;
    }

    @Override
    public Value get(@NotNull Key key) {
        Node node = root;
        while (node != null) {
            if (node.key.compareTo(key) == 0) {
                return node.value;
            }
            if (node.key.compareTo(key) < 0) {
                node = node.right;
            } else {
                node = node.left;
            }
        }
        return node != null ? node.value : null;
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

    private void deleteMin(Node nodeToDelete) {
        Deque<Node> deque;

        if (nodeToDelete.left == null) {
            // Need to find father of this element
            deque = traceToNode(nodeToDelete.key);
            deque.removeFirst();
            Node parentOfNodeToDelete = deque.getFirst();

            if (parentOfNodeToDelete.left != null && parentOfNodeToDelete.left.equals(nodeToDelete)) {
                parentOfNodeToDelete.left = null;
                return;
            } else if (parentOfNodeToDelete.right != null && parentOfNodeToDelete.right.equals(nodeToDelete)) {
                parentOfNodeToDelete.right = null;
                return;
            }
            return;
        }

        deque = new ArrayDeque<>(getHeight(nodeToDelete));
        deque.addFirst(nodeToDelete);
        Node current = deque.getFirst();

        while (current.left != null) {
            current = current.left;
            deque.addFirst(current);
        }

        Node elementToDelete = deque.removeFirst();
        Node parentOfElementToDelete = deque.getFirst();
        parentOfElementToDelete.left = elementToDelete.right;

        balanceBranch(deque);
    }

    // Realization with recursion
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
            return x.left;
        }
        if (x.left == null) {
            return x.right;
        }
        Node t = x;
        x = min(t.right);
        deleteMin(t.right);
        x.right = t.right;
        x.left = t.left;
        fixHeight(x);
        return balance(x);
    }

    private Node delete(Node x, Key key) {
        if (x == null) {
            return null;
        }
        if (key.compareTo(x.key) < 0) {
            x.left = delete(x.left, key);
        }
        if (key.compareTo(x.key) > 0) {
            x.right = delete(x.right, key);
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
        if (root == null) {
            return null;
        }
        Value result = get(key);
        if (result == null) {
            return null;
        }
        root = delete(root, key);
        size--;
        if (root != null) {
            balance(root);
        }
        return result;
    }

//    private Node deleteMin(@NotNull Node x) {
//        Node node = x;
//        while (node.left != null) {
//            node = node.left;
//        }
//        node.left = node.right;
//        return x;
//    }

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
        if(deque.getFirst().key.equals(key)) {
            return key;
        }
        for (Node current: deque) {
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
        if(deque.getFirst().key.equals(key)) {
            return key;
        }
        for (Node current: deque) {
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
                return;
            } else if (parentNode.key.compareTo(node.key) < 0) {
                parentNode.right = node;
                return;
            }
            throw new RuntimeException("WE SHOUDN'T BE HERE");
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
