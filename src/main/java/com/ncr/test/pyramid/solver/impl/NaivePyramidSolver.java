package com.ncr.test.pyramid.solver.impl;

import com.ncr.test.pyramid.data.Pyramid;
import com.ncr.test.pyramid.solver.PyramidSolver;

/**
 * Memory Usage: The current implementation uses recursion to calculate the maximum total.
 * Recursion can be inefficient and consume a large amount of memory for large pyramids
 * **
 * **
 * Time Complexity: The current implementation has an exponential time complexity of O(2^n),
 * where n is the number of rows in the pyramid. This is because each call to getTotalAbove makes two recursive calls.
 * **
 * **
 * Performance Optimization: The current implementation is not optimized for large pyramids.
 * To handle larger inputs efficiently, an alternative approach, such as dynamic programming or iterative methods
 */
public class NaivePyramidSolver implements PyramidSolver {

    @Override
    public long pyramidMaximumTotal(Pyramid pyramid) {
        /**
         * The code does not explicitly handle invalid inputs:
         * such as a null pyramid or pyramids with inconsistent row lengths.
         * */
        if (pyramid == null || pyramid.getRows() == 0 || pyramid.getData() == null) {
            throw new IllegalArgumentException("Invalid pyramid input");
        }

        return getTotalAbove(pyramid.getRows() - 1, 0, pyramid);
    }

    private long getTotalAbove(int row, int column, Pyramid pyramid) {

        /**
         * row < 0: Checks if the row index is less than 0.
         * If it is, it means the index is negative, which is invalid.
         * row >= pyramid.getRows(): Checks if the row index is greater than
         * or equal to the number of rows in the pyramid.
         * If it is, it means the index is out of bounds,
         * as valid row indices range from 0 to pyramid.getRows() - 1.
         * column < 0: Checks if the column index is less than 0.
         * If it is, it means the index is negative, which is invalid.
         * column >= pyramid.getData()[row].length:
         * Checks if the column index is greater than or equal to the length of the row
         * in the data array corresponding to the given row index.
         * If it is, it means the index is out of bounds, as valid column indices for a given row range
         * from 0 to pyramid.getData()[row].length - 1.
         */
        if (row < 0 || row >= pyramid.getRows() || column < 0 || column >= pyramid.getData()[row].length) {
            throw new IllegalArgumentException("Invalid row or column index");
        }

        if (row == 0) {
            // Return the value at the top of the pyramid as the base case for a single-row pyramid.
            return pyramid.get(row, column);
        }

        int myValue = pyramid.get(row, column);
        long left = myValue + getTotalAbove(row - 1, column, pyramid);
        long right = myValue + getTotalAbove(row - 1, column + 1, pyramid);
        return Math.max(left, right);
    }
}
