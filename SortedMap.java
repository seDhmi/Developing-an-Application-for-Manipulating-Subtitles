// The sorted map interface. K represents the key type, which must extend Comparable,
// and T representds the stored data type.
public interface SortedMap<K extends Comparable<K>, T> {

	// Return the size of the map. Must be O(1).
	int size();

	// Return true if the tree is empty. Must be O(1).
	boolean empty();

	// Return true if the tree is full. Must be O(1).
	boolean full();

	// Move current to the first element (the smallest key). The map must not be
	// empty. Must be O(1).
	void findFirst();

	// Move current to the last element (the largest key). The map must not be
	// empty. Must be O(1).
	void findLast();

	// Move current to the next element (the key immediately greater than the
	// current key). The map must not be empty, and current must not be last. Must
	// be O(1).
	void findNext();

	// Move current to the previous element (the key immediately smaller than the
	// current key). The map must not be empty, and current must not be first. Must
	// be O(1).
	void findPrevious();

	// Returns true if current is the first element (the smallest key). The map must
	// not be empty. Must be O(1).
	boolean first();

	// Returns true if current is the last element (the largest key). The map must
	// not be empty. Must be O(1).
	boolean last();

	// Return the key and data of the current element
	Pair<K, T> retrieve();

	// Update the data of current element.
	void update(T e);

	// Search for element with key k and make it the current element if it exists.
	// If the element does not exist the current is unchanged and false is returned.
	// This method must be O(log(n)) in average.
	boolean find(K key);

	// Return the number of nodes in the search path for key. Must be O(log n).
	int nbNodesInSearchPath(K key);

	// Update the current key to the new value newKey if possible and returns true.
	// If newKey does not respect the order, no change is made and false is
	// returned. Must be O(1).
	boolean updateKey(K newKey);

	// Insert a new element if does not exist and return true. The current points to
	// the new element. If the element already exists, current does not change and
	// false is returned. This method must be O(log(n)) in average.
	boolean insert(K key, T data);

	// Remove the element with key k if it exists and return true. If the element
	// does not exist false is returned (the position of current is unspecified
	// after calling this method). This method must be O(log(n)) in average.
	boolean remove(K key);

	// Remove the current element. The next element if it exists is made current,
	// otherwise current moves to the first element. This method must be O(log(n))
	// in average.
	void remove();

	// Return in a list all elements with key k satisfying: k1 <= k <= k2.
	List<Pair<K, T>> inRange(K k1, K k2);
}
