package com.vsu.ringbuffer;
//ненужный комментарий, который можно удалить
//для проверки работы триггера Jenkins
public class Node<E>{
    E value;
    Node<E> next;

    public Node(E value) {
        this.value = value;
        next = null;
    }
}
