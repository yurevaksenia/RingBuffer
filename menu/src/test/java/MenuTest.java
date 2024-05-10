import org.junit.jupiter.api.Test;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Scanner;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.vsu.Menu;
import com.vsu.MenuItem;
import com.vsu.RunnableMethod;

public class MenuTest {

    @Test
    void testGetNumber_InputInRange() {
        String input = "5\n";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        Menu menu = new Menu("Test Menu");
        int number = menu.getNumber("Enter a number: ", 1, 10);
        assertEquals(5, number);
    }

    @Test
    void testRun_Exit() {
        String input = "0\n";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        Menu menu = new Menu("Test Menu");
        menu.addMenuItem(new MenuItem(1, "Test Item", () -> {}));
        menu.run();
    }

    @Test
    void testExecute() {
        MenuItem mockItem = mock(MenuItem.class);
        RunnableMethod mockMethod = mock(RunnableMethod.class);
        when(mockItem.getMethod()).thenReturn(mockMethod);

        Menu menu = new Menu("Test Menu");
        menu.addMenuItem(mockItem);
        menu.execute(1);

        verify(mockItem, times(1)).getMethod();
        verify(mockMethod, times(1)).run();
    }
}