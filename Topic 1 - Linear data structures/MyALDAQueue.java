//Filip Lingefelt, fili8261

import java.util.*;
import java.util.Collection;
import java.util.Iterator;

public class MyALDAQueue<E> implements ALDAQueue<E>{


    private Node<E> first;
    private Node<E> last;
    private int size;
    private int totalCapacity;

    public MyALDAQueue(int totalCapacity){
        if (totalCapacity <= 0){
            throw new IllegalArgumentException("Capacity must be over 0");
        }
        this.totalCapacity = totalCapacity;
    }

    @Override
    public Iterator<E> iterator() {
        return new ALDAQueueIterator();
    }

    private class ALDAQueueIterator implements Iterator<E> {

        private Node<E> current = first;


        public boolean hasNext() {
            if(current == null){
                return false;
            }
            return true;
        }


        public E next() {
            if(current == null){
                throw new NoSuchElementException("No such element");
            }

            if(hasNext()){
                E element = current.data;
                current = current.next;
                return element;
            }else{
                return null;
            }
        }
    }
    private static class Node<E> {
        private E data;
        private Node<E> next;

        Node(E data) {
            this.data = data;
        }
    }

    public void add(E element){
        if(isFull()){
            throw new IllegalStateException("It is full");
        }

        if(element == null){
            throw new NullPointerException("Can't add null");
        }

        if (isEmpty()){
            first = new Node<E>(element);
            last = first;
        }else{
            last.next = new Node<E>(element);
            last = last.next;
        }
        size++;
    }

    public void addAll(Collection<? extends E> c){
        for (E element : c){
            add(element);
        }
    }

    public E remove(){
        if (isEmpty()) {
            throw new NoSuchElementException("It is empty");
        }
        E element = first.data;
        first = first.next;
        if (first == null) {
            last = null;
        }
        size--;
        return element;
    }

    public E peek(){
        if (isEmpty()) {
            return null;
        }
        return first.data;
    }

    public void clear(){
        first = null;
        last = null;
        size = 0;

    }

    public int size(){
        return size;
    }

    public boolean isEmpty(){
        return size == 0 || totalCapacity == 0;
    }

    public boolean isFull(){
        return totalCapacity == size;
    }

    /**
     * Set when creating the queue.
     */
    public int totalCapacity(){
        return totalCapacity;
    }


    public int currentCapacity(){
        return totalCapacity() - size();
    }

    /**
     * Move all elements equal to e to the end of the queue.
     *
     * @param e
     * @throws NullPointerException if e is null.
     * @return the number of elements discriminated.
     */
    @Override
    public int discriminate(E e) {
        if (e == null) {
            throw new NullPointerException("Input is null");
        }
        int count = 0;
        int temp = 0;
        Node<E> current = first;
        Node<E> removed = null;
        Node<E> previous = null;

        while (current != null && temp < size) {
            if(current.data.equals(e)) {
                removed = current;
                if(first != removed){
                    previous.next = removed.next;

                }else { //första
                    first = removed.next;
                }

                if(removed.next == null){ //sista
                    last = previous;
                }

                last.next = removed;
                last = removed;
                current = current.next;
                last.next = null;
                count++;

            } else {
                previous = current;
                current = current.next;


            }
            temp++;
        }
        return count;
    }


    @Override
    public String toString() {
        return toString(first);
    }

    private String toString(Node<E> current) {
        StringBuilder sb = new StringBuilder();
        while (current != null){
            sb.append(current.data);
            current = current.next;
            if(current != null){
                sb.append(", ");
            }
        }
        return "[" + sb + "]";
    }

}
