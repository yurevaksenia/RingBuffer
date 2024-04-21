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
}