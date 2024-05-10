import org.junit.jupiter.api.Test;

import com.vsu.ringbuffer.RingBufferImpl;

import static org.junit.jupiter.api.Assertions.*;

public class RingBufferImpTest {    

    @Test
    void testAddAndPoll() {
        RingBufferImpl<Integer> buffer = new RingBufferImpl<>(3);
        buffer.add(1);
        buffer.add(2);
        buffer.add(3);
        assertEquals(1, buffer.poll());
        assertEquals(2, buffer.poll());
        assertEquals(3, buffer.poll());
        assertNull(buffer.poll());
    }

    @Test
    void testPeek() {
        RingBufferImpl<Integer> buffer = new RingBufferImpl<>(3);
        assertNull(buffer.peek());
        buffer.add(1);
        assertEquals(1, buffer.peek());
        buffer.add(2);
        assertEquals(1, buffer.peek());
    }

    @Test
    void testIsFullAndIsEmpty() {
        RingBufferImpl<Integer> buffer = new RingBufferImpl<>(2);
        assertTrue(buffer.isEmpty());
        assertFalse(buffer.isFull());
        buffer.add(1);
        assertFalse(buffer.isEmpty());
        assertFalse(buffer.isFull());
        buffer.add(2);
        assertFalse(buffer.isEmpty());
        assertTrue(buffer.isFull());
    }

    @Test
    void testGetSize() {
        RingBufferImpl<Integer> buffer = new RingBufferImpl<>(3);
        assertEquals(0, buffer.getSize());
        buffer.add(1);
        assertEquals(1, buffer.getSize());
        buffer.add(2);
        assertEquals(2, buffer.getSize());
        buffer.poll();
        assertEquals(1, buffer.getSize());
    }

    @Test
    void testToString() {
        RingBufferImpl<Integer> buffer = new RingBufferImpl<>(3);
        assertEquals("Буффер пуст", buffer.toString());
        buffer.add(1);
        buffer.add(2);
        buffer.add(3);
        assertEquals("1 2 3", buffer.toString());
    }
}
