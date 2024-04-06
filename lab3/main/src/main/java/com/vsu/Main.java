package com.vsu;

import com.vsu.ringbuffer.RingBuffer;
import com.vsu.factory.FactoryRingBuffer;

/* Полянская Ксения.
Реализовать фабрику (или фабричный метод), которая позволяет получить объекты RingBuffer с указанными типами параметризации.
При этом возращаться должен экземпляр класса-прокси, который добавляет к обычному классу следующее поведение:
    • Каждый раз при заполнении буфер во время вызова метода add() удаляемый элемент (перезаписываемый поверх) должен
      выводиться в консоль с соотвествующим сообщением.
    • Каждый раз при вызове метода poll() , когда буфер оказывается пуст, в консоль должно выводиться сообщение с
      содержимым буфера в последний момент, когда он был полон. Если такого момента еще не было, сообщение должно указывать на этот факт.
    • Кроме того, в сообщениях из предыдущих двух требований должно содержаться время (в миллисекундах или наносекундах),которое потребовалось обработку операции. 
*/
public class Main {

    public static void main(String[] args) {
        //меню
        Menu menu = new Menu("Действия с кольцевым буффером:");
        //фабрика
        FactoryRingBuffer<Integer> factory = new FactoryRingBuffer<>();
        //буфер
        RingBuffer<Integer> buf = factory.createProxyRingBuffer(menu.getNumber("Размер буффера: ", 1, 10000));

        //лямбда-функции, вызывающие методы прокси-объекта и удовлетворяющие функциональному интерфейсу RunnableMethod, для передачи в класс Меню
        RunnableMethod add = () -> buf.add(menu.getNumber("Введите добавляемый элемент: ", -10000, 10000));
        RunnableMethod poll = () -> {
            Integer result = buf.poll();
            System.out.println(result == null ? "Буффер пуст" : "Удалённый элемент: " + result);
        };
        RunnableMethod peek = () -> {
            Integer result = buf.peek();
            System.out.println(result == null ? "Буффер пуст" : "Головной элемент: " + result);
        };
        RunnableMethod print = () -> System.out.println(buf);
        RunnableMethod size = () -> System.out.println("Количество элементов: " + buf.getSize());

        //добавление пунктов меню
        menu.addMenuItem(new MenuItem(1, "Добавить элемент", add));
        menu.addMenuItem(new MenuItem(2, "Удалить элемент", poll));
        menu.addMenuItem(new MenuItem(3, "Головной элемент", peek));
        menu.addMenuItem(new MenuItem(4, "Печать буффера", print));
        menu.addMenuItem(new MenuItem(5, "Количество элементов", size));

        //запуск
        menu.run();
    }
}