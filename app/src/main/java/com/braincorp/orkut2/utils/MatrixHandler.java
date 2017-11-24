package com.braincorp.orkut2.utils;

import com.braincorp.orkut2.model.User;



public class MatrixHandler {

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


    private MatrixHandler() {

    }


    private int[][] matrix = new int[50][50];


    /**
     * Adds a user
     */
    public void add (User user) {
        for (int[] row : matrix) {
            if(row[0] == 0) {
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
    public int remove (User user){
        for (int row = 0; row < matrix.length; row++) {
            for (int column = 0; column < matrix.length; column++) {
                if(matrix[row][column] == user.getId()) {
                    matrix[row][column] = 0;
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




}
