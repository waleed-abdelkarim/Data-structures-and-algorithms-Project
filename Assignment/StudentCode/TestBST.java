
public class TestBST {

	public static void main(String[] args) {

		// TODO Auto-generated method stub
		BST<Integer, Integer> b1 = new BST<Integer, Integer>();
		System.out.println("Insert\n************");
		System.out.println(b1.insert(15, 1).toString());
		System.out.println(b1.insert(15, 1).toString());
		System.out.println(b1.insert(12, 1).toString());
		System.out.println(b1.insert(13, 1).toString());
		System.out.println(b1.insert(20, 1).toString());
		System.out.println(b1.insert(20, 1).toString());
		System.out.println(b1.insert(11, 1).toString());
		System.out.println(b1.insert(17, 1).toString());
		System.out.println(b1.insert(30, 1).toString());
		System.out.println(b1.insert(41, 1).toString());
		System.out.println(b1.insert(35, 1).toString());
//		System.out.println("getAll\n************");
//		b1.getAll().print();
		System.out.println("find\n************");
		System.out.println(b1.find(12).toString());
		System.out.println(b1.find(50).toString());
		System.out.println("remove1\n************");
		System.out.println(b1.remove(11).toString());// no children
		System.out.println(b1.remove(20).toString());// two children
		System.out.println(b1.remove(15).toString());// root
		System.out.println(b1.remove(11).toString());// removed before
		System.out.println(b1.remove(30).toString());// with right child
		System.out.println(b1.remove(41).toString());// with left child
//		System.out.println("getAll\n************");
//		b1.getAll().print();
		System.out.println("remove2\n************");
		System.out.println(b1.remove(12).toString());
		System.out.println(b1.remove(13).toString());
		System.out.println(b1.remove(17).toString());
		System.out.println(b1.remove(35).toString());
		// to make sure for delete in empty BST
		System.out.println(b1.remove(12).toString());
		System.out.println(b1.remove(13).toString());
		System.out.println(b1.remove(17).toString());
		System.out.println(b1.remove(35).toString());
//		System.out.println("getAll\n************");
//		b1.getAll().print();
	}

}
