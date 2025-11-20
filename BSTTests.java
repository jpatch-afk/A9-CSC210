import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Unit tests for Binary Search Tree (BST) class.
 *
 * @author YOUR_NAME_HERE
 * @version Fall 2025
 */
public class BSTTests {
    /** Helper method: verifies that a BinaryTree has the expected structure and contents. */
    private static <T> void verifyBT(BinaryTree<? extends T> t, T[][] contents) {
        for (int i = 0; i <= contents.length; i++) {
            int nj = (int) Math.pow(2, i);
            for (int j = 0; j < nj; j++) {
                int h = (int) Math.pow(2, i - 1);
                int k = j;
                BinaryTree<?> node = t;

                while (h > 0 && node != null) {
                    if (k >= h) node = node.getRight();
                    else node = node.getLeft();
                    k = k % h;
                    h /= 2;
                }

                // Compare expected and actual structure
                if ((i == contents.length || contents[i][j] == null) && node != null) {
                    fail("Row " + i + " position " + j +
                         " should be null but found data: " + node.getData());
                } else if (i < contents.length && contents[i][j] != null) {
                    if (node == null) {
                        fail("Row " + i + " position " + j +
                             " should be " + contents[i][j] + " but found null");
                    } else {
                        assertEquals("Row " + i + " position " + j + 
                                     " expected " + contents[i][j] + 
                                     " but got " + node.getData(),
                                     contents[i][j], node.getData());
                    }
                }
            }
        }
    }

    // Sample tests...
    @Test
    public void testBSTInsertions() {
        Integer[][] gt1 = {{5}};
        Integer[][] gt2 = {{5},{null,7}};


        BST<Integer> tree = new BST<>(5);
        verifyBT(tree, gt1);

        tree.insert(7);
        verifyBT(tree, gt2);

    }

    @Test
    public void testInsertLeft(){
        Integer[][] expected = {{10}, {7, null}};

        BST<Integer> tree = new BST<>(10);

        tree.insert(7);
        verifyBT(tree, expected);
    }

    @Test
    public void testInsertRight(){
        Integer[][] expected = {{10}, {null, 12}};

        BST<Integer> tree = new BST<>(10);

        tree.insert(12);
        verifyBT(tree, expected);
    }

    @Test 
    public void testDuplicate(){
        Integer[][] expected = {{10}, {null, 12}};

        BST<Integer> tree = new BST<>(10);

        tree.insert(12);
        tree.insert(12); //duplicate node
        verifyBT(tree, expected);
    }

    @Test
    public void testDeleteLeaf(){
        Integer[][] first = {{10}, {7, 12}};
        Integer[][] second = {{10}, {7, null}};

        BST<Integer> tree = new BST<>(10);

        tree.insert(7);
        tree.insert(12);
        verifyBT(tree, first);

        tree.deleteWithCopyLeft(12);
        verifyBT(tree, second);
    }

    @Test 
    public void testDeleteOneChild(){
        Integer[][] first = {{10}, {7, 12}, {5, null, null, null}};
        Integer[][] second = {{10}, {5, 12}};

        BST<Integer> tree = new BST<>(10);
        tree.insert(7);
        tree.insert(12);
        verifyBT(tree, first);

        tree.deleteWithCopyLeft(7);
        verifyBT(tree, second); //5 is then promoted to where 7 was as its only child
    }

    @Test
    public void testDeleteTwoChildren(){
        Integer[][] first = {{10}, {7, 12}};
        Integer[][] first_delete = {{7}, {null, 12}};

        //Deleting root
        BST<Integer> tree = new BST<>(10);
        tree.insert(7);
        tree.insert(12);
        verifyBT(tree, first);

        tree.deleteWithCopyLeft(10);
        verifyBT(tree, first_delete);

        //Deleting another node with two children 
        Integer[][] second = {{10}, {7, 12}, {6, 8, 11, 13}};
        Integer[][] second_delete = {{10}, {6, 12}, {null, 8, 11, 13}};

        BST<Integer> new_tree = new BST<Integer>(10);
        new_tree.insert(7);
        new_tree.insert(12);
        new_tree.insert(6);
        new_tree.insert(8);
        new_tree.insert(11);
        new_tree.insert(13);
        verifyBT(new_tree, second);

        new_tree.deleteWithCopyLeft(7);
        verifyBT(new_tree, second_delete);
    }

    @Test
    public void testLeftRotate(){

        //root only has left child

        BST<Integer> first_root = new BST<>(12);
        first_root.insert(9);
        BST<Integer> new_root_1 = first_root.rotateLeft();
        assertSame(first_root, new_root_1);

        //more complex tree

        Integer[][] first = {{10}, {null, 20}, {15, 21, null, null}};
        Integer[][] afterRotate = {{20}, {10, 21}, {null, 15, null, null}};
        
        BST<Integer> second_root = new BST<>(10);
        second_root.insert(20);
        second_root.insert(15);
        second_root.insert(21);
        verifyBT(second_root, first);

        BST<Integer> new_root_2 = second_root.rotateLeft();
        verifyBT(new_root_2, afterRotate);
    }

    @Test
    public void testRightRotate(){

        //root only has right child

        BST<Integer> first_root = new BST<>(12);
        first_root.insert(15);
        BST<Integer> new_root_1 = first_root.rotateRight();
        assertSame(first_root, new_root_1);

        //more complex tree

        Integer[][] first = {{10}, {5, null}, {4, 9, null, null}};
        Integer[][] afterRotate = {{5}, {4, 10}, {null, 9, null, null}};

        BST<Integer> second_root = new BST<>(10);
        second_root.insert(5);
        second_root.insert(4);
        second_root.insert(9);
        verifyBT(second_root, first);

        BST<Integer> new_root_2 = second_root.rotateRight();
        verifyBT(new_root_2, afterRotate);
    }
}