import java.lang.Comparable;
import java.util.Comparator;
public class BinarySearchTree<T extends Comparable<T>> extends BinaryTree<T>{
	/* the comparator of the BinarySearchTree */
	private Comparator<? super T> comp;
	/**
	 * default constructor
	 */
	public BinarySearchTree(){
		super();
	}
	/**
	 * constructor with just the breadthfirsttraverse array as the parameter
	 * @param T[] seq from parent
	 */
	public BinarySearchTree(T[] seq){
		super(seq);
	}
	/**
	 * Constructor for the BinarySearchTree.
	 * @param T[] seq from the parent class
	 * @param T nullSymbol from the parent class
	 */
	public BinarySearchTree(T[] seq,T nullSymbol){
		super(seq,nullSymbol);
	}
	/**
	 * A method to convert compareTo calls to compare using the comparator.
	 * @param T thisData, T thatData The data of the two nodes you are comparing
	 * @return int represents if the data is greater than or less than the other data
 	 */
	private int myCompare(T thisData, T thatData){
		/*if the comparator is not null, return a normal comparison */
		if(comp != null)
			return comp.compare(thisData,thatData);
		else
			/* else cast it to comparable and compare to */
			return thisData.compareTo(thatData);
	}
	public boolean isEmpty(){
		return root == null;
	}
	public boolean contains(T value){
		return contains(value,root);
	}
	/**
	 * A method to determine if the tree contains the value. Will be used to find duplicates.
	 * @param T value The value you are looking for
	 * @return boolean Represents if the value is in the tree or not
	 */
	private boolean contains(T value, BinaryNode<T> node){
		/* see if the node is null */
		if(node == null)
			return false;
		/* compare the value you are searching for to the data at the node */
		int comparison = myCompare(value,node.getData());
		/* if the result is less than 0, search the left subtree */
		if(comparison < 0)
			return contains(value,node.getLeftNode());
		/* if the reuslt is greater than 0, search the right subtree */
		else if(comparison > 0)
			return contains(value,node.getRightNode());
		else
			return true;
	}
	/**
	 * @throws IllegalStateException() if the tree is empty
	 */
	public T findMin(){
		if(isEmpty()) throw new IllegalStateException("The tree is empty.");
		return findMin(root).getData();
	}
	/**
	 * A method to find the smallest node in a subtree.
	 * @param node The node that roots the subtree
	 * @return node containing the smallest data
	 */
	private BinaryNode<T> findMin(BinaryNode<T> node){
		/* if the node is null then return it */
		if(node == null)
			return null;
		/* if it has no left child, then it is itself the smallest node */
		else if(node.getLeftNode() == null)
			return node;
		/* recursivley call findMin untill you find the smallest */
		else
			return findMin(node.getLeftNode());
	}
	/**
	 * @throws IllegalStateException() If the tree is empty 
	 */
	public T findMax(){
		if(isEmpty()) throw new IllegalStateException("The tree is empty");
		return findMax(root).getData();
	}
	/**
	 * A method to find the largest node in a subtree
	 * @param node the Node that roots the subtree
	 * @return node containing the largest data
	 */
	private BinaryNode<T> findMax(BinaryNode<T> node){
		/* if the node is not null */
		if(node != null)
			/* while the right child is not null */
			while(node.getRightNode() != null)
				/* the node now equals the rightNode */
				node = node.getRightNode();
		return node;
	}
	public void insert(T value){
		root = insert(value,root);
	}
	/**
	 * A method to add a value to the list
	 * @param T value,BinaryNode<T> node The value you are inserting and the starting point
	 * @return BinaryNode<T> the newly created node 
	 * @ensure The tree contains no duplicates
	 */
	private BinaryNode<T> insert(T value, BinaryNode<T> node){
		/* when you find a null node, make a new node in its spot */
		if(node == null)
			return new BinaryNode<>(value);
		/* compare the data at the node with the data you are trying to enter */
		int comparison = myCompare(value,node.getData());
		/* if it is less than 0, recursively move left to the next node */
		if(comparison < 0)
			node.setLeftNode(insert(value,node.getLeftNode()));
		/* if its greater than 0, recursively move right to the next node */
		else if(comparison > 0)
			node.setRightNode(insert(value,node.getRightNode()));
		/* if its not less or greater its a duplicate so do nothing */
		else
		;
		return node;
	}
	public void remove(T value){
		root = remove(value,root);
	}
	/**
	 * A method to remove a node from the BinarySearchTree.
	 * @param T value,BinaryNode<T> node The value you want to remove and the node you want to 
	 * remove that contains the value
	 * @return BinaryNode<T> The new root of the subtree
	 */
	private BinaryNode<T> remove(T value, BinaryNode<T> node){
		/* if you found null, then we are at the bottom so we found what we need, return it and start going back up */
		if(node == null)
			return node;
		/* compare the value and the node's data and store the result */
		int comparison = myCompare(value,node.getData());
		/* if the result is less than 0, move left and recusively call remove on the lesser node */
		if(comparison < 0)
			node.setLeftNode(remove(value,node.getLeftNode()));
		/* if the result is greater than 0, move right and recursilty call remove on the greater node */
		else if(comparison > 0)
			node.setRightNode(remove(value,node.getRightNode()));
		/* if it got here then we found the value so if it has two children */
		else if(node.getLeftNode() != null && node.getRightNode() != null){
			/* change the data of the node to be its deepest right subtree's left child's data(meaning the next 
				largest node after the one removed) */
			node.setData(findMin(node.getRightNode()).getData());
			/* then set the node you just found to null by traversing down to it and finding a null child */
			node.setRightNode(remove(node.getData(),node.getRightNode()));
		}
		/* once you get to the node with no children set it equal to the null child */
		else
			/* the node is now the left child if it was found, or the right if it was not */
			node = (node.getLeftNode() != null) ? node.getLeftNode() : node.getRightNode();
		/* then return the rest of the calls to remove by returning the node at each subtree unchanged since it 
			did not alter the node itself, just the children unless it was now a duplicate in which case it is now null*/
		return node;
	}
}