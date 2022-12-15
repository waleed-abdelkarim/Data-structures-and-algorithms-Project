public class BSTNode <K, T> {
	public K key;
	public T data;
	public BSTNode <K, T> left, right;

	public BSTNode(K k, T e) {
		key = k;
		data = e;
		left = right = null;
	}
	
	public BSTNode(K k, T e, BSTNode <K, T> l, BSTNode <K, T> r) {
		key = k;
		data = e;
		left = l;
		right = r;
	}
}
