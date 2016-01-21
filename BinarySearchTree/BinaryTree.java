import java.util.List;
import java.util.ArrayList;
import java.lang.Comparable;

public class BinaryTree<T extends Comparable<T>>{
	BinaryNode<T> root = null;	
	private T nullSymbol = null;

	/**
	 * Default constructor
	 */
	public BinaryTree(){

	}

	/**
	 *	This constructor is useful for test purposes,
	 *  not meant for use in general.
	 *
	 *  Constructs a binary tree from a given valid breadth-first traversal sequence.
	 *  @param seq is an array containing breadth-first traversal sequence of the nodes of a tree.
	 */
	public BinaryTree(T[] seq){
		initFromBfsSequence(seq);
	}

	/**
	 *	This constructor is useful for test purposes,
	 *  not meant for use in general.
	 *
	 *  Constructs a binary tree from a given valid breadth-first traversal sequence. 
	 *	A given special symbol (nullSymbol) in the sequence is interpreted as absence of node. 
	 *	During construction of the tree, when such a special symbol is encountered, 
	 *	that is considered to be an absent node, and thus no corresponding node is added to the tree.
	 * 	
	 * 	@param seq is an array containing breadth-first traversal sequence of the nodes of a tree.
	 * 	@param nullSymbol is a special symbol in the given sequence that denotes absence of a node.
	 */
	public BinaryTree(T[] seq, T nullSymbol){
		this.nullSymbol = nullSymbol;
		initFromBfsSequence(seq);
	}
	/**
	 * A method to help initialize a tree from an array.
	 * @param T[] seq An array that represents the breadth first traversal of a tree.
	 * @ensure The tree is now populated by the given sequence.
	 * @throws IllegalArgumentException() if either the sequence is empty or the root is null
	 */
	private void initFromBfsSequence(T[] seq){
		/* if the array is of 0 length, it is empty and a tree cannot be made. */
		if(seq.length == 0)
			throw new IllegalArgumentException("Cannot build tree from empty sequence");
		/* if the root is null, it cannot be made size the root cannot be null */
		if(seq[0].equals(nullSymbol))
			throw new IllegalArgumentException("Symbol for root cannot be nullSymbol");
		/* make a new array list of size seq.length.*/
		List<BinaryNode<T>> nodes = new ArrayList<BinaryNode<T>>(seq.length);
		/* the root is the first element in the array so make it a node */
		this.root = new BinaryNode<T>(seq[0]);
		/* add it to the arraylist. */
		nodes.add(root);
		/* for each element in the array. */
		for(int i = 0; i < nodes.size(); i++){
			/* if the node at the index is null */		
			if(nodes.get(i) == null){ 		
				/* make its children null as well */
				handleNullParentNode(nodes, i, seq.length);	
			/* if the node is not null */			
			}else{		
				/* check if its children are null and if not, add them to the list. */		
				handleNonNullParentNode(nodes, i, seq);				
			}
		}		
	}
	/**
	 * A helper method to assist in the initialization of a tree. This will handle the case of the
	 * parent node having children that are null.
	 * @param List<BinaryNode<T>> nodes, int nullNodeIndex, int seqLength The list containing all the
	 * nodes, the int representing the parent of the null nodes, and the int representing where you
	 * are in the array.
	 */
	private void handleNullParentNode(List<BinaryNode<T>> nodes, int nullNodeIndex, int seqLength){
		/* left child index in the array is just after the parent index */
		int leftIndex = (nullNodeIndex * 2) + 1;
		/* check of it is in bounds. */		
		if(leftIndex < seqLength){
			/* add a null item to the list. */
			nodes.add(null);
			/* the right child index in the array is just after the left child index. */
			int rightIndex = (nullNodeIndex * 2) + 2;
			/* check if it is in bounds. */
			if(rightIndex < seqLength){
				/* add a null item to the list */
				nodes.add(null);
			}
		}
	}
	/**
	 * A helper method to assist in the initialization of a tree. This will handle the case of the 
	 * parent node having children that are NOT null.
	 * @param List<BinaryNode<T>> nodes, int parentIndex, T[] seq The list containing all the nodes,
	 * the int represent the parent index from which to start, and the array that is the breadth first
	 * sequence.
	 */
	private void handleNonNullParentNode(List<BinaryNode<T>> nodes, int parentIndex, T[] seq){
		/* the left child is the index just after the index of the parent in the array */
		int leftIndex = (parentIndex * 2) + 1;	
		/* check of the index falls out of bounds of the list index. */		
		if(leftIndex < seq.length){
			BinaryNode<T> leftNode = null;
			/* if the left child is not empty.*/
			if(!seq[leftIndex].equals(nullSymbol)){
				/* the left child is now a node contain the data of left index */
				leftNode = new BinaryNode<T>(seq[leftIndex]);
			}
			/* set the left child of the list to be the left child of the parent node in the list.*/
			nodes.get(parentIndex).leftNode = leftNode;
			/* add the left child to the list. */
			nodes.add(leftNode);
			/* in an array, the right child index is the index just after the left child index. */
			int rightIndex = (parentIndex * 2) + 2;
			/* check for out of bounds */			
			if(rightIndex < seq.length){
				BinaryNode<T> rightNode = null;
				/* if the right child is not empty */
				if(!seq[rightIndex].equals(nullSymbol)){
					/* the right child is now a node containing the data of the right index. */
					rightNode = new BinaryNode<T>(seq[rightIndex]);
				}
				/* set the right child to be the right child of the parent node in the list. */
				nodes.get(parentIndex).rightNode = rightNode;
				/* add the right child to the list */
				nodes.add(rightNode);			
			}
		}
	}
	/**
	 * A method that returns the height of the tree. Uses the node's height method.
	 * @return int Represents the height of the tree.
	 */
	public int height(){
		if (root == null) return 0;	
		return root.height();
	}
	/**
	 * A method that returns the width of the tree.
	 * @return int Represents the width of the tree.
	 */
	public int width(){
		/* if the root is null, the witdth is 0.*/
		if(root == null) return 0;
		/* make a new array of size height() * 2 since that will be the
		 * number of levels in the tree */
		int[] levels = new int[this.height() * 2 + 1];
		/* make a starting index for the tree level*/
		int treeLevel = 0;
		/* account for all of the children on the left side of the root and the right
		 * for each tree level */
		widthAssist(levels,root,treeLevel);
		/* initialize width to be the root */
		int width = levels[0];
		/* compare the width of the root level with all the other levels of the tree */
		for(int i = 0;i < levels.length;i++){
			/* if the width is less than the next level */
			if(width < levels[i]){
				/* that is the new width */
				width = levels[i];
			}
		}
		return width;
	}
	/**
	 * A helper method for the width method.
	 * @param int [] array,BinaryNode<T> node, int treeLevel The array to keep track of the level 
	 * counts, the BinaryNode you are starting from, and the level of the tree of which you are 
	 * currently at.
	 * @ensure The int array containing the value at each level is now populated
	 */
	private void widthAssist(int [] array,BinaryNode<T> node,int treeLevel){
		/* if the node is the null Symbol or null, we want to return */
		if(node == null || node.data.equals(nullSymbol)){
			return;
		}
		/* increase the width of the current treeLevel */
		array[treeLevel]++;
		/* recusively traverse the tree until we hit a leaf */
		widthAssist(array,node.leftNode,treeLevel + 1);
		/* same for the right */
		widthAssist(array,node.rightNode,treeLevel + 1);
		return;
	}
	/**
	 * A method to return a string representation of a breadth first traverse of a tree.
	 * @return String Represents the breadth first traverse of the tree.
	 */
	public String breadthFirstTraverse(){
		/* make a queue of this size. should be no bigger than this */
		int i = height() * width() + 50;
		/* this is our queue object */
		MyQueue<T> queue = new MyQueue<>(i);
		StringBuilder string = new StringBuilder();
		/* if the tree is empty throw an exception. */
		if(root == null)
			throw new IllegalStateException("The tree is empty");
		int treeLevel = 0;
		T data = null;
		/* traverse the tree and populate the queue */
		breadthFirstTraverse(root,treeLevel,queue);
		/* while there is still items in the queue */
		while(queue.size() > 0){
			/* if the data read pointer is pointing at is null or the nullSymbol */
			if(queue.next(null) || queue.next(nullSymbol))
				/* dequeue it but not into the string */
				data = queue.dequeue();
			else
				/* dequeue each item from the queue */
				string.append(" " + queue.dequeue());
		}

		return string.toString().trim();
	}
	/**
	 * A special method to que a tree using the children algorithm indexing
	 * @param BinaryTree<T>,int index,T nullSymbol tree The tree you want to enqueue, the
	 * starting index for recursion, and the nullSymbol of the tree
	 * @ensure the queue is now populated by a tree and that there are no duplicates
	 */
	private void breadthFirstTraverse(BinaryNode<T> node, int index, MyQueue<T> queue){
		/* if node is null, add nullSymbol to the queue */
		if(node == null || node.data.equals(nullSymbol)){
			queue.enqueue(index,nullSymbol);
			return;
		}
		/* enqueue the node */
		queue.enqueue(index,node.data);
		/* recursively call enqueueTree until you find a null left node */
		breadthFirstTraverse(node.leftNode,index * 2 + 1,queue);
		/* same for the right */
		breadthFirstTraverse(node.rightNode,index * 2 + 2,queue);
		return;
	}
	/**
	 * A method to display the tree's preorder traversal. Uses the node's method/
	 * @return String Represents the tree's preorder traversal.
	 */
	public String preOrderTraverse(){
		return root.preOrderTraverse().trim();				
	}
	/**
	 * A method to display the tree's post order traversal. Uses the node's method.
	 * @return String Represents the tree's postorder traversal.
	 */
	public String postOrderTraverse(){
		return root.postOrderTraverse().trim();
	}
	/**
	 * A method to display the tree's in order traversal. Uses the node's method then trims the string.
	 * @return String Represents the tree's inorder traversal.
	 */
	public String inOrderTraverse(){
		return root.inOrderTraverse().trim();
	}
	/**
	 * An inner class to be used by the BinaryTree class. Helps to identify the properties of each
	 * node.
	 */
	class BinaryNode<T>{
		private T data = null;
		/* both children are intially null so that means the node starts as a leaf node. */
		private BinaryNode<T> leftNode = null;
		private BinaryNode<T> rightNode = null;
		/**
		 * Constructor for the BinaryNode class.
		 * @param T data The data you want at the node initially
		 */
		public BinaryNode(T data){
			this.data = data;			
		}
		/**
		 * A method to convert the data at a node to a string.
		 * @return String Represents the data at the node as a string.
		 */
		public String toString(){
			return "" + data;
		}
		/**
		 * A method to return the left child of the node.
		 * @return BinaryNode<T> Represents the left child of the node.
		 */
		public BinaryNode<T> getLeftNode(){
			return this.leftNode;
		}
		/**
		 * A method to return the right child of the node.
		 * @return BinaryNode<T> Represents the right child of the node.
		 */
		public BinaryNode<T> getRightNode(){
			return this.rightNode;
		}
		/**
		 * A method to set the left child of the node.
		 * @param BinaryNode<T> node The node you wish to set the left child to.
		 * @ensure The node is no longer a leaf node.
		 */
		public void setLeftNode(BinaryNode<T> node){
			this.leftNode = node;
		}
		/**
		 * A method to set the right child of the node.
		 * @param BinaryNode<T> node The node you wish to set the right child to.
		 * @ensure The node is no longer a leaf node.
		 */
		public void setRightNode(BinaryNode<T> node){
			this.rightNode = node;
		}
		/**
		 * A method to reutn the data at a Node.
		 * @return T Represents the data at the node.
		 */
		public T getData(){
			return this.data;
		}
		/**
		 * A method to set the data of the node.
		 * @param T data The data you wish the node to contain.
		 */
		public void setData(T data){
			this.data = data;
		}
		/**
		 * A method to calculate the height of a binary tree.
		 * @return int Represent the height of the tree.
		 */
		public int height(){
			/* if the tree is just the root its height is 0. */
			if(isLeaf()) return 0;
			
			int leftHeight = 0;
			int rightHeight = 0;
			/* check the left child. recusivly calls height() */
			if(leftNode != null){ 
				leftHeight = leftNode.height();
			}
			/* check the right child and again recursivly calls height() */
			if(rightNode != null){
				rightHeight = rightNode.height();
			}
			/* if the leftHeight is greater than the right, the leftHeight is the maxHeight. Otherwise
			 * it becomes the rightHeight.
			 */
			int maxHeight = leftHeight > rightHeight? leftHeight: rightHeight;
			/* add 1 so that each time it returns a call from the stack, the height increases by 1. */
			return maxHeight + 1;
		}
		/**
		 * A method that determines if a node is a leaf node.
		 * @return Boolean Represents if the node is a leaf node.
		 */
		public boolean isLeaf(){
			/* if both chidren are null, it must have none and therefore be a leaf node. */
			return (leftNode == null && rightNode == null);
		}

