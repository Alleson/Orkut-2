package com.braincorp.orkut2.utils;

import com.braincorp.orkut2.database.Database;
import com.braincorp.orkut2.model.User;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class MatrixHandlerTest {

    private Database database;
    private MatrixHandler matrixHandler;
    private User userA;
    private User userB;

    @Before
    public void setup() {
        database = Database.getInstance();
        matrixHandler = MatrixHandler.getInstance();

        userA = new User.Builder().setUserName("Alleson")
                .setPassword("1234alleson")
                .setFullName("Alleson Albuquerque").build();

        userB = new User.Builder().setUserName("alan")
                .setPassword("1234alan")
                .setFullName("Alan Camargo").build();

        database.insert(userA);
        database.insert(userB);
    }

    @Test
    public void shouldAddUsersAsFriends() {
        matrixHandler.add(userA, userB);

        int userAIndex = database.getUserIndex(userA);
        int userBIndex = database.getUserIndex(userB);

        int[][] matrix = matrixHandler.getMatrix();
        assertEquals(matrix[userAIndex][userBIndex], 1);
        assertEquals(matrix[userBIndex][userAIndex], 1);
    }

    @Test
    @SuppressWarnings("ForLoopReplaceableByForEach")
    public void shouldRemoveUser() {
        matrixHandler.add(userA, userB);
        matrixHandler.remove(userA);

        int[][] matrix = matrixHandler.getMatrix();
        int userAIndex = database.getUserIndex(userA);

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                assertEquals(matrix[userAIndex][j], 0);
                assertEquals(matrix[i][userAIndex], 0);
            }
        }
    }

    @Test
    public void shouldDisplayUsersAsFriends() {
        matrixHandler.add(userA, userB);
        assertTrue(matrixHandler.areFriends(userA, userB));
    }

    @Test
    public void shouldNotDisplayUsersAsFriends() {
        assertFalse(matrixHandler.areFriends(userA, userB));
    }

}
