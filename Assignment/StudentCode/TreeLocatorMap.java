
public class TreeLocatorMap<K extends Comparable<K>> implements LocatorMap<K> {
	public Map<K, Location> BSTmap = new BST<K, Location>();
	public Locator<K> treeLocator = new TreeLocator<K>();

	@Override
	public Map<K, Location> getMap() {
		// TODO Auto-generated method stub
		return BSTmap;
	}

	@Override
	public Locator<K> getLocator() {
		// TODO Auto-generated method stub
		return treeLocator;
	}

	@Override
	public Pair<Boolean, Integer> add(K k, Location loc) {
		// TODO Auto-generated method stub
		Pair<Boolean, Integer> pa1 = new Pair<>(false, 0);
		if (!BSTmap.empty())
			pa1 = BSTmap.find(k);
		if (pa1.first) {
			pa1.first = false;
			return pa1;
		}
		pa1 = BSTmap.insert(k, loc);
		treeLocator.add(k, loc);
		return pa1;
	}

	@Override
	public Pair<Boolean, Integer> move(K k, Location loc) {
		// TODO Auto-generated method stub
		Pair<Boolean, Integer> pa1 = new Pair<>(false, 0);
		if (BSTmap.empty())
			return pa1;
		pa1 = BSTmap.find(k);
		if (!pa1.first)
			return pa1;
		Location oldLoc = BSTmap.retrieve();
		BSTmap.remove(k);
		treeLocator.remove(k, oldLoc);
		BSTmap.insert(k, loc);
		treeLocator.add(k, loc);
		return pa1;
	}

	@Override
	public Pair<Location, Integer> getLoc(K k) {
		// TODO Auto-generated method stub
		Pair<Location, Integer> pa1 = new Pair<>(null, 0);
		if (BSTmap.empty())
			return pa1;
		if (!BSTmap.find(k).first){
			pa1.second = BSTmap.find(k).second;
			return pa1;
		}
		pa1.second = BSTmap.find(k).second;
		pa1.first = BSTmap.retrieve();
		return pa1;
	}

	@Override
	public Pair<Boolean, Integer> remove(K k) {
		// TODO Auto-generated method stub
		Pair<Boolean, Integer> pa1 = new Pair<>(false, 0);
		if (BSTmap.empty())
			return pa1;
		pa1 = BSTmap.find(k);
		if (!pa1.first)
			return pa1;
		BSTmap.find(k);
		Location oldLoc = BSTmap.retrieve();
		pa1 = BSTmap.remove(k);
		treeLocator.remove(k, oldLoc);
		return pa1;
	}

	@Override
	public List<K> getAll() {
		// TODO Auto-generated method stub
		return BSTmap.getAll();
	}

	@Override
	public Pair<List<K>, Integer> getInRange(Location lowerLeft, Location upperRight) {
		// TODO Auto-generated method stub
		Pair<List<Pair<Location, List<K>>>, Integer> pa1 = treeLocator.inRange(lowerLeft, upperRight);
		List<Pair<Location, List<K>>> tmp1 = pa1.first;
		List<K> tmp2 = new LinkedList<>();
		if (!tmp1.empty()) {
			tmp1.findFirst();
			while (!tmp1.last()) {
				List<K> tmp3 = tmp1.retrieve().second;
				if (!tmp3.empty()) {
					tmp3.findFirst();
					while (!tmp3.last()) {
						tmp2.insert(tmp3.retrieve());
						tmp3.findNext();
					}
					tmp2.insert(tmp3.retrieve());
				}
				tmp1.findNext();
			}
			List<K> tmp3 = tmp1.retrieve().second;
			if (!tmp3.empty()) {
				tmp3.findFirst();
				while (!tmp3.last()) {
					tmp2.insert(tmp3.retrieve());
					tmp3.findNext();
				}
				tmp2.insert(tmp3.retrieve());
			}
		}
		Pair<List<K>, Integer> pa2 = new Pair<List<K>, Integer>(tmp2, pa1.second);
		return pa2;
	}

}
