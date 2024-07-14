package org.example;

import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.Objects;

public class CustomArrayListImpl<E> implements CustomArrayList<E>{

    private E[] objects;
    private int size;

    @SuppressWarnings("unchecked")
    public CustomArrayListImpl() {
        this.objects = (E[]) new Object[]{};
    }

    @SuppressWarnings("unchecked")
    public CustomArrayListImpl(int size) {
        this.objects = (E[]) new Object[size];
    }

    @SuppressWarnings("unchecked")
    private E[] increase(E[]objects, int newSize){
        E[] newObjects = (E[])new Object[newSize];
        System.arraycopy(objects,0,newObjects,0,objects.length);
        return newObjects;
    }

    @Override
    public boolean add(E element) {
        int tempSize=size;
        if (this.size==this.objects.length){
            objects = increase(objects,tempSize+1);
        }
        objects[size]=element;
        this.size++;
        return true;
    }

    @Override
    public boolean add(int index, E element) {
        Objects.checkIndex(index, size);
        int tempSize=size;
        if (size==objects.length){
            objects = increase(objects, tempSize+1);
        }
        System.arraycopy(objects, index,objects,index+1,size-index);
        objects[index] = element;
        this.size++;
        return true;
    }

    @Override
    public void addAll(Collection<? extends E> c) {
        if (c.size()> objects.length-size){
            objects = increase(objects,size+c.size());
        }
        int s = size;
        for (E e : c){
            objects[s]=e;
            s++;
        }
        size+=c.size();

    }

    @SuppressWarnings("unchecked")
    @Override
    public void clear() {
        this.objects = (E[]) new Object[]{};
        this.size = 0;
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
        Objects.checkIndex(index,size);
        System.arraycopy(objects,index+1,objects,index,size-index-1);
        objects[size-1]=null;
    }

    @Override
    public void remove(Object o) {
        int i;
        for (i=0;i<size;i++){
            if (objects[i].equals(o)) break;
        }
        this.remove(i);
    }

    @Override
    public void sort(Comparator<? super E> c) {
        quickSort(objects, 0, size - 1, c);
    }

    private void quickSort(E[] array, int low, int high, Comparator<? super E> c) {
        if (low < high) {
            int pi = partition(array, low, high, c);

            quickSort(array, low, pi - 1, c);
            quickSort(array, pi + 1, high, c);
        }
    }

    private int partition(E[] array, int low, int high, Comparator<? super E> c) {
        E pivot = array[high];
        int i = (low - 1);

        for (int j = low; j <= high - 1; j++) {

            if (c.compare(array[j], pivot) <= 0) {
                i++;

                E temp = array[i];
                array[i] = array[j];
                array[j] = temp;
            }
        }

        E temp = array[i + 1];
        array[i + 1] = array[high];
        array[high] = temp;

        return i + 1;
    }

    @Override
    public String toString() {
        return "CustomArrayListImpl{" +
                "objects=" + Arrays.toString(objects) +
                ", size=" + size +
                '}';
    }
}
