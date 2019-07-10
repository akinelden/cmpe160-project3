import java.util.ArrayList;

public class Main{
    public static void main(String[] args){
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
    }

    private static void printArrayList(ArrayList<Double> array){
        for(Double d : array){
            System.out.print(d.toString() + " ");
        }
        System.out.println("\n");
    }
}