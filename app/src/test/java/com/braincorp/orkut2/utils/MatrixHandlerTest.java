package com.braincorp.orkut2.utils;

import com.braincorp.orkut2.model.User;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;


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
        int id = matrix.remove(user);
        assertEquals(matrix.getMatrix()[0][0], 0);
        assertEquals(id, 1234);

    }

    @Test
    public void shouldNotBeFriends() {
        User a = new User.Builder().setId(1234)
                .setUserName("alan")
                .setPassword("alan123")
                .setFullName("Alan Camargo").build();

        User b = new User.Builder().setId(4321)
                .setUserName("test")
                .setPassword("test123")
                .setFullName("Test User").build();

        MatrixHandler matrix = MatrixHandler.getInstance();
        matrix.add(a);
        matrix.add(b);

        assertFalse(matrix.areFriends(a, b));
    }

}
