package com.braincorp.orkut2.utils;

import com.braincorp.orkut2.model.User;

import org.junit.Test;

import static org.junit.Assert.assertEquals;


public class MatrixHandlerTest {
    @Test
    public void shouldAddNewUser(){
        User user = new User.Builder().setId(1234)
                .setUserName("Alleson")
                .setPassword("1234alleson")
                .setFullName("Alleson Albuquerque").build();
        MatrixHandler matrix = MatrixHandler.getInstance();
        matrix.add(user);
        assertEquals(matrix.getMatrix()[0][0], 1234);
    }

    @Test
    public void shouldRemoveUser() {
        User user = new User.Builder().setId(1234)
                .setUserName("Alleson")
                .setPassword("1234alleson")
                .setFullName("Alleson Albuquerque").build();
        MatrixHandler matrix = MatrixHandler.getInstance();
        matrix.add(user);
        long id = matrix.remove(user);
        assertEquals(matrix.getMatrix()[0][0], 0);
        assertEquals(id, 1234);

    }
}
