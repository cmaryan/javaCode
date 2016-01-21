import java.lang.Comparable;
import java.util.Arrays;
import java.util.Comparator;
/**
 * An implementation of the MinHeap data structure
 */
public class MinHeap<T extends Comparable<? super T>>{
	/**
	 * The members of the MinHeap class will be a size of the array it is using and the
	 * array itself which of course is generic
	 */
	private static final int DEFAULT_SIZE = 13;
	private int size;
	private T[] array;
	/**
	 * Default constructor for MinHeap
	 * @ensure length is now 14
	 */
	public MinHeap(){
		this(DEFAULT_SIZE);
	}
	/**
	 * Constuctor that intializes the size of the heap to the value specified
	 * @param int i the size of the heap
	 */
	@SuppressWarnings({"unchecked","rawtypes"})	/*This is done because even though we know a generic type will be
		 											* an Object and therefore comparable, 
													* the compiler will warn us about it*/			
	public MinHeap(int i){
		this.size = 0;
		array = (T[]) new Comparable[i+1];
	}
	/**
	 * A constructor that takes an array of items as an argument and makes a MinHeap out of it
	 * @param T[] seq The array of elements you wish to build a heap from
	 * @ensures The seq is now in the order of the heap structure
	 */
	@SuppressWarnings({"unchecked","rawtypes"})
	public MinHeap(T[] seq){
		size = seq.length;
		array = (T[]) new Comparable[(size + 2) * 11 / 10 ];
		int i = 1;
		for(T item : seq)
			array[i++] = item;
		buildHeap();
	}
	/**
	 * A method to establish MinHeap order
	 */
	private void buildHeap(){
		for(int i = size / 2; i > 0;i--)
			moveValueDown(i);
	}
	/**
	 * A method to determine if the MinHeap is empty
	 * @return boolean Represents if the heap is empty
	 */
	public boolean isEmpty(){
		/* if the size is 0, its empty */
		return size == 0;
	}
	/**
	 * A method to determine if the heap is full
	 * @return boolean Represents if the heap is full
	 */
	public boolean isFull(){
		/* if the size is one less than the length of the array,it is full */
		return size == array.length - 1;
	}
	/**
	 * A method to return the heap as a string
	 * @return string The heap as a printable string
	 */
	@SuppressWarnings({"unchecked","rawtypes"})
	public String toString(){
		StringBuilder string = new StringBuilder();
		for(int i = 0;i < this.size;i++){
			if(array[i+1] == null)
				break;
			string.append(" " + array[i+1]);
		}
		return string.toString().trim();
	}
	/**
	 * A method to find the minimum value of the heap
	 * @return T The minimum value of the heap
	 */
	private T findMin(){
		if(isEmpty())
			return null;
		/* the minimum value of the minHeap is the root which is index 1 not 0 */
		return array[1];
	}
	/**
	 * A method to enlarge the current array if it is not big enough to store the new item
	 * @param int i The array size to enlarge to
	 * @throws IllegalArgumentException() if the value is smaller than the array's size
	 */
	@SuppressWarnings({"unchecked","rawtypes"})
	private void enlargeArray(int i){
		/* if the argument is less than the current array size, throw an exception */
		if(i < array.length)
			throw new IllegalArgumentException();
		T[] newArray = (T[]) new Comparable[i];
		for(int j = 0; j < array.length;j++){
			/* copy each element to the new array */
			newArray[j] = array[j];
		}
		/* set the pointer for array to be newArray with the bigger size */
		array = newArray;
	}
	/**
	 * A method to insert a value into the heap
	 * @param T value The value you wish to insert
	 * @ensure The heap is now larger by one item
	 */
	public void insert(T value){
		/* first check and see if the array is full then increase its size by an
		 * arbitrary amount if it is. */
		if(isFull())
			enlargeArray(array.length * 2 + 1);
		/* make a marker for the new hole we are putting into the heap that will
		 * eventually contain the value */
		int hole = ++size;
		/* run through the heap going up toward the root until you find the spot for
		 * the hole */
		for(array[0] = value;value.compareTo(array[hole / 2]) < 0;hole /= 2)
			/* the hole will go up a level each iteration by dividing the index
			 * by 2 */
			array[hole] = array[hole / 2];
		/* once it stops when the value is greater than the root of the subtree,
		 * it will stop and make that hole value the index*/
		array[hole] = value;
	}
	/**
	 * A method to delete from the heap
	 * @return T value The item you just removed
	 * @ensure The heap is now one less in size
	 * @throws IllegalStateException() If the heap is empty
	 */
	public T remove(){
		/* check if the heap is empty */
		if(isEmpty())
			throw new IllegalStateException();
		/* get the value at array[1] */
		T item = findMin();
		/* make it the value of the last element of the heap whilst decreasing the size */ 
		array[1] = array[size--];
		/* move the hole we made at the root down to its proper spot */
		moveValueDown(1);
		return item;
	}
	/**
	 * A method to check if the root of a subtree in the heap is following the MinHeap structure
	 * @param int hole The hole we need to move down
	 * @ensure The value at the index of hole is in keeping with the structure of the MinHeap
	 */
	private void moveValueDown(int index){
		/* marker for the child of the root */
		int child;
		/* whereever the hole is starting at, save the value */
		T item = array[index];
		/* infinite loop for moving the hole down, breaks when the element is greater than the value
		 * at the hole, meaning we found the location for the item*/
		for(;index * 2 <= size;index = child){
			/* go to the next level */
			child = index * 2;
			/* if the child is not the last element and its sibling is less than it, increase child
			 * index */
			if(child != size && array[child + 1].compareTo(array[child]) < 0)
				child++;
			/* the value at the child index is less than the value at the hole, the value at the
			 * hole becomes the value at the child meaning the hole moves down */
			if(array[child].compareTo(item) < 0)
				array[index] = array[child];
			/* if the child is the last element of the heap or if the value at the child index
			 * is greater than the value of the hole, then we are where we should be so stop */
			else
				break;
		}
		/* the hole is now at where the item should go so put the value in it */
		array[index] = item;
	}
}