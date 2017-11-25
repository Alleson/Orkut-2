package com.braincorp.orkut2.utils;

import com.braincorp.orkut2.database.Database;
import com.braincorp.orkut2.model.User;

public class MatrixHandler {

    private static final int EMPTY_SLOT = 0;
    private static final int OCCUPIED_SLOT = 1;

    private static MatrixHandler instance;

    /**
     * Singleton Constructor
     * @return the instance of the object
     */
    public static MatrixHandler getInstance() {
        if (instance == null)
            instance = new MatrixHandler();
        return instance;
    }

    private Database database;
    private int[][] matrix;

    private MatrixHandler() {
        database = Database.getInstance();
        matrix = new int[50][50];

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                matrix[i][i] = i == j ? 0 : EMPTY_SLOT;
            }
        }
    }

    /**
     * Removes a user
     * @param user the user to be removed
     */
    public void remove(User user) {
        int index = database.getUserIndex(user);
        for (int i = 0; i < matrix.length; i++) {
            matrix[index][i] = EMPTY_SLOT;
            matrix[i][index] = EMPTY_SLOT;
        }
    }

    /**
     * Gets the matrix
     * @return the adjacent matrix
     */
    public int[][] getMatrix() {
        return matrix;
    }

    /**
     * Adds a user as friends to another
     * @param loggedUser the user currently logged in
     * @param targetUser the user to be added
     */
    public void add(User loggedUser, User targetUser) {
        int a = database.getUserIndex(loggedUser);
        int b = database.getUserIndex(targetUser);

        matrix[a][b] = OCCUPIED_SLOT;
        matrix[b][a] = OCCUPIED_SLOT;
    }

    /**
     * Determines whether a user is friends with another
     * @param loggedUser the user currently logged in
     * @param targetUser the user to validate friendship status
     * @return {@code true} if positive
     */
    public boolean areFriends(User loggedUser, User targetUser) {
        int a = database.getUserIndex(loggedUser);
        int b = database.getUserIndex(targetUser);

        return matrix[a][b] == OCCUPIED_SLOT && matrix[b][a] == OCCUPIED_SLOT;
    }

}
