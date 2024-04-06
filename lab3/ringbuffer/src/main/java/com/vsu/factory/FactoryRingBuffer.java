package com.vsu.factory;

import com.vsu.ringbuffer.RingBuffer;
import com.vsu.ringbuffer.RingBufferImpl;
import com.vsu.ringbuffer.proxy.ProxyHandler;

import java.lang.reflect.Proxy;

public class FactoryRingBuffer<E> {

    public RingBuffer<E> createProxyRingBuffer(int size) {
        RingBufferImpl<E> ringBuffer = new RingBufferImpl<>(size);

        RingBuffer<E> proxyRingBuffer = (RingBuffer) Proxy.newProxyInstance(
                RingBuffer.class.getClassLoader(),
                new Class[]{RingBuffer.class},
                new ProxyHandler<>(ringBuffer)
        );
        return proxyRingBuffer;
    }
}
