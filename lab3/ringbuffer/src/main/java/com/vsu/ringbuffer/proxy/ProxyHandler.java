package com.vsu.ringbuffer.proxy;

import com.vsu.ringbuffer.RingBufferImpl;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class ProxyHandler<E> implements InvocationHandler {

    private final RingBufferImpl<E> proxyBuffer;
    private String fullness;    //последняя наполненность буфера (пустая строка, если буфер ещё не был полон)

    public ProxyHandler(RingBufferImpl<E> proxyBuffer) {
        this.proxyBuffer = proxyBuffer;
        fullness = "";
    }

    private Object addInvoke(Method method, Object[] args) throws Throwable {
        if (!proxyBuffer.isFull()) {
            method.invoke(proxyBuffer, args);
            fullness = proxyBuffer.isFull() ? proxyBuffer.toString() : "";  //после добавления проверяем, не стал ли он заполненным
            return null;
        }
        //попадаем сюда, если буфер уже полный
        //засекаем время
        long start = System.nanoTime();

        E oldHeadElement = proxyBuffer.peek();   //получаем затираемый элемент
        method.invoke(proxyBuffer, args);        //вызываем метод add буфера
        fullness = proxyBuffer.toString();       //сохраняем последнюю наполненность буфера

        //останавливаем таймер
        long elapsed = System.nanoTime() - start;
        System.out.println("Перезаписанный элемент: " + oldHeadElement.toString());
        System.out.println("Время, затраченное на операцию [в наносекундах]: " + elapsed);
        return null;
    }

    private Object pollInvoke(Method method, Object[] args) throws Throwable {
        //засекаем время
        long start = System.nanoTime();
        Object result = method.invoke(proxyBuffer, args);   //запоминаем результат вызова метода poll, т.е. удаляемый элемент
        long elapsed = System.nanoTime() - start;

        if (proxyBuffer.isEmpty()) {
            if (fullness.equals("")) {
                System.out.println("За время работы программы буффер не заполнялся полностью.");
            }
            else {
                System.out.println("Последнее содержимое буффера: " + fullness);
            }
            System.out.println("Время, затраченное на операцию [в наносекундах]: " + elapsed);
        }
        return result;
    }


    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if (method.getName().equals("add"))
            return addInvoke(method, args);
        else if (method.getName().equals("poll"))
            return pollInvoke(method, args);
        else
            return method.invoke(proxyBuffer, args);
    }
}

