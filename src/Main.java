import java.util.ArrayList;

public class Main{
    public static void main(String[] args){
        /*
        AVLTree<Double> myAVL = new AVLTree<>();
        myAVL.insert(9.0);
        printArrayList(myAVL.bfTraverse());
        myAVL.insert(5.0);
        printArrayList(myAVL.bfTraverse());
        myAVL.insert(12.0);
        printArrayList(myAVL.bfTraverse());
        myAVL.insert(3.0);
        printArrayList(myAVL.bfTraverse());
        myAVL.insert(6.0);
        printArrayList(myAVL.bfTraverse());
        myAVL.insert(8.0);
        printArrayList(myAVL.bfTraverse());
        myAVL.insert(14.0);
        printArrayList(myAVL.bfTraverse());
        myAVL.insert(17.0);
        printArrayList(myAVL.bfTraverse());
        myAVL.insert(7.0);
        printArrayList(myAVL.bfTraverse());
        myAVL.insert(8.5);
        printArrayList(myAVL.bfTraverse());
        printArrayList(myAVL.inOrderTraversal());
        myAVL.delete(5.0);
        printArrayList(myAVL.bfTraverse());
        myAVL.delete(5.0);
        printArrayList(myAVL.bfTraverse());
        myAVL.delete(7.0);
        printArrayList(myAVL.bfTraverse());
        myAVL.delete(3.0);
        printArrayList(myAVL.bfTraverse());
        myAVL.delete(9.0);
        printArrayList(myAVL.bfTraverse());
        */
        
		AVLTree<Integer> tree = new AVLTree<Integer>();
		tree.insert(50);
		tree.insert(10);
		if(!tree.isEmpty())
			tree.insert(100);
		tree.insert(90);
		tree.insert(5); 
		if(!tree.contains(15)) 
			tree.insert(0);
		tree.insert(-5);
		tree.insert(-70); 
		if(tree.size() > 5)
			tree.insert(8);
		tree.insert(6);
		tree.delete(0);
		if(tree.numElementsInRange(5, 50) > 3) 
			tree.delete(-5);
		tree.insert(-80);
		tree.insert(100);
		if(tree.balanceFactor(50) < 1) 
		tree.insert(110);
		tree.insert(120);
		tree.delete(50);
		tree.insert(20);
		tree.insert(25);
		if(tree.areCousins(20, 100))
			tree.delete(5);
		tree.delete(-5); 
		tree.insert(9);
		tree.delete(100);
		tree.delete(20);
		tree.insert(7);
		tree.insert(60);
		tree.insert(70);
		if(tree.height() < 6)
			tree.delete(8);
		System.out.println("BFTraverse:\n" + tree.bfTraverse());
		System.out.println("True one:\n[9, 5, 60, -70, 6, 25, 90, -80, 7, 10, 70, 120]\n");
		System.out.println("Your InorderTraversal:\n" + tree.inOrderTraversal());
		System.out.println("True one:\n" + "[-80, -70, 5, 6, 7, 9, 10, 25, 60, 70, 90, 120]");
    }

    /**
     * The function to write doubles in the <code>array</code>
     * seperated with space.
     * @param array The array containing doubles
     */
    private static void printArrayList(ArrayList<Double> array){
        for(Double d : array){
            System.out.print(d.toString() + " ");
        }
        System.out.println("\n");
    }
}