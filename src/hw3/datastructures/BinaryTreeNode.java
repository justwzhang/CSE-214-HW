//justin zhang 112615200
package hw3.datastructures;

import java.util.Arrays;
import java.util.List;

/**
 * @author Ritwik Banerjee and Justin Zhang
 */
public class BinaryTreeNode<E> {
    
    private E element;
    
    private BinaryTreeNode<E> left   = null;
    private BinaryTreeNode<E> right  = null;
    private BinaryTreeNode<E> parent = null;
    
    public BinaryTreeNode(E element) {
        this.element = element;
    }
    
    public E element() { return element; }
    
    public BinaryTreeNode<E> left() { return left; }
    
    public BinaryTreeNode<E> right() { return right; }
    
    public BinaryTreeNode<E> parent() { return parent; }
    
    public void setElement(E element) { this.element = element; }
    
    public void setLeft(BinaryTreeNode<E> node) { this.left = node; }
    
    public void setRight(BinaryTreeNode<E> node) { this.right = node; }
    
    public void setParent(BinaryTreeNode<E> node) { this.parent = node; }
    
    /**
     * This checks the equality between two BinaryTreeNodes which also in turn checks
     * if the entire tree is equal as well.
     *
     * @param o the other BinaryTreeNode that we wish to compare to.
     * @return <code>true</code> if the two nodes are equal, <code>false</code> otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if(this == o) return true;
        if(!(o instanceof BinaryTreeNode)) return false;
        BinaryTreeNode<E> temp = (BinaryTreeNode<E>)o;
        Boolean elementEq = this.element.equals(temp.element());
        if(temp.right() == null && this.right() != null)
            return false;
        if(temp.right() != null && this.right() == null)
            return false;
        if(temp.left() == null && this.left() != null)
            return false;
        if(temp.left() != null && this.left == null)
            return false;
        if(temp.right() != null && temp.left() != null && this.left != null && this.right != null) {
            Boolean childrenEq = this.left.equals(temp.left()) && this.right.equals(temp.right());
            return elementEq && childrenEq;
        }
        if(temp.right() != null && temp.left() == null && this.left == null && this.right != null) {
            Boolean childrenEq = this.right.equals(temp.right());
            return elementEq && childrenEq;
        }
        if(temp.right() == null && temp.left() != null && this.left != null && this.right == null) {
            Boolean childrenEq = this.left.equals(temp.left());
            return elementEq && childrenEq;
        }
        return elementEq;
    }
    
    /**
     * !!DO NOT MODIFY THIS CODE!!
     *
     * @return
     */
    @Override
    public int hashCode() {
        int result = element.hashCode();
        result = 31 * result + (left != null ? left.hashCode() : 0);
        result = 31 * result + (right != null ? right.hashCode() : 0);
        return result;
    }
    
    /** !!DO NOT MODIFY THIS CODE!! */
    private void print(String prefix, boolean isTail) {
        System.out.println(prefix + (isTail ? "'-- " : "|-- ") + element.toString());
        List<BinaryTreeNode<E>> children = Arrays.asList(left, right);
        for (int i = 0; i < children.size() - 1; i++) {
            if (children.get(i) != null)
                children.get(i).print(prefix + (isTail ? "    " : "|   "), false);
        }
        if (children.size() > 0 && children.get(children.size() - 1) != null) {
            children.get(children.size() - 1).print(prefix + (isTail ? "    " : "|    "), true);
        }
    }
    
    /** !!DO NOT MODIFY THIS CODE!! */
    public void print() {
        print("", true);
    }
}
