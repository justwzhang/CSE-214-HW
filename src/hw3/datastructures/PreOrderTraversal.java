//justin zhang 112615200
package hw3.datastructures;

import java.util.LinkedList;
import java.util.List;

/**
 * @author Ritwik Banerjee and Justin Zhang
 */
public class PreOrderTraversal<E> extends Traversal<E> {
    private List<E> elements;

    /**
     * Traverses a tree in order and returns a list of all elements in a list.
     *
     * @param tree is the inputted binary tree structure
     * @return a list of the elements traversed preorder
     */
    @Override
    public List<E> of(BinaryTree<E> tree) {
        elements = new LinkedList<E>();
        preOrder(tree.root());
        return elements;
    }

    /**
     * This is a helper method thad does the traversing
     *
     * @param node is the input node to start the traversal
     */
    private void preOrder(BinaryTreeNode<E> node){
        if(node == null) return;
        elements.add(node.element());
        preOrder(node.left());
        preOrder(node.right());
    }
}
