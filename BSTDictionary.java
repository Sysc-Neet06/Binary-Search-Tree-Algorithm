/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Neethan's PC
 */
public class BSTDictionary<E, K extends Sortable> implements Dictionary<E, K> {

    protected BSTNode<E, K> root; // protected variable represented the root

    /**
     * Creates a BST dictionary object with root leading no where
     */
    public BSTDictionary() {
        root = null;
    }

    /**
     * Search method used to iterate through the tree
     * @param key search key
     * @return the node associated with the key value
     */
   @Override
    public Object search(Sortable key) {
        BSTNode<E, K> curr = root; // node used to keep track of position in tree

        int posval; // varaible used to keep track of the value of the comparison
        
        while (curr != null) // while loop
        {
            posval = curr.getKey().compareTo(key); // keeps track of the comparison value

            if (posval < 0) // is the value is less then zero its on the right side
            {
                curr = curr.getRight(); // set curr node to the node of the right of curr
            } 
            else if (posval > 0) 
            {
                curr = curr.getLeft(); // set curr to the node of the left side
            } else 
            {
                return curr; // returns curr
            }

        }
        return null; // return null otherwisee
    }

    /**
     * insert method used to insert new nodes into the tree
     * @param key search key
     * @param element type E object stored in the tree
     */
    @Override
    public void insert(Sortable key, Object element) {
        BSTNode newNode = new BSTNode(key, element, null, null); // creates a new node
        BSTNode curr = root; // set the curr node to the root
        int compareVal; // keeps track of comparision val for me

        if (root == null) // when root = null
        {
            root = newNode; // set root with the new node
        } else { // otherswise we need to follow binary search tree rules

            while (curr != null) { // while loop
                compareVal = curr.getKey().compareTo(key); // set the variable with comparision val
                if (compareVal < 0) { // if less then 0
                    if (curr.getRight() == null) { // and rightside is null
                        curr.setRight(newNode); // place new node on the right of the curr node
                        newNode = null; // set the new node to null
                    }
                    curr = curr.getRight(); // set curr to the one on the right

                } else if (compareVal >= 0) { // if greater then 0
                    if (curr.getLeft() == null) { // checks to see if left side is null
                        curr.setLeft(newNode); //set left side to with newnode
                        newNode = null; // newnode is null
                    }
                    curr = curr.getLeft(); // set curr to the node on the left
                }
            }
        }
    }

    /**
     * Deletions method used to delete nodes from the tree
     * @param key search key that are stored in the tree
     */
    @Override
    public void delete(Sortable key) {
        BSTNode head = null; // head node that is set to null
        BSTNode curr = root; // curr node set to root
        int nextnode; // variable used to keep track of comparison value
        int path = 0; // keeps track of which node to go to

        if (search(key) != null) { // if the key were using is not null

            while (curr.getKey().compareTo(key) != 0) { //  while loop
                nextnode = curr.key.compareTo(key); // keeps track of value
                if (nextnode < 0) { // is less the 0
                    head = curr; // set head with curr
                    curr = curr.getRight(); // curr set to right side of curr
                } else if (nextnode > 0) { // if greater then 0
                    head = curr; // head set to curr
                    curr = curr.getLeft(); // curr set to left side of curr
                }
                path = nextnode; // path set with nextnode value

            }
            // if node is a leaf
            if (curr.getLeft() == null && curr.getRight() == null) {
                if (path < 0) { // less then 0
                    head.setRight(null); // set the right side of head to null
                } else if (path > 0) { //greater then 0
                    head.setLeft(null); // set the left side of head to null
                } else {
                    root = null; // sets the root to null
                }
                
                // if node has one child
            } else if (curr.getLeft() == null && curr.getRight() != null) {
                if (path < 0) { //less than 0
                    head.setRight(curr.getRight()); // set the right side of head to right side of curr
                } else if (path > 0) { //greater than 0
                    head.setLeft(curr.getRight()); // sets the left side of head to right side of curr
                } else { // root set to the right of root
                    root = root.getRight();
                }
               
            } else if (curr.getLeft() != null && curr.getRight() == null) {
                if (path < 0) { // less than 0
                    head.setRight(curr.getLeft()); // set head right side to curr left side
                } else if (path > 0) { // greater than 0
                    head.setLeft(curr.getLeft()); // set head left side to curr left side
                } else {
                    root = root.getRight(); //root set to the right of root
                }
                 // node has two children
            } else if (curr.getLeft() != null && curr.getRight() != null) {
                BSTNode Left = curr.getLeft(); // left node tracker set to left of curr
                BSTNode Right = curr.getRight(); // right node tracker set to rightof curr
                BSTNode parent = head; // keeps track of head
                curr = Left; // setting curr to Left

                while (curr.getRight() != null) { // while loop
                    parent = curr; // parent is set to curr node
                    curr = curr.getRight(); // setting curr to the right of curr
                }

                BSTNode middle = new BSTNode(curr.getKey(), curr.getElement(), null, null); // declaring a middle node variable

                if (Left.getKey().compareTo(middle.getKey()) == 0) { // if left side key equals the middle key val
                    middle.setLeft(Left.getLeft()); // set middle node left side to left side of the left side node
                } else {
                    middle.setLeft(Left); // setting middle node to the left side of curr
                }
                middle.setRight(Right); // sets middle node to the right side of curr
                
                if (path < 0) { // less than zero
                    head.setRight(middle); // sets right side head to the middle node
                } else if (path > 0) { // greater than 0
                    head.setLeft(middle); // sets left side of head to middle
                } else {
                    root = middle; // sets root to middle
                }

                if (curr.getLeft() != null) { // if left of curr is not null
                    parent.setRight(curr.getLeft()); // sets head right side to curr left side
                } else if (parent != null) { // if head is not null
                    parent.setRight(null); // sets right side of head to null
                }
            }

        }
    }
    /**
     * prints the tree
     */
    @Override
    public void printTree() {
        Printhelperfunction(root); // calls the helper function
    }
    /**
     * Helper function to help print the tree
     * @param top 
     */
    private void Printhelperfunction(BSTNode<E,K> top){
        if(top != null){ // if the top of the tree is not null
            Printhelperfunction(top.getLeft()); // calls helper with left side of top
            System.out.println(top.getElement().toString()); // calls to string method with top node
            Printhelperfunction(top.getRight()); // calls helper function with right side of top
        }
    }
    
    /**
     * depth function finds the depth of the tree
     * @return 
     */
    @Override
    public int depth() {
        return PostHelperFunction(root); // calls helper function for this
    }
    
    
    /**
     * Helper function to help find depth using post fix form
     * @param parent
     * @return depth of the tree
     */
    private int PostHelperFunction(BSTNode<E,K> parent){
        if(parent != null){ // if the partent node is not null
            int left= 1 + PostHelperFunction(parent.getLeft()); //adds one to the left side total and calls helper function again with left side node
            int right = 1 + PostHelperFunction(parent.getRight()); // adds one to the right side depth and calls helper functions again with right side node
            
            if(right > left){ // checks if right side is larger than left side
                return right; // returns right value
            }
            
                return left; // returns left value
            
        }
        return 0; // returns 0 otherwise
    }
}
