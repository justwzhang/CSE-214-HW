//justin zhang 112615200
package hw3.datastructures;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.LinkedList;

/**
 * @author Ritwik Banerjee and Justin Zhang
 */
public class BinarySearchTree<T extends Comparable<T>> implements BinaryTree<T> {

    private BinaryTreeNode<T> root;
    private int size;
    private BinaryTreeNode<T> originalRoot;

    /**
     * !!DO NOT MODIFY THIS CODE!!
     * Consrtucts a binary search tree consisting of items from the given collection. Duplicates in the collection are
     * ignored, i.e., every item will be considered only once for the tree being constructed.
     *
     * @param collection the given collection
     */
    public BinarySearchTree(Collection<T> collection) {
        this();
        for (T t : collection)
            add(t);
    }

    /**
     * !!DO NOT MODIFY THIS CODE!!
     * Constructs an empty binary search tree.
     */
    private BinarySearchTree() {
        root = null;
        size = 0;
    }

    /**
     * This takes in an element to add and it inserts it into
     * the Binary Search Tree based and its compare to value.
     *
     * @param t the element to input into the Binary Tree.
     */
    @Override
    public void add(T t) {
        BinaryTreeNode<T> temp = new BinaryTreeNode<T>(t);
        if (root == null) {
            root = temp;
            originalRoot = root;
            size++;
            return;
        }
        if (root.element().compareTo(temp.element()) > 0) {
            if (root.left() == null) {
                root.setLeft(temp);
                temp.setParent(root);
                root = originalRoot;
                size++;
                return;
            } else {
                root = root.left();
                add(t);
                root = originalRoot;
                return;
            }

        }
        if (root.element().compareTo(temp.element()) < 0) {
            if (root.right() == null) {
                root.setRight(temp);
                temp.setParent(root);
                root = originalRoot;
                size++;
            } else {
                root = root.right();
                add(t);
                root = originalRoot;
            }

        }
    }

    /**
     * This takes in an element that is to be removed from the
     * Binary Search tree. This tree traverses down the tree
     * based on the compareTo values of the two elements.
     *
     * @param t the element to be deleted from the Binary search tree.
     */
    @Override
    public void remove(T t) {
        if (root == null) {
            return;
        }
        if (root.element().compareTo(t) != 0) {
            while (root != null && root.element().compareTo(t) != 0) { //searches for that node
                if (root.element().compareTo(t) < 0) {
                    root = root.right();
                    continue;
                }
                if (root.element().compareTo(t) > 0) {
                    root = root.left();
                    continue;
                }
            }
        }

        if (root == null) {
            root = originalRoot;
            return;
        }
        if (root.left() == null && root.right() == null) { //removes leaf node
            root = root.parent();
            if (root.element().compareTo(t) < 0) { //is right child
                root.setRight(null);
                root = originalRoot;
                size--;
                return;
            }
            if (root.element().compareTo(t) > 0) {//is right child
                root.setLeft(null);
                root = originalRoot;
                size--;
                return;
            }
            if (root.element().compareTo(t) == 0 && root.right().element().compareTo(t) == 0) {
                root.setRight(null); //after two child swap, new node to be deleted is equal and a right child
                root = originalRoot;
                size--;
                return;
            }
        }
        if (((root.left() == null) && (root.right() != null)) || ((root.left() != null) && (root.right() == null))) { //removes with only one child
            if (root == originalRoot) {//root node to be deleted
                if (root.right() == null) {
                    root = root.left();
                    originalRoot = root;
                    root.setParent(null);
                    size--;
                } else {
                    root = root.right();
                    originalRoot = root;
                    root.setParent(null);
                    size--;
                }
                return;
            }
            if (root.left() == null && root.parent().left() == root) {//has a right child and is a left child
                root.parent().setLeft(root.right());
                root.right().setParent(root.parent());
                root = originalRoot;
                size--;
                return;
            }
            if (root.left() == null && root.parent().right() == root) {//has a right child and is a right child
                root.parent().setRight(root.right());
                root.right().setParent(root.parent());
                root = originalRoot;
                size--;
                return;
            }
            if (root.right() == null && root.parent().left() == root) {//has a left child and is a left child
                root.parent().setLeft(root.left());
                root.left().setParent(root.parent());
                root = originalRoot;
                size--;
                return;
            }
            if (root.right() == null && root.parent().right() == root) {//has a left child and is a right child
                root.parent().setRight(root.left());
                root.left().setParent(root.parent());
                root = originalRoot;
                size--;
                return;
            }
        }
        if ((root.left() != null) && (root.right() != null)) {//removes with two children
            BinaryTreeNode<T> successorNode = successor(root.right());
            root.setElement(successorNode.element());
            root = root.right();
            remove(successorNode.element());
            root = originalRoot;
        }

    }

    /**
     * This is a helper method for the deletion algorithm which finds the next smallest successor
     * for a node to be deleted.
     *
     * @param node is the input node to look for the next smallest successor.
     * @return the smallest successor node
     */
    private BinaryTreeNode<T> successor(BinaryTreeNode<T> node) {
        if (node.left() == null && node.right() == null) {
            return node;
        }
        while (node.left() != null) {
            node = node.left();
        }
        return node;
    }

    /**
     * Returns the search path that starts at the root node of the tree, and ends at the node containing the specified
     * item. If such a node exists in the tree, it is the last object in the returned list. Otherwise, this method will
     * still return the path corresponding to the search for this item, but append a <code>null</code> element at the
     * end of the list.
     *
     * @param t the specified item
     * @return the search path, with a node containing the specified item as the last object in the list if the item is
     * found in the tree, and the <code>null</code> node if item is not found in the tree.
     */
    @Override
    public List<BinaryTreeNode<T>> find(T t) {
        List<BinaryTreeNode<T>> temp = new LinkedList<BinaryTreeNode<T>>();
        BinaryTreeNode<T> tempRoot = root;
        while (tempRoot != null) {
            if (tempRoot.element().compareTo(t) < 0) {
                temp.add(tempRoot);
                tempRoot = tempRoot.right();
                continue;
            }
            if (tempRoot.element().compareTo(t) > 0) {
                temp.add(tempRoot);
                tempRoot = tempRoot.left();
                continue;
            }
            if (tempRoot.element().compareTo(t) == 0) {
                temp.add(tempRoot);
                break;
            }
        }
        if (tempRoot == null) {
            temp.add(null);
        }

        return temp;
    }

    /**
     * !!DO NOT MODIFY THIS CODE!!
     */
    @Override
    public void print() {
        root.print();
    }

    /**
     * !!DO NOT MODIFY THIS CODE!!
     *
     * @return the root node of this tree
     */
    @Override
    public BinaryTreeNode<T> root() {
        return root;
    }

    /**
     * !!DO NOT MODIFY THIS CODE!!
     *
     * @return the size, i.e., the number of nodes in this tree
     */
    @Override
    public int size() {
        return size;
    }

}