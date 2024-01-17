import structure.CyclicalLinkedList;
import java.io.IOException;


public class Main {
    public static void main(String[] args) throws CyclicalLinkedList.SimpleLinkedListException, IOException {
        CyclicalLinkedList<Integer> list=new CyclicalLinkedList<>();
        list.addLast(3);
        list.addLast(9);
        list.addLast(2);
        list.addLast(3);
        list.addLast(9);
        list.addLast(10);
        list.addLast(2);
        list.print();
        CyclicalLinkedList<Integer> list1=new CyclicalLinkedList<>();
        list1.addLast(3);
        list1.addLast(9);
        list1.addLast(2);
        list1.addLast(3);
        list1.addLast(9);
        list1.addLast(2);
        list1.print();
        System.out.println(list1.equals(list));
    }
}