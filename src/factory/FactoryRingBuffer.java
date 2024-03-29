package factory;

import ringbuffer.RingBuffer;
import ringbuffer.RingBufferImpl;
import ringbuffer.proxy.ProxyHandler;

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
