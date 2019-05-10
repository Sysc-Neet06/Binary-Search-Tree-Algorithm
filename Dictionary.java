/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Neethan's PC
 */
public interface Dictionary<E, K extends Sortable> {
	// search for an entry with key KEY and return the object
	public abstract E search(K key);

	// insert a key-value pair into the dictionary
	public abstract void insert(K key, E element);

	// delete an entry with key KEY
	public abstract void delete(K key);

	// print the Dictionary in sorted order (as determined by the keys)
	public abstract void printTree();

	// return the depth of the underlying tree
	public abstract int depth();
}