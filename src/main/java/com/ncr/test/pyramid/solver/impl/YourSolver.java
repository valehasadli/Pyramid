package com.ncr.test.pyramid.solver.impl;

import com.ncr.test.pyramid.data.Pyramid;
import com.ncr.test.pyramid.solver.PyramidSolver;

public class YourSolver implements PyramidSolver {

    @Override
    public long pyramidMaximumTotal(Pyramid pyramid) {
        int[][] data = pyramid.getData();

        for (int i = 1; i < data.length; i++) {
            for (int j = 0; j < data[i].length - 1; j++) {
                data[i][j] += Math.max(data[i - 1][j], data[i - 1][j + 1]);
            }
        }

        return data[data.length - 1][0];
    }
}
