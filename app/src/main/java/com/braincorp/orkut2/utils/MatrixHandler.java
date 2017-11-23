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


    private long[][] matrix = new long[50][50];


    /**
     * Adds a user
     */
    public void add (User user) {
        for (long[] row : matrix) {
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

    public long remove (User user){
        long aux;

        for (long[] row : matrix) {
            if(row[0] == user.getId()) {
                aux = user.getId();
                row[0] = 0;
                return aux;
            }
        }
        return 0;
    }

    public long[][] getMatrix() {
        return matrix;
    }
}
