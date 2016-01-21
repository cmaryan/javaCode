import org.junit.Test;
import static org.junit.Assert.*;

public class TestBinaryTree{
	
	@Test
	public void TestHeightofTreeWithSingleNode(){
		String[] seq = {"A"};
		BinaryTree<String> tree = new BinaryTree<String>(seq);

		assertEquals(0, tree.height());
	}

	@Test
	public void TestHeightofTreeWith2Nodes(){
		String[] seq = {"A", "B"};
		BinaryTree<String> tree = new BinaryTree<String>(seq);

		assertEquals(1, tree.height());
	}

	@Test
	public void TestHeightofTreeWith3Nodes(){
		String[] seq = {"A", "B", "C"};
		BinaryTree<String> tree = 
			new BinaryTree<String>(seq);

		assertEquals(1, tree.height());
	}

	@Test
	public void TestHeightofTreeWith4Nodes(){
		String[] seq = {"A", "B", "C", "D"};
		BinaryTree<String> tree = 
			new BinaryTree<String>(seq);

		assertEquals(2, tree.height());
	}

	@Test
	public void TestWidthtofTreeWithSingleNode(){
		String[] seq = {"A"};
		BinaryTree<String> tree = 
			new BinaryTree<String>(seq);

		assertEquals(1, tree.width());
	}

	@Test
	public void TestWidthtofTreeWith2Nodes(){
		String[] seq = {"A", "B"};
		BinaryTree<String> tree = 
			new BinaryTree<String>(seq);

		assertEquals(1, tree.width());
	}

	@Test
	public void TestWidthtofTreeWith3Nodes(){
		String[] seq = {"A", "B", "C"};
		BinaryTree<String> tree = 
			new BinaryTree<String>(seq);

		assertEquals(2, tree.width());
	}

	@Test
	public void TestWidthtofTreeWith4Nodes(){
		String[] seq = {"A", "B", "C", "D"};
		BinaryTree<String> tree = 
			new BinaryTree<String>(seq);

		assertEquals(2, tree.width());
	}

	@Test
	public void TestWidthtofTreeWith9Nodes(){
		String[] seq = {"A", "B", "C", "D", "E", "F", "G", "H", "I"};
		BinaryTree<String> tree = 
			new BinaryTree<String>(seq);

		assertEquals(4, tree.width());
	}

	@Test
	public void TestWithNullNodes1(){
		String nullSymbol = "0";
		String[] seq = {"A", "B", "C", "D", "0", "0", "G"};
		BinaryTree<String> tree = 
			new BinaryTree<String>(seq, nullSymbol);

		tree.inOrderTraverse();	
		assertEquals(2, tree.width());
		assertEquals(2, tree.height());
	}

	@Test
	public void TestWithNullNodes2(){
		String nullSymbol = "0";
		String[] seq = {"A", "B", "C", "D", "0", "0", "G", "H", "I", "0", "0", "0", "0","0", "J"};
		BinaryTree<String> tree = 
			new BinaryTree<String>(seq, nullSymbol);

		assertEquals("A B D H I C G J", tree.preOrderTraverse());	
		assertEquals("H I D B J G C A", tree.postOrderTraverse());	
		assertEquals("H D I B A C G J", tree.inOrderTraverse());	

		assertEquals(3, tree.width());
		assertEquals(3, tree.height());
	}

	@Test
	public void testBreadFirstTraverse1(){
		String[] seq = {"A", "B", "C", "D", "E", "F", "G", "H", "I"};
		BinaryTree<String> tree = 
			new BinaryTree<String>(seq);

		assertEquals(4, tree.width());
		assertEquals("A B C D E F G H I", tree.breadthFirstTraverse());	
	}

	@Test
	public void testBreadFirstTraverse2(){
		String nullSymbol = "0";
		String[] seq = {"A", "B", "C", "D", "0", "0", "G", "H", "I", "0", "0", "0", "0","0", "J"};
		BinaryTree<String> tree = 
			new BinaryTree<String>(seq, nullSymbol);
		assertEquals("A B C D G H I J", tree.breadthFirstTraverse());	
	}

	@Test(expected=IllegalArgumentException.class)
	public void testBuidTreeFromEmptySeq(){
		String nullSymbol = "0";
		String[] seq = {};
		BinaryTree<String> tree = 
			new BinaryTree<String>(seq, nullSymbol); // should raise exception		
	}	

	@Test(expected=IllegalArgumentException.class)
	public void testBuidTreeFromSeqWithFirstNullSymbol(){
		String nullSymbol = "0";
		String[] seq = {"0", "B", "C", "D", "0", "0", "G", "H", "I", "0"};
		BinaryTree<String> tree = 
			new BinaryTree<String>(seq, nullSymbol); // should raise exception		
	}	
}