package ru.anbn;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.assertTrue;

@DisplayName("SimpleTest")
public class SimpleTest {

    @BeforeAll
    static void beforeAll() {
        System.out.println("@BeforeAll annotation!");
    }

    @BeforeEach
    void beforeEach() {
        System.out.println("@BeforeEach annotation!");
    }

    @AfterEach
    void afterEach() {
        System.out.println("@AfterEach annotation!");
    }

    @AfterAll
    static void afterAll() {
        System.out.println("@AfterAll annotation!");
    }

    @Test
    void simpleGreenTest() {
        assertTrue(3 > 2);
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
