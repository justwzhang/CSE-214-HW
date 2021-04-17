//justin zhang 112615200
package hw3.datastructures;

import java.util.List;
import java.util.LinkedList;

/**
 * @author Ritwik Banerjee and Justin Zhang
 */
public class InOrderTraversal<E> extends Traversal<E> {
    private List<E> elements;

    /**
     * Traverses a tree in order and returns a list of all elements in a list.
     *
     * @param tree is the inputted binary tree structure
     * @return a list of the elements traversed inorder
     */
    @Override
    public List<E> of(BinaryTree<E> tree) {
        elements = new LinkedList<E>();
        inOrder(tree.root());
        return elements;
    }

    /**
     * This is a helper method thad does the traversing
     *
     * @param node is the input node to start the traversal
     */
    private void inOrder(BinaryTreeNode<E> node){
        if(node == null) return;
        inOrder(node.left());
        elements.add(node.element());
        inOrder(node.right());
    }
}
