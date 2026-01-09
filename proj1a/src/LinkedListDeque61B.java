import java.util.List;
import java.util.ArrayList;
public class LinkedListDeque61B<T> implements Deque61B<T> {
    private Node<T> sentinel;
    private int size;

    public static class Node<T> {
        public T item;
        public Node<T> next;
        public Node<T> prev;
        public Node(T i) {
            item = i;
        }
    }

    public LinkedListDeque61B() {
        sentinel = new Node<>(null);
        sentinel.next = sentinel;
        sentinel.prev = sentinel;
        size = 0;
    }

    @Override
    public void addFirst(T x) {
        Node<T> newNode = new Node<>(x);
        newNode.prev = sentinel;
        newNode.next = sentinel.next;
        sentinel.next.prev = newNode;
        sentinel.next = newNode;
        size++;
    }

    @Override
    public void addLast(T x) {
        Node<T> newNode = new Node<>(x);
        newNode.next = sentinel;
        newNode.prev = sentinel.prev;
        sentinel.prev.next = newNode;
        sentinel.prev = newNode;
        size++;
    }

    @Override
    public List<T> toList() {
        List<T> list = new ArrayList<>();
        Node<T> current = sentinel.next;
        while (current != sentinel) {
            list.add(current.item);
            current = current.next;
        }
        return list;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public int size() {
        return size;
    }
}