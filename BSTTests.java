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
        Integer[][] gt3 = {{null, 7}};
        Integer[][] gt4 = {{5}, {6}, {7}, {9}, {10}};
        Integer[][] gt5 = {{5}, {6}, {9}, {10}};

        BST<Integer> tree = new BST<>(5);
        verifyBT(tree, gt1);

        //Insert
        tree.insert(7);
        verifyBT(tree, gt2);

        tree.insert(6);
        tree.insert(7);
        tree.insert(9);
        tree.insert(10);
        verifyBT(tree, gt4);

        //Delete Tests
        tree.deleteWithCopyLeft(5);
        verifyBT(tree, gt3);

        tree.deleteWithCopyLeft(7);
        verifyBT(tree, gt5);
        
        //Rotate Left


        //Rotate Right
        
    }
}