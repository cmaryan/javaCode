import org.junit.*;
import static org.junit.Assert.*;
import java.util.ArrayList;
/**
 * This class will test the implementation of queue that I created.
 */
public class MyQueueTest{
	/**
	 * MyQueue Test fixture
	 */
	private MyQueue<Integer> queue;
	
	/**
	 * Before every test, preform the following setup.
	 */
	@Before
	public void setup(){
		queue = new MyQueue<>();
	}
	
	/**
	 * Tests the values of the members when a queue is created
	 */
	@Test
	public void constructorTest(){
		assertEquals(0,this.queue.size());
		assertEquals(0,this.queue.backPointer());
		assertEquals(0,this.queue.readPointer());
	}
	/**
	 * Tests the contains() method of MyQueue
	 */
	@Test
	public void containsTest(){
		Integer two = new Integer(2);
		this.queue.enqueue(two);
		/* should now contain 2 */
		assertTrue(this.queue.contains(2));
		/* should not contain -1 */
		assertFalse(this.queue.contains(-1));
	}
	/**
	 * Tests the next() method of MyQueue
	 */
	@Test
	public void nextTest(){
		Integer two = new Integer(2);
		this.queue.enqueue(two);
		/* readPointer should be pointing at 2 */
		assertTrue(this.queue.next(2));
	}
	@Test(expected=IllegalStateException.class)
	public void nextTest2(){
		this.queue.next(1);		//should raise an exception
	}
	/**
	 * Tests the indexOf() method of MyQueue
	 */
	@Test
	public void indexOfTest(){
		/* 5 is not in the queue so expecting -1 */
		assertEquals(-1,this.queue.indexOf(5));
		Integer two = new Integer(2);
		this.queue.enqueue(two);
		/* should be at index 0 */
		assertEquals(0,this.queue.indexOf(two));
	}
	/**
	 * Tests the enqueue() method by testing the values of the pointers afterward
	 */
	@Test
	public void enqueueTest(){
		Integer one = new Integer(1);
		Integer twenty = new Integer(20);
		this.queue.enqueue(one);
		/* the size and what backPointer is pointing at should be 1 */
		assertEquals(1,this.queue.size());
		assertEquals(1,this.queue.backPointer());
		this.queue.enqueue(twenty);
		/* now 2 */
		assertEquals(2,this.queue.size());
		assertEquals(2,this.queue.backPointer());
	}
	/**
	 * Tests the overloaded enqueue() method that also takes and index
	 */
	@Test
	public void enqueueTest2(){
		Integer one = new Integer(1);
		Integer twenty = new Integer(20);
		this.queue.enqueue(one);
		this.queue.enqueue(6,twenty);
		/* backPointer should now be 7 */
		assertEquals(7,this.queue.backPointer());
	}
	/**
	 * Once again testing the overloaded method but this time expecting an exception
	 */
	@Test(expected=IllegalArgumentException.class)
	public void enqueueTest3(){
		Integer one = new Integer(1);
		Integer twenty = new Integer(20);
		this.queue.enqueue(one);
		this.queue.dequeue();
		/* readPointer should now be at 1 so cannot add at it */
		this.queue.enqueue(0,twenty); //should raise an exception
	}
	/**
	 * Tests the dequeue method
	 */
	@Test
	public void dequeueTest(){
		Integer one = new Integer(1);
		Integer twenty = new Integer(20);
		this.queue.enqueue(one);
		this.queue.enqueue(twenty);
		Integer newOne = this.queue.dequeue();
		/* since 1 was in first newOne should = 1 */
		assertEquals(1,newOne.intValue());
		Integer newTwenty = this.queue.dequeue();
		/* newTwenty should be equal to twenty */
		assertEquals(20,newTwenty.intValue());
		/* size() should now be 0 */
		assertEquals(0,this.queue.size());
		/* readPointer should now be pointing at index 2 */
		assertEquals(2,this.queue.readPointer());
	}
	/**
	 * Tests if trying to dequeue an empty queue throws an exception
	 */
	@Test(expected=IllegalStateException.class)
	public void dequeueTest2(){
		this.queue.dequeue(); //should raise an exception
	}
}