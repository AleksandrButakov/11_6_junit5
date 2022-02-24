package ru.anbn;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

@DisplayName("SimpleTest")
public class SimpleTest {

    @Test
    void simpleGreenTest() {
        assertTrue(3 > 2);
        //System.out.println("Hello, world!");
    }

    @Test
    void simpleRedTest() {
        assertTrue(3 < 2);
    }

    @Test
    void simpleBrokenTest() {
        throw new IllegalStateException("Broken :(");
    }

}
