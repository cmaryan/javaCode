import java.util.ArrayList;
/**
 * This class will be my implementation of a Queue so I can use it in the BinarySearchTree
 */
public class MyQueue<T>{
	/**
	 *The members will include the size of the queue, the pointer at the front, the pointer to 
	 * the back, and array to store the objects
	 */
	private int size;
	private int readPointer;
	private int backPointer;
	private ArrayList<T> list;
	/**
	 * The default constructor for the queue will intialize the array list and its pointers
	 */
	public MyQueue(){
		size = 0;
		backPointer = 0;
		readPointer = 0;
		list = new ArrayList<>(100);
		for(int i = 0;i < 100;i++)
			list.add(null);
	}
	/**
	 * optional contructor that takes an int to specify the size of the queue. Useful for
	 * trees
	 */
	public MyQueue(int i){
		size = 0;
		backPointer = 0;
		readPointer = 0;
		list = new ArrayList<>(i);
		for(int j = 0;j < i;j++)
			list.add(null);
	}
	/**
	 * A method to return the queue's readPointer
	 * @return int The location of the readPointer
	 */
	public int readPointer(){
		return this.readPointer;
	}
	/**
	 * A method to return the queue's size
	 * @return int The size of the queue
	 */
	public int size(){
		return this.size;
	}
	/**
	 * A method to return what the queue is now pointing at
	 * @return boolean Represents if the data is what readPointer is pointing at
	 * @throws IllegalStateException() if the queue is empty
	 */
	public boolean next(T value){
		/* if the queue is empty throw an exception */
		if(size == 0)
			throw new IllegalStateException("The Queue is empty.");
		/* special case for null so we dont throw a null pointer exception */
		if(value == null)
			return list.get(readPointer()) == null;
		/* use the arraylist comparison */
		return list.get(readPointer()).equals(value);
	}
	/**
	 * A method to return the queue's backPointer
	 * @return int The location of the backPointer.
	 */
	public int backPointer(){
		return this.backPointer;
	}
	/**
	 * A method to check if the queue has a value
	 * @param T value The value you want to see if the queue contains
	 * @return boolean Represents if the value is in the queue
	 */
	public boolean contains(T value){
		if(list.contains(value))
			return true;
		return false;
	}
	/**
	 * A method to return the index of a value in the queue
	 * @param T value The value you want the index of
	 * @return int The index of the value or -1 if the queue does not contain the value
	 */
	public int indexOf(T value){
		return list.indexOf(value);
	}
	/**
	 * A method to put a value into the queue
	 * @param T value The value you want in the queue
	 * @ensure size > 0
	 */
	public void enqueue(T value){
		/* increase the size of the queue */
		size++;
		/* add the value to whereever the backPointer is pointing */
		list.set(backPointer(),value);
		/* increase backPointer */
		backPointer++;
	}
	/**
	 * A method to overload enqueue so you can add a value at any index as long as it is after 
 	 * readPointer
	 * @param int index, T value The index of where you want to add the value and the value 
	 * itself
	 * @throws IllegalArgumentException() if index < readPointer or if there is already something there
	 * @Overload the enqueue method allowing an index
	 */
	public void enqueue(int index, T value){
		/* throw an exception if the index is before the readPointer */
		if(index < readPointer())
			throw new IllegalArgumentException("Cannot enqueue before readPointer.");
		/* only add if there is nothing there */
		else if(list.get(index) == null)
			list.set(index,value);
		else
			throw new IllegalArgumentException("cannot change what is in the queue");
		/* increase the size */
		size++;
		/* if the index was greater than the backPointer, make that + 1 the new backPointer */
		if(index > backPointer())
			backPointer = index + 1;
	}
	/**
	 * A method to remove a value from the queue
	 * @return T value the value you have removed
	 * @ensure size = size - 1, and read is now pointing at the next value
	 * @throws IllegalStateException() If the queue is empty.
	 */
	public T dequeue(){
		/* throw an exception if the queue is empty */
		if(size == 0)
			throw new IllegalStateException("The Queue is empty.");
		T data;
		/* get the data at the read pointer */
		data = list.get(readPointer());
		/* increase read point */
		readPointer++;
		/* decrease size */
		size--;
		return data;
	}
}