public class TLNode<T> {
	public Location loc;
	public List<T> data = new LinkedList<T>();
	public TLNode<T> c1, c2, c3, c4;

	public TLNode(Location l, T e) {
		loc = l;
		data.insert(e);
		c1 = c2 = c3 = c4 = null;
	}
}