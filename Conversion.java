/**
 * Class to implement tree conversions
 *
 * @author Jenna Paczkowski 
 * @version Fall 2025
 */
public class Conversion {
  
  /**
   * Converts a sorted array into a BST
   * @param <T> Type of the array and the BST
   * @param arr sorted array to turn into a BST
   * @return Binary Search Tree from the sorted array
   */
  public static <T extends Comparable<T>> BST<T> arrayToBST(T[] arr) {

    if(arr == null || arr.length == 0){
      return null; //cannot make a tree if the array doesn't exist 
    }

    else {
      return arrayToBSTHelp(arr, 0, arr.length - 1);
    }
  }

  /**
   * Recursive help method to turn a sorted array into a BST
   * @param <T> Type of the array and the BST
   * @param arr sorted array to turn into a BST
   * @param low_index smallest index 
   * @param high_index largest index
   * @return Binary Search Tree from the sorted array
   */
  public static <T extends Comparable<T>> BST<T> arrayToBSTHelp(T[] arr, int low_index, int high_index){

    if(low_index > high_index){ //base case
      return null; 
    }

    int mid_index = low_index + (high_index - low_index)/2; //gets the middle index to become the root to create the left + right subtrees 


    BST<T> root = new BST<T>(arr[mid_index]); //create the new tree based on middle index

    //Left and Right subtrees using recursion
    BST<T> leftTree = arrayToBSTHelp(arr, low_index, mid_index - 1);
    BST<T> rightTree = arrayToBSTHelp(arr, mid_index + 1, high_index);

    //adds the trees to the root tree
    root.setLeft(leftTree);
    root.setRight(rightTree);

    return root; 
  }

  /**
   * Converts a Binary Search Tree to a DLL
   * @param <S> Type of the list and the tree
   * @param t Tree to be converted in a DLL
   * @return DLL created from the initial Tree
   */
  public static <S extends Comparable<S>> DLL<S> binaryTreeToDLL(BinaryTree<S> t) {

    if(t == null) {
      return new DLL<>(); //cannot make a list if tree doesn't exist
    }
    else {
      BinaryTree<S>[] result = binaryTreeToDLLHelp(t);
      return new DLL<>(result[0], result[1]);
    }
  }

  /**
   * Recursive method to convert a Tree into a DLL
   * @param <S> Type of the list and the tree
   * @param t Tree to be converted in a DLL
   * @return BinaryTree List with a left list and right list 
   */
  @SuppressWarnings("unchecked")
  public static <S extends Comparable<S>> BinaryTree<S>[] binaryTreeToDLLHelp(BinaryTree<S> t){

    if(t == null){
      return null;
    }

    //Creates the two lists 
    BinaryTree<S>[] leftList = binaryTreeToDLLHelp(t.getLeft());
    BinaryTree<S>[] rightList = binaryTreeToDLLHelp(t.getRight());

    //Initializes the head and tail 
    BinaryTree<S> head = t;
    BinaryTree<S> tail = t;

    //Adds the left list to the root 
    if(leftList != null){
      leftList[1].setRight(t); //tail should point to the root as root is in the middle and left list is to the left
      t.setLeft(leftList[1]); //root should also point to the tail as it is doubly-linked list
      head = leftList[0]; // new head is the leftList's head 
    }

    if(rightList != null){
      rightList[0].setLeft(t); //head should point to the root like leftList points right
      t.setRight(rightList[0]); //root points left as it is a doubly-linked list
      tail = rightList[1]; //new tail is rightList's tail
    }
    
    BinaryTree<S>[] result = new BinaryTree[2];
    result[0] = head;
    result[1] = tail;
    return result; 
  }

}
