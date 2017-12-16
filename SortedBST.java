
class SBSTNode<K extends Comparable<K>, T> {
	public K key;
	public T data;
	public SBSTNode<K, T> left, right, next, previous;

	public SBSTNode(K key, T data) {
		this.key = key;
		this.data = data;
		left = right = next = previous = null;
	}
}

public class SortedBST<K extends Comparable<K>, T> implements SortedMap<K, T> {

	private int n;
	private SBSTNode<K, T> root, head, tail, current;

	public SortedBST() {
		current = tail = head = root = null;
	}

	@Override
	public int size() {
		return n;
	}

	@Override
	public boolean empty() {
		return n == 0;
	}

	@Override
	public boolean full() {
		return false;
	}

	@Override
	public Pair<K, T> retrieve() {
		return new Pair<K, T>(current.key, current.data);
	}

	@Override
	public void update(T data) {
		current.data = data;
	}

	@Override
	public boolean updateKey(K newKey) {
		if (current.next != null && current.next.key.compareTo(newKey) <= 0) {
			return false;
		}
		if (current.previous != null && current.previous.key.compareTo(newKey) >= 0) {
			return false;
		}
		current.key = newKey;
		return true;
	}

	@Override
	public boolean insert(K key, T data) {
		if (root == null) {
			current = head = tail = root = new SBSTNode<K, T>(key, data);
			n = 1;
			return true;
		}

		SBSTNode<K, T> p = root;
		SBSTNode<K, T> q = null;
		while (p != null) {
			int res = key.compareTo(p.key);
			if (res == 0) {
				break;
			} else {
				q = p;
				if (res < 0) {
					p = p.left;
				} else {
					p = p.right;
				}
			}
		}
		if (p != null) {
			return false;
		}

		SBSTNode<K, T> tmp = new SBSTNode<K, T>(key, data);
		if (key.compareTo(q.key) < 0) {
			q.left = tmp;
			tmp.next = q;
			tmp.previous = q.previous;
			q.previous = tmp;
			if (q == head) {
				head = tmp;
			} else {
				tmp.previous.next = tmp;
			}
		} else {
			q.right = tmp;
			tmp.previous = q;
			tmp.next = q.next;
			q.next = tmp;
			if (q == tail) {
				tail = tmp;
			} else {
				tmp.next.previous = tmp;
			}
		}
		current = tmp;
		n++;
		return true;
	}

	@Override
	public boolean find(K key) {
		SBSTNode<K, T> p = root;
		while (p != null) {
			int res = key.compareTo(p.key);
			if (res == 0) {
				break;
			} else if (res < 0) {
				p = p.left;
			} else {
				p = p.right;
			}
		}
		if (p == null) {
			return false;
		}
		current = p;
		return true;
	}

	@Override
	public int nbNodesInSearchPath(K key) {
		SBSTNode<K, T> p = root;
		int cpt = 0;
		while (p != null) {
			cpt++;
			int res = key.compareTo(p.key);
			if (res == 0) {
				break;
			} else if (res < 0) {
				p = p.left;
			} else {
				p = p.right;
			}
		}
		return cpt;
	}

	@Override
	public boolean remove(K key) {
		// Search for k
		K k1 = key;
		SBSTNode<K, T> p = root;
		SBSTNode<K, T> q = null; // Parent of p
		while (p != null) {
			int res = k1.compareTo(p.key);
			if (res < 0) {
				q = p;
				p = p.left;
			} else if (res > 0) {
				q = p;
				p = p.right;
			} else { // Found the key

				// Check the three cases
				if ((p.left != null) && (p.right != null)) { // Case 3: two children
					// Search for the min in the right subtree
					SBSTNode<K, T> min = p.right;
					q = p;
					while (min.left != null) {
						q = min;
						min = min.left;
					}
					p.key = min.key;
					p.data = min.data;
					k1 = min.key;
					p = min;
					// Now fall back to either case 1 or 2
				}

				// Change list links
				if (p == tail) {
					tail = p.previous;
					if (tail != null) {
						tail.next = null;
					}
				} else {
					p.next.previous = p.previous;
				}
				if (p == head) {
					head = p.next;
					if (head != null) {
						head.previous = null;
					}
				} else {
					p.previous.next = p.next;
				}

				// The subtree rooted at p will change here
				if (p.left != null) { // One child
					p = p.left;
				} else { // One or no children
					p = p.right;
				}

				if (q == null) { // No parent for p, root must change
					root = p;
				} else {
					if (k1.compareTo(q.key) < 0) {
						q.left = p;
					} else {
						q.right = p;
					}
				}
				n--;
				return true;
			}
		}

		return false; // Not found
	}

	@Override
	public void findFirst() {
		current = head;
	}

	@Override
	public void findNext() {
		current = current.next;
	}

	@Override
	public void findPrevious() {
		current = current.previous;
	}

	@Override
	public boolean first() {
		return current == head;
	}

	@Override
	public boolean last() {
		return current == tail;
	}

	@Override
	public void findLast() {
		current = tail;
	}

	@Override
	public List<Pair<K, T>> inRange(K k1, K k2) {
		LinkedList<Pair<K, T>> res = new LinkedList<Pair<K, T>>();
		if (k1.compareTo(k2) <= 0) {
			inRange(k1, k2, root, res);
		}
		return res;
	}

	private void inRange(K k1, K k2, SBSTNode<K, T> t, List<Pair<K, T>> res) {
		if (t == null) {
			return;
		}
		int res1 = k1.compareTo(t.key);
		int res2 = k2.compareTo(t.key);

		if (res1 < 0) {
			inRange(k1, k2, t.left, res);
		}
		if (res1 <= 0 && res2 >= 0) {
			res.insert(new Pair<K, T>(t.key, t.data));
		}
		if (res2 > 0) {
			inRange(k1, k2, t.right, res);
		}
	}

	@Override
	public void remove() {
		if (current.left != null & current.right != null) {
			remove(current.key);
		} else {
			SBSTNode<K, T> newCurrent = current.next;
			if (newCurrent == null && current != head) {
				newCurrent = head;
			}
			remove(current.key);
			current = newCurrent;
		}
	}
}
