import org.junit.*;
import static org.junit.Assert.*;
/**
 * This class will test the Unbounded Priority queue, focusing mostly on the limitless
 * property. This is because the test for minHeap tested most of the class already.
 */
public class TestUnboundedPriorityQueue{
	/**
	 * Queue test fixture
	 */
	private UnboundedPriorityQueue<Integer> queue;
	private int i = 2;
	
	/**
	 * before every test, preform the following
	 */
	@Before
	public void setup(){
		/* this queue will have size of 2 so we can test if we can insert beyond that
		 */
		queue = new UnboundedPriorityQueue<Integer>(i);
	}
	/**
	 * Tests the constructor
	 */
	@Test
	public void constructorTest(){
		/* should be empty */
		assertTrue(this.queue.isEmpty());
	}
	/**
	 * Test the unbounded property of the queue
	 */
	@Test
	public void sizeTest(){
		Integer one = new Integer(1);
		Integer two = new Integer(2);
		Integer three = new Integer(3);
		Integer four = new Integer(4);
		this.queue.enqueue(one);
		this.queue.enqueue(two);
		/* if the queue works, it should increase the size of this now */
		this.queue.enqueue(three);
		this.queue.enqueue(four);
	}
	/**
	 * Tests the dequeue method
	 */
	@Test
	public void dequeueTest(){
		Integer one = new Integer(1);
		this.queue.enqueue(one);
		this.queue.dequeue();
		/* should be empty now */
		assertTrue(this.queue.isEmpty());
	}
	/**
	 * Makes sure an exception is thrown 
	 */
	@Test(expected=IllegalStateException.class)
	public void removeTest(){
		this.queue.dequeue(); //should throw exception
	}
}