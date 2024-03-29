import factory.FactoryRingBuffer;
import menu.Menu;
import menu.MenuItem;
import menu.RunnableMethod;
import ringbuffer.RingBuffer;

public class App {

    public static void main(String[] args) {
        //меню
        Menu menu = new Menu("Действия с кольцевым буфером");
        //фабрика
        FactoryRingBuffer<Integer> factory = new FactoryRingBuffer<>();
        //буфер
        RingBuffer<Integer> buf = factory.createProxyRingBuffer(menu.getNumber("Размер буфера: ", 1, 10000));

        //лямбда-функции, вызывающие методы прокси-объекта и удовлетворяющие функциональному интерфейсу RunnableMethod, для передачи в класс Меню
        RunnableMethod add = () -> buf.add(menu.getNumber("Введите добавляемый элемент: ", -10000, 10000));
        RunnableMethod poll = () -> {
            Integer result = buf.poll();
            System.out.println(result == null ? "Буффер пуст." : "Удалённый элемент: " + result);
        };
        RunnableMethod peek = () -> {
            Integer result = buf.peek();
            System.out.println(result == null ? "Буффер пуст." : "Головной элемент: " + result);
        };
        RunnableMethod print = () -> System.out.println(buf);
        RunnableMethod size = () -> System.out.println("Количество элементов: " + buf.getSize());

        //добавление пунктов меню
        menu.addMenuItem(new MenuItem(1, "Добавить элемент", add));
        menu.addMenuItem(new MenuItem(2, "Удалить элемент", poll));
        menu.addMenuItem(new MenuItem(3, "Вернуть головной элемент", peek));
        menu.addMenuItem(new MenuItem(4, "Печать буфера", print));
        menu.addMenuItem(new MenuItem(5, "Количество элементов", size));

        //запуск
        menu.run();
    }
}
