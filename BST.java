/**
 * Implements binary search trees.
 *
 * @author Jenna Paczkowski
 * @version Fall 2025
 */
public class BST<E extends Comparable<E>> extends BinaryTree<E> implements BST_Ops<E> {

    /**
     * Constructor that creates a tree with a root 
     * @param value value that becomes the root 
     */
    public BST(E value){
        super(value);
    }

    /**
     * Constructor that creates a tree with a left and a right child
     * @param data root
     * @param left value to create left child
     * @param right value to create right child
     */
    public BST(E data, BinaryTree<E> left, BinaryTree<E> right){
        super(data, left, right);
    }

    /**
     * Method to set the value for a left child
     * @param left value to set the left child
     */
    public void setLeft(BinaryTree<E> left) {
        if ((left == null)||(left instanceof BST<E>)) {
          super.setLeft(left);
        } else {
          throw new UnsupportedOperationException("Only BST children allowed.");
        }
    }

    /**
     * Method to set the value for a right child
     * @param right value to set the right child
     */
    public void setRight(BinaryTree<E> right) {
        if ((right == null)||(right instanceof BST<E>)) {
          super.setRight(right);
        } else {
          throw new UnsupportedOperationException("Only BST children allowed.");
        }
    }

    /**
     * Getter for left child
     * @return value of left child
     */
    public BST<E> getLeft(){
        return (BST<E>) super.getLeft();
    }

    /**
     * Getter for right child
     * @return value of right child
     */
    public BST<E> getRight(){
        return (BST<E>) super.getRight();
    }

    /**
     * Finds the node that corresponds to the given data
     * 
     * @param data value to be found
     * @return node that the given data corresponds to
     */
    public BST<E> lookup(E data){

        BinaryTree<E> tree = this;

        while(tree != null){
            E value = tree.getData(); //gets the data of the node
            if(data.equals(value)) {
                return (BST<E>) tree;
            }
            else if(data.compareTo(value) < 0){
                value = tree.getLeft().getData();
            }
            else if(data.compareTo(value) > 0){
                value = tree.getRight().getData();
            }
        }
        System.out.println("Node not found. Try inserting it instead.");
        return null;
    }

    /**
     * Inserts given data while preserving BST order
     * 
     * @param data data to be inserted
     */
    public void insert(E data){

        if(this.getData() == null){
            this.setData(data);
            return;
        }

        BinaryTree<E> tree = this;
        
        if(lookup(data) != null){
            System.out.println("Node already exists.");
        }
        else {
            while(true) {
                E value = tree.getData(); 
                if(data.compareTo(value) < 0){
                    if(tree.getLeft() == null){
                        tree.setLeft(new BST<E>(data));
                        break;
                    }
                    else {
                        value = tree.getLeft().getData();
                    }
                }
                if(data.compareTo(value) > 0){
                    if(tree.getRight() == null){
                        tree.setRight(new BST<E>(data));
                        break;
                    }
                    else{
                        value = tree.getRight().getData();
                    }
                }
            }
        }
    }

    /**
     * Deletes a node within the tree with copy left preserving order
     * 
     * @param evictee node to be deleted
     * @return new tree without the given evictee
     */
    public BST<E> deleteWithCopyLeft(E evictee){

        BST<E> newTree = this;

        if(newTree == null || newTree.getData() == null){
            return null;
        }

        if(lookup(evictee) == null){
            System.out.println("Node doesn't exist. Try again.");
            return newTree;
        }
        else {
            BST<E> node = lookup(evictee);
            if(node.isLeaf()){
                node = null;
                return newTree;
            }
            else if(node.getLeft() != null) {
                node = node.getLeft();
                return newTree;
            }
            else if(node.getRight() != null){
                node = node.getRight();
                return newTree;
            }
            else {
                BST<E> root = newTree.getLeft(); //gets root and finds the max by transversal and finds the rightmost node for copy left
                while (root.getRight() != null) {
                    root = root.getRight();
                }
                node.setData(root.getData());
            }
            return newTree;
        }
    }

    /**
     * Rotates an unbalanced tree with rotate left
     * 
     * @return new tree
     */
    public BST<E> rotateLeft(){

        BST<E> node = this;
        BST<E> newNode = node.getRight();

        if(newNode == null){
            return node; //cannot rotate left if there is no right child, so return tree
        }
        else {
           node.setRight(newNode.getLeft());
           newNode.setLeft(node);
        }
        return newNode;
    }

    /**
     * Rotates an unbalanced tree with rotate right
     * 
     * @return new tree
     */
    public BST<E> rotateRight(){

        BST<E> node = this;
        BST<E> newNode = node.getLeft();

        if(newNode == null){
            return node; //cannot rotate right if there is no left child, so return tree
        }
        else {
           node.setLeft(newNode.getRight());
           newNode.setRight(node);
        }
        return newNode;
    }
}
