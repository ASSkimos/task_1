package structure;

class ListNode<T> {
    public T value;
    public ListNode<T> next;
    public ListNode<T> prev;

    public ListNode(T value, ListNode<T> next, ListNode<T> prev) {
        this.value = value;
        this.next = next;
        this.prev = prev;
    }

    public ListNode(T value) {
        this(value, null, null);
    }
}
