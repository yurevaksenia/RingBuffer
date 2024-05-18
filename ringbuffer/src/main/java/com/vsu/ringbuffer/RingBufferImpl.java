package com.vsu.ringbuffer;

public class RingBufferImpl<E> implements RingBuffer<E>{
    private Node<E> head;           //указатель на начало буфера
    private Node<E> tail;           //указатель на конец буфера
    public int maxSize = 5;         //максимальный размер буфера
    private int countElements;      //текущее количество элементов


    public RingBufferImpl(int size){
        head = null; 
        tail = null;
        maxSize = size;
        countElements = 0;
    }

    //возвращает и удаляет элемент из начала очереди
    @Override
    public E poll(){
        if (countElements == 0){
            return null;
        }
        E item = head.value;
        if (countElements < 2)  {
            head = tail = null;
        }
        else {
            tail.next = head.next;
            head = tail.next;
        }
        countElements--;
        return item;
    }

    //возвращает (но не удаляет) элемент из начала очереди
    @Override
    public E peek() { return (countElements == 0 ? null : head.value); }


    //добавляет элемент в конец очереди. Затирает начало очереди в случае, если она заполнена.
    @Override
    public void add(E item) {
        if (countElements == 0)
        {
            head = new Node<>(item);
            head.next = head;
            tail = head;
        }
        else    {
            if (countElements == maxSize) {
                head = head.next;
                countElements--;
            }
            Node<E> newNode = new Node<>(item);
            newNode.next = head;
            tail.next = newNode;
            tail = newNode;
        }
        countElements++;
    }

    //возвращает размер коллекции
    @Override
    public int getSize() {
        return countElements;
    }

    @Override
    public String toString() {
        if (getSize() == 0) return "Буффер пуст";
        StringBuilder strBuffer = new StringBuilder();
        Node<E> current = head;
        while (current != tail) {
            strBuffer.append(current.value.toString()).append(" ");
            current = current.next;
        }
        return strBuffer.append(tail.value.toString()).toString();
    }

    public boolean isFull() { return countElements == maxSize; }

    public boolean isEmpty() { return countElements == 0; }

}
