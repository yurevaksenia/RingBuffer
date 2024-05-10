import org.junit.jupiter.api.Test;

import com.vsu.factory.*;
import com.vsu.ringbuffer.*;

import static org.junit.jupiter.api.Assertions.*;

public class FactoryRingBufferTest {
    @Test
    void testCreateProxyRingBuffer() {
        FactoryRingBuffer<Integer> factory = new FactoryRingBuffer<>();
        RingBuffer<Integer> buffer = factory.createProxyRingBuffer(3);

        assertNotNull(buffer);
        assertEquals(0, buffer.getSize());

        buffer.add(1);
        buffer.add(2);
        buffer.add(3);
        buffer.add(4);
        assertEquals(3, buffer.getSize());

        assertEquals(2, buffer.poll());
        assertEquals(3, buffer.poll());
        assertEquals(4, buffer.poll());
        assertNull(buffer.poll());
    }
}
