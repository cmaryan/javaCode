import org.junit.*;
import static org.junit.Assert.*;
/** 
 * This class will test the MinHeap Class implementation
 */
public class TestMinHeap{
	/**
	 * MinHeap test fixture
	 */
	private MinHeap<Integer> heap;
	private MinHeap<Integer> heap2;
	private MinHeap<Integer> heap3;
	private Integer[] array = new Integer[]{1,2,4,5,6,7,8};
	private Integer[] array2 = new Integer[]{5,2,8,4,1,6,7};
	/**
	 * Before every test, preform the following setup
	 */
	@Before
	public void setup(){
		heap = new MinHeap<Integer>();
	}
	/**
	 * Tests the constructors for the MinHeap and ensures they are in order if you pass an array
	 */
	@Test
	public void constructorTest(){
		/* with no argument, the heap should be empty */
		assertTrue(this.heap.isEmpty());
		String string = "1 2 4 5 6 7 8";
		/* being out of order initially, it should be rearranged into this order,
		 * which although it is not in the same order as the other, it does follow
		 * the MinHeap priority */
		String string2 = "1 2 6 4 5 8 7";
		heap3 = new MinHeap<Integer>(array2);
		heap2 = new MinHeap<Integer>(array);
		/* also tests the toString() method */
		assertEquals(string,heap2.toString());
		assertEquals(string2,heap3.toString());
	}
	/** 
	 * Tests the insert method of MinHeap
	 */
	@Test
	public void insertTest(){
		Integer one = new Integer(1);
		Integer twenty = new Integer(20);
		Integer five = new Integer(5);
		Integer six = new Integer(6);
		String string = "5 20";
		String string2 = "1 6 5 20";
		heap.insert(twenty);
		/* it should not be empty now */
		assertFalse(heap.isEmpty());
		/*five should now be above twenty in the heap */
		heap.insert(five);
		assertEquals(string,heap.toString());
		heap.insert(one);
		heap.insert(six);
		assertEquals(string2,heap.toString());
	}
	/**
	 * Tests the remove method of the MinHeap and be extension tests the moveValueDown method which is called when
	 * you remove.
	 */
	@Test
	public void removeTest(){
		Integer one = new Integer(1);
		Integer four = new Integer(4);
		Integer eight = new Integer(8);
		Integer nine = new Integer(9);
		Integer fifteen = new Integer(15);
		Integer twenty = new Integer(20);
		Integer thirtyfour = new Integer(34);
		Integer twelve = new Integer(12);
		String string = "4 9 8 34 15 20 12";
		String string2 = "8 9 12 34 15 20";
		heap.insert(one);
		heap.insert(four);
		heap.insert(eight);
		heap.insert(nine);
		heap.insert(fifteen);
		heap.insert(twenty);
		heap.insert(twelve);
		heap.insert(thirtyfour);
		/* the heap should now have 4 as the first element with 8 and 9 as its children and the rest following */
		heap.remove();
		assertEquals(string,heap.toString());
		/* the heap should now have moved the 9 to be the right child of 8 as the root */
		heap.remove();
		assertEquals(string2,heap.toString());
		heap.remove();
		heap.remove();
		heap.remove();
		heap.remove();
		heap.remove();
		heap.remove();
		/* with all removed, the heap should now be empty */
		assertTrue(heap.isEmpty());
	}
	/**
	 * Tests the exception of removing when the heap is empty
	 */
	@Test(expected=IllegalStateException.class)
	public void removeTest2(){
		heap.remove(); //should raise exception
	}
}