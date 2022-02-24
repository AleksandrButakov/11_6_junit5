package ru.anbn;

import org.junit.jupiter.api.*;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class JunitCore {

    public static void main(String[] args) throws Exception {
        // lookup classes with annotation @Test
        // here we go with class SimpleTest.class

        // достали информацию о классе
        Class cl = SimpleTest.class;
        // получили информацию по всем методам в классе и двигаемся по ним в цикле

        // @Test
        for (Method method : cl.getDeclaredMethods()) {
            // получили аннотацию @Test если она там есть
            Test methodAnnotation = method.getAnnotation(Test.class);
            if (methodAnnotation != null) {
                // run methods with @Test
                try {
                    method.invoke(cl.getConstructor().newInstance());
                } catch (InvocationTargetException e) {
                    if (e.getCause() instanceof AssertionError) {
                        System.out.println("Test failed: " + method.getName());
                        continue;
                    } else {
                        System.out.println("Test broken: " + method.getName());
                        continue;
                    }
                }
                System.out.println("Test passed: " + method.getName());
            }
        }
        System.out.println();

        // @BeforeAll
        for (Method method : cl.getDeclaredMethods()) {
            // получили аннотацию @Test если она там есть
            BeforeAll methodAnnotationBeforeAll = method.getAnnotation((BeforeAll.class));
            //Test methodAnnotation = method.getAnnotation(Test.class);
            if (methodAnnotationBeforeAll != null) {
                // run methods with @BeforeAll
                method.invoke(cl.getConstructor().newInstance());
                System.out.println("Annotation @BeforeAll is completed: " + method.getName());
            }
        }
        System.out.println();

        // @BeforeEach
        for (Method method : cl.getDeclaredMethods()) {
            // получили аннотацию @Test если она там есть
            BeforeEach methodAnnotationBeforeEach = method.getAnnotation((BeforeEach.class));
            //Test methodAnnotation = method.getAnnotation(Test.class);
            if (methodAnnotationBeforeEach != null) {
                // run methods with @BeforeAll
                method.invoke(cl.getConstructor().newInstance());
                System.out.println("Annotation @BeforeEach is completed: " + method.getName());
            }
        }
        System.out.println();

        // @AfterEach
        for (Method method : cl.getDeclaredMethods()) {
            // получили аннотацию @Test если она там есть
            AfterEach methodAnnotationAfterEach = method.getAnnotation((AfterEach.class));
            //Test methodAnnotation = method.getAnnotation(Test.class);
            if (methodAnnotationAfterEach != null) {
                // run methods with @BeforeAll
                method.invoke(cl.getConstructor().newInstance());
                System.out.println("Annotation @AfterEach is completed: " + method.getName());
            }
        }
        System.out.println();

        // @AfterAll
        for (Method method : cl.getDeclaredMethods()) {
            // получили аннотацию @Test если она там есть
            AfterAll methodAnnotationAfterAll = method.getAnnotation((AfterAll.class));
            //Test methodAnnotation = method.getAnnotation(Test.class);
            if (methodAnnotationAfterAll != null) {
                // run methods with @BeforeAll
                method.invoke(cl.getConstructor().newInstance());
                System.out.println("Annotation @AfterAll is completed: " + method.getName());
            }
        }
        System.out.println();

    }
}
