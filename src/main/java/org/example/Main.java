package org.example;

public class Main {
    public static void main(String[] args) {
        MyHashMap<String, Integer> myMap = new MyHashMap<>();

        myMap.put("Hello", 123);
        myMap.put("World", 567);
        myMap.put("!", 789);
        System.out.println(myMap.getSize());
        System.out.println(myMap.get("Hello"));
        System.out.println(myMap.remove("!"));
        System.out.println(myMap.getSize());
    }
}