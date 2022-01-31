package lesson2;

public class Main {

    public static void main(String[] args) {
        SimpleLinkedListImpl<Integer> list = new SimpleLinkedListImpl<>();
        list.insertFirst(1);
        list.insertFirst(2);
        list.insertFirst(3);
        list.insertFirst(4);
        System.out.println(list.toString());
        list.reverse();
        System.out.println(list.toString());
        System.out.println(list.hasLoop());

    }
}
