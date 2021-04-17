//justin zhang 112615200
package hw3.datastructures;

import java.util.LinkedList;
import java.util.List;

/**
 * @author Ritwik Banerjee and Justin Zhang
 */
public class PostOrderTraversal<E> extends Traversal<E> {
    private List<E> elements;

    /**
     * Traverses a tree in order and returns a list of all elements in a list.
     *
     * @param tree is the inputted binary tree structure
     * @return a list of the elements traversed postorder
     */
    @Override
    public List<E> of(BinaryTree<E> tree) {
        elements = new LinkedList<E>();
        postOrder(tree.root());
        return elements;
    }

    /**
     * This is a helper method thad does the traversing
     *
     * @param node is the input node to start the traversal
     */
    private void postOrder(BinaryTreeNode<E> node){
        if(node == null) return;
        postOrder(node.left());
        postOrder(node.right());
        elements.add(node.element());
    }
}
