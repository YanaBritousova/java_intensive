package org.example;
import java.util.Collection;
import java.util.Comparator;

public interface CustomArrayList<E> {

     boolean add(int index, E element);
     void addAll(Collection<? extends E> c);
     void clear();
     E get(int index);
     boolean isEmpty();
     void remove(int index);
     void remove(Object o);
     void sort(Comparator<? super E> c);

}
