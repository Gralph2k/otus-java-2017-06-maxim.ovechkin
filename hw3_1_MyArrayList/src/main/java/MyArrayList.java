import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.*;

/**
 * Created by maxim.ovechkin on 20.06.2017.
 */
public class MyArrayList<T> implements List<T> {
    final int ARRAY_GROWTH_STEP = 100;
    Object[] elements;
    private int size=0;
    private static final int MAX_ARRAY_SIZE = 2147483639;
    
     public MyArrayList(int size){
         if (size<=0)
             throw new IllegalArgumentException("Illegal size:" + size);
         elements = new Object[size];
    }

    public MyArrayList() {
        elements = new Object[ARRAY_GROWTH_STEP];
    }

    public boolean isEmpty() {
        return this.size==0;
    }

    public int size() {
        return this.size;
    }
    
    public boolean contains(Object o) {
        return indexOf(o) != -1;
    }

    
    public Iterator<T> iterator() {
        return new MyListIterator();
    }

    public Object[] toArray() {
        return Arrays.copyOf(elements,size());
    }


    public <T1> T1[] toArray(T1[] t1s) {
        throw new NotImplementedException();
    }

    private void checkArraySize(int index){
         if (index>MAX_ARRAY_SIZE)
             throw new ArrayIndexOutOfBoundsException("Max elements count reached");
    }

    private void adjustArraySize(){
        if (size+1>elements.length) {
            this.elements=Arrays.copyOf(elements,Math.max(elements.length+ARRAY_GROWTH_STEP,MAX_ARRAY_SIZE));
        }
    }
    
    public boolean add(T t) {
        checkArraySize(size()+1);
        adjustArraySize();
        elements[size++]=t;
        return true;
    }

    
    public boolean remove(Object o) {
        throw new NotImplementedException();
    }

    
    public boolean containsAll(Collection<?> collection) {
        throw new NotImplementedException();
    }

    
    public boolean addAll(Collection<? extends T> collection) {
        throw new NotImplementedException();
    }

    
    public boolean addAll(int i, Collection<? extends T> collection) {
        throw new NotImplementedException();
    }

    
    public boolean removeAll(Collection<?> collection) {
        throw new NotImplementedException();
    }

    
    public boolean retainAll(Collection<?> collection) {
        throw new NotImplementedException();
    }

    
    public void clear() {
        throw new NotImplementedException();
    }

    private T elements(int i){
        return (T) this.elements[i];
    }

    public T get(int i) {
        checkIndex(i);
        return elements(i);
    }

    public T set(int i, T t) {
        checkIndex(i);
        Object o =  elements(i);
        elements[i]=t;
        return (T) o;
    }

    private void checkIndex(int i){
        if (i<0||i>=size()) {
            throw new ArrayIndexOutOfBoundsException("Index out of bounds:"+i);
        }
    }
    
    public void add(int i, T t) {
        throw new NotImplementedException();
    }

    
    public T remove(int i) {
        throw new NotImplementedException();
    }

    
    public int indexOf(Object o) {
         for (int i = 0; i < size; i++) {
            if (o.equals(elements[i])) {
                return i;
            }
        }
        return -1;
    }

    
    public int lastIndexOf(Object o) {
        throw new NotImplementedException();
    }

    
    public ListIterator<T> listIterator() {
        return new MyListIterator();
    }

    
    public ListIterator<T> listIterator(int i) {
        throw new NotImplementedException();
    }

    
    public List<T> subList(int i, int i1) {
        throw new NotImplementedException();
    }


    private class MyListIterator implements ListIterator<T>{
        int cursor;

        private MyListIterator(){
            this(0);
        }

        private MyListIterator(int cursor) {
            this.cursor = cursor;
        }

        public boolean hasNext() {
            return cursor<size();
        }

        public T next() {
            T t = elements(cursor);
            cursor++;
            return t;
        }

        public boolean hasPrevious() {

            return cursor>0;
        }

        public T previous() {
            return elements(cursor--);
        }

        public int nextIndex() {
            return cursor;
        }

        public int previousIndex() {
            return cursor-1;
        }

        public void remove() {
            throw new NotImplementedException();
        }

        public void set(T t) {
            int currentIndex=cursor-1;
            MyArrayList.this.set(currentIndex,t);
        }

        public void add(T t) {
            throw new NotImplementedException();
        }
    }
}
