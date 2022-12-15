public class TreeLocator<T> implements Locator<T> {
	public TLNode<T> root;

	public TreeLocator() {
		root = null;
	}

	@Override
	public int add(T e, Location loc) {
		// TODO Auto-generated method stub
		if (root == null) {
			root = new TLNode<T>(loc, e);
			return 0;
		}
		TLNode<T> p = root;
		int count = 0;
		while (p != null) {
			count++;
			if (loc.x == p.loc.x && loc.y == p.loc.y) {
				p.data.insert(e);
				return count;
			} else if (loc.x < p.loc.x && loc.y <= p.loc.y && p.c1 != null) {
				p = p.c1;
			} else if (loc.x <= p.loc.x && loc.y > p.loc.y && p.c2 != null) {
				p = p.c2;
			} else if (loc.x > p.loc.x && loc.y >= p.loc.y && p.c3 != null) {
				p = p.c3;
			} else if (loc.x >= p.loc.x && loc.y < p.loc.y && p.c4 != null) {
				p = p.c4;
			} else {
				break;
			}
		}
		TLNode<T> ins = new TLNode<T>(loc, e);
		if (loc.x < p.loc.x && loc.y <= p.loc.y) {
			p.c1 = ins;
		} else if (loc.x <= p.loc.x && loc.y > p.loc.y) {
			p.c2 = ins;
		} else if (loc.x > p.loc.x && loc.y >= p.loc.y) {
			p.c3 = ins;
		} else if (loc.x >= p.loc.x && loc.y < p.loc.y) {
			p.c4 = ins;
		}
		return count;
	}

	@Override
	public Pair<List<T>, Integer> get(Location loc) {
		// TODO Auto-generated method stub
		List<T> tmp = new LinkedList<T>();
		Pair<List<T>, Integer> pa1 = new Pair<List<T>, Integer>(tmp, 0);
		if (root == null)
			return pa1;
		TLNode<T> p = root;
		int count = 0;
		while (p != null) {
			count++;
			if (loc.x == p.loc.x && loc.y == p.loc.y) {
				tmp = p.data;
				pa1.first = p.data;
				p.data.findFirst();
				pa1.second = count;
				return pa1;
			} else if (loc.x < p.loc.x && loc.y <= p.loc.y && p.c1 != null) {
				p = p.c1;
			} else if (loc.x <= p.loc.x && loc.y > p.loc.y && p.c2 != null) {
				p = p.c2;
			} else if (loc.x > p.loc.x && loc.y >= p.loc.y && p.c3 != null) {
				p = p.c3;
			} else if (loc.x >= p.loc.x && loc.y < p.loc.y && p.c4 != null) {
				p = p.c4;
			} else {
				break;
			}
		}
		pa1.second = count;
		return pa1;
	}

	@Override
	public Pair<Boolean, Integer> remove(T e, Location loc) {
		// TODO Auto-generated method stub
		Pair<Boolean, Integer> pa1 = new Pair<Boolean, Integer>(false, 0);
		if (root == null)
			return pa1;
		TLNode<T> p = root;
		int count = 0;
		while (p != null) {
			count++;
			if (loc.x == p.loc.x && loc.y == p.loc.y) {
				break;
			} else if (loc.x < p.loc.x && loc.y <= p.loc.y && p.c1 != null) {
				p = p.c1;
			} else if (loc.x <= p.loc.x && loc.y > p.loc.y && p.c2 != null) {
				p = p.c2;
			} else if (loc.x > p.loc.x && loc.y >= p.loc.y && p.c3 != null) {
				p = p.c3;
			} else if (loc.x >= p.loc.x && loc.y < p.loc.y && p.c4 != null) {
				p = p.c4;
			} else {
				break;
			}
		}
		boolean removed = false;
		if (loc.x == p.loc.x && loc.y == p.loc.y) {
			if (!p.data.empty()) {
				p.data.findFirst();
				while (!p.data.last()) {
					if (p.data.retrieve().equals(e)) {
						p.data.remove();
						removed = true;
					} else if (!p.data.last())
						p.data.findNext();
				}
			}
			if (!p.data.empty()) {
				if (p.data.retrieve().equals(e)) {
					p.data.remove();
					removed = true;
				}
			}
			pa1.first = removed;
		}
		pa1.second = count;
		return pa1;
	}

	@Override
	public List<Pair<Location, List<T>>> getAll() {
		// TODO Auto-generated method stub
		List<Pair<Location, List<T>>> all = new LinkedList<Pair<Location, List<T>>>();
		inOrder(root, all);
		return all;
	}

	private void inOrder(TLNode<T> node, List<Pair<Location, List<T>>> add) {
		if (node == null) {
			return;
		}
		Pair<Location, List<T>> pa1 = new Pair<Location, List<T>>(node.loc, node.data);
		add.insert(pa1);
		inOrder(node.c1, add);
		inOrder(node.c2, add);
		inOrder(node.c3, add);
		inOrder(node.c4, add);
	}

	@Override
	public Pair<List<Pair<Location, List<T>>>, Integer> inRange(Location lowerLeft, Location upperRight) {
		// TODO Auto-generated method stub
		List<Pair<Location, List<T>>> tmp = new LinkedList<Pair<Location, List<T>>>();
		Box b = new Box(0);
		range(root, lowerLeft, upperRight, tmp, b);
		Pair<List<Pair<Location, List<T>>>, Integer> re = new Pair<List<Pair<Location, List<T>>>, Integer>(tmp, b.co);
		return re;
	}

	private void range(TLNode<T> n, Location lowerLeft, Location upperRight, List<Pair<Location, List<T>>> add,
			Box count) {
		if (n == null)
			return;
		count.co++;
		if ((n.loc.x >= lowerLeft.x && n.loc.x <= upperRight.x)
				&& (n.loc.y >= lowerLeft.y && n.loc.y <= upperRight.y)) {
			Pair<Location, List<T>> pa1 = new Pair<Location, List<T>>(n.loc, n.data);
			add.insert(pa1);
		}
		if (n.c1 != null && n.c1.loc.x >= lowerLeft.x && n.c1.loc.y >= lowerLeft.y) {
			range(n.c1, lowerLeft, upperRight, add, count);
		}
		if (n.c2 != null && n.c2.loc.y <= upperRight.y && n.c2.loc.x >= lowerLeft.x) {
			range(n.c2, lowerLeft, upperRight, add, count);
		}
		if (n.c3 != null && n.c3.loc.y <= upperRight.y && n.c3.loc.x <= upperRight.x) {
			range(n.c3, lowerLeft, upperRight, add, count);
		}
		if (n.c4 != null && n.c4.loc.y <= upperRight.y && n.loc.x <= upperRight.x && n.loc.y >= lowerLeft.y
				&& n.loc.x >= lowerLeft.x) {
			range(n.c4, lowerLeft, upperRight, add, count);
		}
	}

	class Box {
		public int co;

		public Box(int count) {
			co = count;
		}
	}
}
