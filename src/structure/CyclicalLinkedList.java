package structure;


import java.lang.reflect.Array;

public class CyclicalLinkedList<T> {


    private ListNode<T> head = null;
    private ListNode<T> tail = null;
    private int count = 0;

    public void clear() {
        ListNode temp = null;
        head = temp;
        tail = temp;
        count = 0;
    }
    public boolean isEmpty() {
        return count == 0;
    }

    public void addFirst(T value) {
        ListNode<T> temp = new ListNode<>(value, head, tail);
        if (count == 0) {
            tail = head = temp;
        } else {
            tail.next = temp;
            head.prev = temp;
            head = temp;
        }

        count++;
    }

    public void addLast(T value) {
        ListNode<T> temp = new ListNode<>(value, head, tail);
        if (count == 0) {
            tail = head = temp;
        } else {
            tail.next = temp;
            head.prev = temp;
            tail = temp;
        }

        count++;
    }

    public boolean contains(T value) throws SimpleLinkedListException {
        checkEmpty();
        ListNode curr = head;
        int i = 0;
        do {
            if (curr.value.equals(value)) {
                return true;
            }
            curr = curr.next;
            i++;
        }
        while (curr != head);
        return false;
    }

    public int indexOf(T value) throws SimpleLinkedListException {
        checkEmpty();
        ListNode curr = head;
        int i = 0;
        do {
            if (curr.value.equals(value)) {
                return i;
            }
            curr = curr.next;
            i++;
        }
        while (curr != head);
        return -1;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null) {
            return false;
        }
        if (this.getClass() != o.getClass()) {
            return false;
        }
        CyclicalLinkedList list=(CyclicalLinkedList) o;
        if (count != list.count) {
            return false;
        }
        ListNode curr1 = head;
        ListNode curr2 = list.head;
        do {
            if (!curr1.value.equals(curr2.value)) {
                return false;
            }
            curr1 = curr1.next;
            curr2 = curr2.next;
        }
        while (curr1 != head);
        return true;
    }
    public void set(int index, T value) {
        ListNode node =getNode(index);
        node.value=value;
    }


    private void checkEmpty() throws SimpleLinkedListException {
        if (count == 0) {
            throw new SimpleLinkedListException("Empty list");
        }
    }

    private ListNode<T> getNode(int index) {
        if (index < count / 2) {
            int i = 0;
            for (ListNode<T> curr = head; curr != null; curr = curr.next, i++) {
                if (i == index) {
                    return curr;
                }
            }
        } else {
            int i = count - 1;
            for (ListNode<T> curr = tail; curr != null; curr = curr.prev, i--) {
                if (i == index) {
                    return curr;
                }
            }
        }

        return null;
    }

    public T removeFirst() throws SimpleLinkedListException {
        checkEmpty();
        return remove(0);
    }

    public T removeLast() throws SimpleLinkedListException {
        checkEmpty();
        return remove(count - 1);
    }

    public T remove(int index) throws SimpleLinkedListException {
        checkEmpty();
        if (index < 0 || index >= count) {
            throw new SimpleLinkedListException("Incorrect index");
        }
        checkEmpty();
        ListNode<T> curr = getNode(index);
        T value = curr.value;
        ListNode<T> prev = curr.prev;
        ListNode<T> next = curr.next;
        prev.next = next;
        next.prev = prev;
        if (index == 0) {
            head = next;
        }
        if (index == count - 1) {
            tail = prev;
        }
        count--;
        return value;
    }

    public void insert(int index, T value) throws SimpleLinkedListException {
        if (index < 0 || index >= count) {
            throw new SimpleLinkedListException("Incorrect index");
        }
        if (index == 0) {
            addFirst(value);
        } else {
            ListNode<T> prev = getNode(index - 1);
            ListNode<T> curr = prev.next;
            ListNode<T> temp = new ListNode<>(value, curr, prev);
            prev.next = temp;
            curr.prev = temp;
            count++;
        }
    }

    public int size() {
        return count;
    }

    public T getFirst() throws SimpleLinkedListException {
        checkEmpty();

        return head.value;
    }

    public T getLast() throws SimpleLinkedListException {
        checkEmpty();

        return tail.value;
    }

    public T get(int index) throws SimpleLinkedListException {
        if (index < 0 || index >= count) {
            throw new SimpleLinkedListException("Incorrect index");
        }
        return getNode(index).value;
    }

    public void print() {
        ListNode curr = head;
        do {
            System.out.print(curr.value + " ");
            curr = curr.next;
        }
        while (curr != head);
        System.out.println();
    }

    public static <T> T[] toArray(CyclicalLinkedList<T> list) throws SimpleLinkedListException {
        Class<?> runtimeType = list.size() > 0 ? list.get(0).getClass() : Object.class;
        T[] array = (T[]) Array.newInstance(runtimeType, list.size());
        int i = 0;
        ListNode head = list.head;
        ListNode curr = head;
        do {
            array[i++] = (T) curr.value;
            curr = curr.next;

        } while (curr != head);
        return array;
    }
    public static class SimpleLinkedListException extends Exception {
        public SimpleLinkedListException(String message) {
            super(message);
        }
    }
}
