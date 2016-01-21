import java.lang.Comparable;
import java.util.Arrays;
import java.util.Comparator;
/**
 * An implementation of the UnboundedPriorityQueue class which will have no size limit
 */
public class UnboundedPriorityQueue<T extends Comparable<? super T>>{
	/**
	 * The only memeber of this class is the heap
	 */
	private MinHeap<T> heap;
	/**
	 * Default constructor for the UnboundedPriorityQueue
	 */
	public UnboundedPriorityQueue(){
		this.heap = new MinHeap<T>();
	}
	/**
	 * This contructor uses the overloaded constructor for MinHeap
	 */
	public UnboundedPriorityQueue(int i){
		this.heap = new MinHeap<T>(i);
	}
	/**
	 * A method to enqueue a value into the PriorityQueue
	 * @param T value The value you wish to enqueue into the PriorityQueue
	 */
	public void enqueue(T value){
		this.heap.insert(value);
	}
	/**
	 * A method to dequeue a value from the PriorityQueue
	 * @return T The value you wish to remove from the PriorityQueue
	 * @throws IllegalStateException() if the PriorityQueue is empty
	 */
	public T dequeue(){
		if(this.isEmpty())
			throw new IllegalStateException("The PriorityQueue is empty");
		return this.heap.remove();
	}
	/**
	 * A method to determine if the heap is empty
	 * @return boolean Represents if the heap is empty
	 */
	public boolean isEmpty(){
		return this.heap.isEmpty();
	}
}