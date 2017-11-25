package com.braincorp.orkut2.utils;

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

    private int[][] matrix;

    private MatrixHandler() {
        matrix = new int[50][50];

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                matrix[i][i] = i == j ? 0 : EMPTY_SLOT;
            }
        }
    }

    /**
     * Adds a user
     */
    public void add(User user) { // FIXME
        for (int[] row : matrix) {
            if (row[0] == EMPTY_SLOT) {
                row[0] = user.getId();
                break;
            }
        }
    }

    /**
     * Removes a user
     * @param user the user to be removed
     * @return the ID of the user removed
     */
    public int remove(User user) { // FIXME
        for (int row = 0; row < matrix.length; row++) {
            for (int column = 0; column < matrix.length; column++) {
                if (matrix[row][column] == user.getId()) {
                    matrix[row][column] = EMPTY_SLOT;
                }
            }
        }
        return user.getId();
    }

    /**
     * Get the matrix
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
    public void addAsFriend(User loggedUser, User targetUser) { // FIXME
        for (int i = 0; i < matrix.length; i++) {
            if (matrix[i][0] == loggedUser.getId()) {
                for (int j = 0; j < matrix.length; j++) {
                    if (matrix[i][j] == EMPTY_SLOT) {
                        matrix[i][j] = targetUser.getId();
                        matrix[j][i] = targetUser.getId();
                        return;
                    }
                }
            }
        }
    }

    /**
     * Determines whether a user is friends with another
     * @param loggedUser the user currently logged in
     * @param targetUser the user to validate friendship status
     * @return {@code true} if positive
     */
    public boolean areFriends(User loggedUser, User targetUser) { // FIXME
        for (int[] row : matrix) {
            if (row[0] == loggedUser.getId()) {
                for (int id : row) {
                    if (id == targetUser.getId())
                        return true;
                }
            }
        }
        return false;
    }

}
