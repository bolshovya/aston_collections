package org.example;

import java.util.Arrays;

public class MyHashMap<K,V> {

    @Override
    public String toString() {
        return "MyHashMap{" +
                "table=" + Arrays.toString(table) +
                '}';
    }

    public int getSize() {
        return size;
    }


    private class Node<K,V> {
        private final int hash;
        private final K key;
        private V value;
        private Node<K,V> next;

        public Node(int hash, K key, V value) {
            this.hash = hash;
            this.key = key;
            this.value = value;
        }

        public int getHash() {
            return hash;
        }

        public K getKey() {
            return key;
        }

        public V getValue() {
            return value;
        }

        public void setValue(V value) {
            this.value = value;
        }

        public Node<K, V> getNext() {
            return next;
        }

        public void setNext(Node<K, V> next) {
            this.next = next;
        }

        public final String toString() { return key + "=" + value; }
    }

    private int capacity = 16;

    private Node<K,V>[] table;

    private int size = 0;

    public MyHashMap() {
        this.table = new Node[capacity];
    }

    public void put(K key, V value) {
        int hash = key.hashCode();
        int index = hash % capacity;
        Node<K,V> n = table[index];

        if (n == null) {
            table[index] = new Node<K,V>(hash, key, value);
            size++;
        } else {
            while(n.getNext() != null) {
                if (n.getHash() == hash && n.getKey().equals(key)) {
                    n.setValue(value);
                    return;
                }
                n = n.getNext();
            }
            if (n.getHash() == hash && n.getKey().equals(key)) {
                n.setValue(value);
            } else {
                n.setNext(new Node<K,V>(hash, key, value));
                size++;
            }
        }
    }

    public V get(K key) {
        int hash = key.hashCode();
        int index = hash % capacity;
        Node<K,V> n = table[index];

        if (n == null) {
            return null;
        }

        while (n != null) {
            if (n.getHash() == hash && n.getKey().equals(key)) {
                return n.getValue();
            }
            n = n.getNext();
        }

        return null;
    }

    public V remove(K key) {
        int hash = key.hashCode();
        int index = hash % capacity;
        Node<K,V> n = table[index];

        if (n == null) {
            return null;
        }

        if (n.getHash() == hash && n.getKey().equals(key)) {
            table[index] = n.getNext();
            size--;
            return n.getValue();
        }

        while (n != null) {
            Node<K,V> prev = n;
            n = n.getNext();
            if (n.getHash() == hash && n.getKey().equals(key)) {
                prev.setNext(n.getNext());
                size--;
                return n.getValue();
            }
        }

        return null;
    }
}