		/**
		 * A method to display the preorder traversal of a node. Uses the stringBuilder object to
		 * continually edit the string before returning the final product. 
		 * @return String represents the preorder traversal of a node.
		 */
		public String preOrderTraverse(){
			StringBuilder stringBuffer = new StringBuilder();			
			/* first the node is traversed */
			stringBuffer.append(" " + data);
			/* then then left child */
			if(leftNode != null){
				stringBuffer.append(leftNode.preOrderTraverse());				
			}
			/* then the right child */
			if(rightNode != null){
				stringBuffer.append(rightNode.preOrderTraverse());
			}

			return stringBuffer.toString();				
		}
		/**
		 * A method to display the post order traversal of a node. Uses a similar implementaiton
		 * to preorder with the order in which they are appended being the key difference. In this
		 * situation, the node is appended after its children.
		 * @return String represents the postOrderTraverse.
		 */
		public String postOrderTraverse(){			
			StringBuilder stringBuffer = new StringBuilder();
			/* first the left child */
			if(leftNode != null){
				stringBuffer.append(leftNode.postOrderTraverse());
			}
			/* then the right */
			if(rightNode != null){
				stringBuffer.append(rightNode.postOrderTraverse());
			}
			/* then the node itself */
			stringBuffer.append(" " + data);
			
			return stringBuffer.toString();
		}
		/**
		 * A method that displays the in order traversal as a string. Again, similar implementation.
		 * Just in a different order.
		 * @return String represents the in order traversal.
		 */
		public String inOrderTraverse(){	
			StringBuilder stringBuffer = new StringBuilder();
			/* first the left child */
			if(leftNode != null){
				stringBuffer.append(leftNode.inOrderTraverse());
			}
			/* then the node itself */
			stringBuffer.append(" " + data);
			/* then the right */
			if(rightNode != null){
				stringBuffer.append(rightNode.inOrderTraverse());
			}
			
			return stringBuffer.toString();
		}
	}
}