package ru.anbn;

import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class JunitCore {

    public static void main(String[] args) throws Exception {
        // lookup classes with annotation @Test
        // here we go with class SimpleTest.class

        // достали информацию о классе
        Class cl = SimpleTest.class;
        // получили информацию по всем методам в классе и двигаемся по ним в цикле
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

    }

}
