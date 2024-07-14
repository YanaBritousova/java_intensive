package org.example;

import java.util.ArrayList;
import java.util.Collection;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        CustomArrayList<Integer> customArrayList = new CustomArrayListImpl<>(4);
        Collection<Integer> c = new ArrayList<>();
        c.add(0);
        c.add(1);
        c.add(2);
        c.add(3);
        customArrayList.addAll(c);
        System.out.println(customArrayList.toString());


    }
}