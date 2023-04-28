package com.ncr.test.pyramid.solver.impl;

import com.ncr.test.pyramid.data.Pyramid;
import com.ncr.test.pyramid.solver.PyramidSolver;

/**
 * TASK: There is something wrong here. A few things actually...
 */
public class NaivePyramidSolver implements PyramidSolver {
    /**
     * The getTotalAbove method recursively calculates
     * the total by summing the value at the current position with the maximum total from the row above,
     * either to the left or right.
     * However, it does not handle the case when the pyramid is empty.
     * To address this, you can add a check at the beginning of the pyramidMaximumTotal method to
     * return 0 if the pyramid is empty.
     */
    @Override
    public long pyramidMaximumTotal(Pyramid pyramid) {
        if (pyramid.getRows() == 0) {
            return 0;
        }
        return getTotalAbove(pyramid.getRows() - 1, 0, pyramid);
    }

    /**
     * The getTotalAbove method's base case is incorrect.
     * Currently, the method returns 0 when the row parameter equals 0.
     * However, since the rows in the pyramid are 0-indexed, the base case should be when row equals -1,
     * indicating that we have reached the top of the pyramid.
     * The corrected base case should be if (row == -1) return 0;.
     */
    private long getTotalAbove(int row, int column, Pyramid pyramid) {
        if (row == -1) {
            return 0;
        }

        int myValue = pyramid.get(row, column);
        long left = myValue + getTotalAbove(row - 1, column, pyramid);
        long right = myValue + getTotalAbove(row - 1, column + 1, pyramid);
        return Math.max(left, right);
    }
}