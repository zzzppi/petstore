package com.petstore.mockito;

public interface Visible {
    default void print() {
        System.out.println("print hello world");
    }
}
