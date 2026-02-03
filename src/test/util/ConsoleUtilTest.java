package test.util;

import com.revpasswordmanager.util.ConsoleUtil;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

public class ConsoleUtilTest {

    @Test
    void testGetIntInput() {
        String input = "42\n";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        Scanner scanner = new Scanner(in);
        assertEquals(42, ConsoleUtil.getIntInput(scanner));
    }

    @Test
    void testGetIntInputInvalid() {
        String input = "abc\n";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        Scanner scanner = new Scanner(in);
        assertEquals(-1, ConsoleUtil.getIntInput(scanner));
    }

    @Test
    void testGetStringInput() {
        String input = "Hello World\n";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        Scanner scanner = new Scanner(in);
        assertEquals("Hello World", ConsoleUtil.getStringInput(scanner, "Prompt: "));
    }

    @Test
    void testGetBooleanInput() {
        String input = "y\n";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        Scanner scanner = new Scanner(in);
        assertTrue(ConsoleUtil.getBooleanInput(scanner, "Prompt: "));

        input = "n\n";
        in = new ByteArrayInputStream(input.getBytes());
        scanner = new Scanner(in);
        assertFalse(ConsoleUtil.getBooleanInput(scanner, "Prompt: "));
    }
}
