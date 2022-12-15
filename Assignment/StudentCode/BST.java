public class BST<K extends Comparable<K>, T> implements Map<K, T> {
	BSTNode<K, T> root, current;

	public BST() {
		root = current = null;
	}

	@Override
	public boolean empty() {
		// TODO Auto-generated method stub
		return root == null;
	}

	@Override
	public boolean full() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public T retrieve() {
		// TODO Auto-generated method stub
		return current.data;
	}

	@Override
	public void update(T e) {
		// TODO Auto-generated method stub
		current.data = e;
	}

	@Override
	public Pair<Boolean, Integer> find(K key) {
		// TODO Auto-generated method stub
		BSTNode<K, T> p = root;
		Pair<Boolean, Integer> pa1 = new Pair<Boolean, Integer>(false, 0);
		int count = 0;
		if (empty())
			return pa1;
		while (p != null) {
			count++;
			if (key.compareTo(p.key) == 0) {
				current = p;
				pa1.first = true;
				pa1.second = count;
				return pa1;
			} else if (key.compareTo(p.key) < 0)
				p = p.left;
			else
				p = p.right;
		}
		pa1.second = count;
		return pa1;
	}

	@Override
	public Pair<Boolean, Integer> insert(K key, T data) {
		// TODO Auto-generated method stub
		BSTNode<K, T> p = root, q = current;
		Pair<Boolean, Integer> pa1 = new Pair<Boolean, Integer>(true, 0);
		if (find(key).first) {
			pa1 = find(key);
			pa1.first = false;
			current = q;
			return pa1;
		}
		BSTNode<K, T> ins = new BSTNode<K, T>(key, data);
		int count = 0;
		if (empty()) {
			root = current = ins;
			return pa1;
		}
		while (p != null) {
			count++;
			q = p;
			if (key.compareTo(p.key) < 0)
				p = p.left;
			else
				p = p.right;

		}
		if (key.compareTo(q.key) < 0)
			q.left = ins;
		else
			q.right = ins;
		current = ins;
		pa1.second = count;
		return pa1;
	}

	@Override
	public Pair<Boolean, Integer> remove(K key) {
		// TODO Auto-generated method stub
		BSTNode<K, T> p = root, q = null;
		Pair<Boolean, Integer> pa1 = new Pair<Boolean, Integer>(false, 0);
		int count = 0;
		while (p != null && key.compareTo(p.key) != 0) {
			count++;
			q = p;
			if (key.compareTo(p.key) < 0)
				p = p.left;
			else
				p = p.right;
		}
		if (p == null) {
			pa1.second = count;
			return pa1;
		}
		count++;
		if ((p.left != null) && (p.right != null)) {
			BSTNode<K, T> min = p.right;
			q = p;
			while (min.left != null) {
				q = min;
				min = min.left;
			}
			p.key = min.key;
			p.data = min.data;
			p = min;
			key = min.key;
		}
		if (p.left != null)
			p = p.left;
		else
			p = p.right;
		if (q == null)
			root = p;
		else {
			if (key.compareTo(q.key) < 0)
				q.left = p;
			else
				q.right = p;
		}
		current = root;
		pa1.first = true;
		pa1.second = count;
		return pa1;
	}

	@Override
	public List<K> getAll() {
		// TODO Auto-generated method stub
		List<K> tmp = new LinkedList<K>();
		inOrder(root, tmp);
		return tmp;
	}

	private void inOrder(BSTNode<K, T> node, List<K> add) {
		if (node == null) {
			return;
		}
		inOrder(node.left, add);
		add.insert(node.key);
		inOrder(node.right, add);
	}
}
