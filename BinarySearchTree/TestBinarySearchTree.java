import org.junit.Test;
import static org.junit.Assert.*;

public class TestBinarySearchTree{
	
	@Test
	public void testInsertToEmptyTree(){		
		BinarySearchTree<Integer> tree = new BinarySearchTree<Integer>();
		tree.insert(5);
		assertEquals("5", tree.inOrderTraverse());

		tree.insert(3);
		assertEquals("3 5", tree.inOrderTraverse());

		tree.insert(6);
		assertEquals("3 5 6", tree.inOrderTraverse());
		
		tree.insert(7);
		assertEquals("3 5 6 7", tree.inOrderTraverse());
		assertEquals("5 3 6 7", tree.breadthFirstTraverse());
	}

	@Test
	public void testInsert1(){
		Integer[] seq = {5, 3, 7};
		BinarySearchTree<Integer> tree = new BinarySearchTree<Integer>(seq);
		assertEquals("3 5 7", tree.inOrderTraverse());
		tree.insert(6);
		assertEquals("3 5 6 7", tree.inOrderTraverse());
	}

	@Test
	public void testInsert2(){
		Integer[] seq = {5, 3, 7};
		BinarySearchTree<Integer> tree = new BinarySearchTree<Integer>(seq);
		assertEquals("3 5 7", tree.inOrderTraverse());
		tree.insert(2);
		assertEquals("2 3 5 7", tree.inOrderTraverse());
	}

	@Test
	public void testContainsExisting(){
		int nullSymbol = 0;
		Integer[] seq = {5, 3, 7, 0, 0, 6};
		BinarySearchTree<Integer> tree = new BinarySearchTree<Integer>(seq, nullSymbol);
		assertEquals("3 5 6 7", tree.inOrderTraverse());
		assertTrue(tree.contains(6));
	}

	@Test
	public void testContainsNonExisting(){
		int nullSymbol = 0;
		Integer[] seq = {5, 3, 7, 0, 0, 6};
		BinarySearchTree<Integer> tree = new BinarySearchTree<Integer>(seq, nullSymbol);
		assertEquals("3 5 6 7", tree.inOrderTraverse());
		assertFalse(tree.contains(9));
	}

	@Test
	public void testRemoveRoot1(){
		int nullSymbol = 0;
		Integer[] seq = {5, 3, 7, 0, 0, 6};
		BinarySearchTree<Integer> tree = new BinarySearchTree<Integer>(seq, nullSymbol);
		assertEquals("3 5 6 7", tree.inOrderTraverse());
		tree.remove(5);
		assertEquals("3 6 7", tree.inOrderTraverse());
	}

	@Test
	public void testRemoveRoot2(){
		int nullSymbol = 0;
		Integer[] seq = {5, 3, 7};
		BinarySearchTree<Integer> tree = new BinarySearchTree<Integer>(seq, nullSymbol);
		assertEquals("3 5 7", tree.inOrderTraverse());
		tree.remove(5);
		assertEquals("3 7", tree.inOrderTraverse());
	}

	@Test
	public void testRemoveRoot3(){
		int nullSymbol = 0;
		Integer[] seq = {5, 3};
		BinarySearchTree<Integer> tree = new BinarySearchTree<Integer>(seq, nullSymbol);
		assertEquals("3 5", tree.inOrderTraverse());
		tree.remove(5);
		assertEquals("3", tree.inOrderTraverse());
	}

	@Test
	public void testRemoveLeaf1(){
		int nullSymbol = 0;
		Integer[] seq = {5, 3};
		BinarySearchTree<Integer> tree = new BinarySearchTree<Integer>(seq, nullSymbol);
		assertEquals("3 5", tree.inOrderTraverse());
		tree.remove(3);
		assertEquals("5", tree.inOrderTraverse());
	}
}