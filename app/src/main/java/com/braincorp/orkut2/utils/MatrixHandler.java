package com.braincorp.orkut2.utils;

import com.braincorp.orkut2.model.User;

/**
 * Created by Alleson on 23/11/2017.
 */

public class MatrixHandler {

    private static MatrixHandler instance;

    public static MatrixHandler getInstance() {
        if (instance == null)
            instance = new MatrixHandler();
        return instance;
    }

    private MatrixHandler() {

    }

    long[][] matrix = new long[50][50];

    public void add (User user) {
        for (long[] row : matrix) {
            if(row[0] == 0) {
                row[0] = user.getId();
                break;
            }


        }
    }

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
