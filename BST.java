/**
 * Implements binary search trees.
 *
 * @author Jenna Paczkowski
 * @version Fall 2025
 */
public class BST<E extends Comparable<E>> extends BinaryTree<E> implements BST_Ops<E> {


    public BST(E value){
        super(value);
    }

    public BST(E data, BinaryTree<E> left, BinaryTree<E> right){
        super(data, left, right);
    }

    public void setLeft(BinaryTree<E> left) {
        if ((left == null)||(left instanceof BST<E>)) {
          super.setLeft(left);
        } else {
          throw new UnsupportedOperationException("Only BST children allowed.");
        }
    }

    public void setRight(BinaryTree<E> right) {
        if ((right == null)||(right instanceof BST<E>)) {
          super.setRight(right);
        } else {
          throw new UnsupportedOperationException("Only BST children allowed.");
        }
    }

    public BST<E> getLeft(){
        return (BST<E>) super.getLeft();
    }

    public BST<E> getRight(){
        return (BST<E>) super.getRight();
    }


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
