package org.example;

import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.Objects;

public class CustomArrayListImpl<E> implements CustomArrayList<E>{

    private E[] objects;
    private static final int DEFAULT_SIZE = 10;
    private int size;

    @SuppressWarnings("unchecked")
    public CustomArrayListImpl() {
        this.objects = (E[]) new Object[]{};
    }


    @Override
    public boolean add(int index, E element) {
        return true;
    }

    @Override
    public void addAll(Collection<? extends E> c) {

    }

    @SuppressWarnings("unchecked")
    @Override
    public void clear() {
        this.objects = (E[]) new Object[10];
    }

    @Override
    public E get(int index) {
        Objects.checkIndex(index, size);
        return objects[index];
    }

    @Override
    public boolean isEmpty() {
        return this.size == 0;
    }

    @Override
    public void remove(int index) {

    }

    @Override
    public void remove(Object o) {

    }

    @Override
    public void sort(Comparator<? super E> c) {

    }

    @Override
    public String toString() {
        return "CustomArrayListImpl{" +
                "objects=" + Arrays.toString(objects) +
                ", size=" + size +
                '}';
    }
}
