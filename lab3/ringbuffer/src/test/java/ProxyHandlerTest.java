import org.junit.jupiter.api.Test;

import com.vsu.ringbuffer.RingBufferImpl;
import com.vsu.ringbuffer.proxy.ProxyHandler;

import static org.junit.jupiter.api.Assertions.*;

public class ProxyHandlerTest {
    @Test
    void testInvoke_AddMethod() throws Throwable {
        RingBufferImpl<Integer> buffer = new RingBufferImpl<>(3);
        ProxyHandler<Integer> handler = new ProxyHandler<>(buffer);
        handler.invoke(buffer, buffer.getClass().getMethod("add", Object.class), new Object[]{1});
        assertEquals(1, buffer.getSize());
    }

    @Test
    void testInvoke_PollMethod() throws Throwable {
        RingBufferImpl<Integer> buffer = new RingBufferImpl<>(3);
        buffer.add(1);
        ProxyHandler<Integer> handler = new ProxyHandler<>(buffer);
        handler.invoke(buffer, buffer.getClass().getMethod("poll"), null);
        assertEquals(0, buffer.getSize());
    }
}
