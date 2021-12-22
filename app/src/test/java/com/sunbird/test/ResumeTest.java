package com.sunbird.test;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者：王豪
 * 日期：2021/12/22
 * 描述：<必填>
 */

public class ResumeTest {
    @Test
    public void testAverageCard() {
        int[] target = {4, 3, 1, 7, 1};
        int w = 2;

        //不小于W   average >= W


    }

    public void check(int[] target, int w, int index) {

    }

    @Test
    public void testBuildRectangle() {
        int n = 4;
        char[][] sourceRectangle = buildSourceRectangle(n, new Point(0, 3), new Point(2, 0));
//        char[][] sourceRectangle = buildSourceRectangle(n, new Point(1, 0), new Point(1, 1));
//        char[][] sourceRectangle = buildSourceRectangle(n, new Point(1, 3), new Point(2, 3));
        logRectangle(sourceRectangle);
        buildTargetRectangle(sourceRectangle);
        logRectangle(sourceRectangle);
    }


    private char[][] buildSourceRectangle(int size, Point firstAsterisk, Point secondAsterisk) {
        if (size < 2) {
            throw new RuntimeException("size should great than 2");
        }
        String errMessageFormatter = "%s value %d should  great than 0 and less than size value %d";
        if (!checkValid(firstAsterisk.getX(), size)) {
            throw new RuntimeException(String.format(errMessageFormatter, "firstAsterisk.x", firstAsterisk.getX(), size));
        }
        if (!checkValid(firstAsterisk.getY(), size)) {
            throw new RuntimeException(String.format(errMessageFormatter, "firstAsterisk.y", firstAsterisk.getY(), size));
        }
        if (!checkValid(secondAsterisk.getX(), size)) {
            throw new RuntimeException(String.format(errMessageFormatter, "secondAsterisk.x", secondAsterisk.getX(), size));
        }
        if (!checkValid(secondAsterisk.getY(), size)) {
            throw new RuntimeException(String.format(errMessageFormatter, "secondAsterisk.y", secondAsterisk.getY(), size));
        }

//        if (firstAsterisk.getX() == secondAsterisk.getX()) {
//            throw new RuntimeException("two asterisk should not in same line");
//        }

        return buildRectangle(size, new Point[]{firstAsterisk, secondAsterisk});
    }


    private void buildTargetRectangle(char[][] sourceRectangle) {
        int size = sourceRectangle.length;
        List<Point> asteriskPoints = new ArrayList<>();
        //find asterisk points
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (sourceRectangle[i][j] == '*') {
                    asteriskPoints.add(new Point(i, j));
                }
            }
        }

        if (asteriskPoints.size() != 2) {
            throw new RuntimeException("rectangle should only have two asterisks");
        }

        Point firstPoint = asteriskPoints.get(0);
        Point secondPoint = asteriskPoints.get(1);

        if (firstPoint.getX() == secondPoint.getX()) {
            // two asterisk in same row
            int rowIndex = firstPoint.getX();

            if (rowIndex == 0) {
                rowIndex++;
            } else {
                rowIndex--;
            }
            sourceRectangle[rowIndex][firstPoint.getY()] = '*';
            sourceRectangle[rowIndex][secondPoint.getY()] = '*';
        } else if (firstPoint.getY() == secondPoint.getY()) {
            // two asterisk in same column
            int columnIndex = firstPoint.getY();

            if (columnIndex == 0) {
                columnIndex++;
            } else {
                columnIndex--;
            }
            sourceRectangle[firstPoint.getX()][columnIndex] = '*';
            sourceRectangle[secondPoint.getX()][columnIndex] = '*';
        } else {
            sourceRectangle[secondPoint.getX()][firstPoint.getY()] = '*';
            sourceRectangle[firstPoint.getX()][secondPoint.getY()] = '*';
        }
    }


    /**
     * build a rectangle with '-' and '*'
     *
     * @param size     the rectangle side size
     * @param asterisk asterisks point
     * @return a rectangle char array
     */
    private char[][] buildRectangle(int size, Point[] asterisk) {
        char[][] result = new char[size][size];

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                result[i][j] = '-';
            }
        }

        for (Point point : asterisk) {
            result[point.getX()][point.getY()] = '*';
        }

        return result;
    }


    private boolean checkValid(int value, int maxValue) {
        return value >= 0 && value <= maxValue;
    }

    private void logRectangle(char[][] rectangle) {
        StringBuilder sb = new StringBuilder();
        sb.append(">>>>>>>>>>>>>\n");
        for (int i = 0; i < rectangle.length; i++) {
            for (int j = 0; j < rectangle.length; j++) {
                sb.append(rectangle[i][j]);
            }
            sb.append('\n');
        }
        sb.append("<<<<<<<<<<<<<\n");
        System.out.println(sb.toString());
    }


    @Test
    public void testBuildArrangement() {

    }

    @Test
    public void testFindX() {

        //1,3,5,6,7
//        int[] target = {1, 3, 5, 6, 7};
//        int[] target = {6, 7, 1, 3, 5};
        int[] source = {6, 7, 8, 9, 1, 3, 5};
        int target = 3;

        int xIndex = findX(source, target);
        Assert.assertEquals(5, xIndex);
    }

    private int findX(int[] source, int target) {
        int low = 0;
        int high = source.length;

        while (low < high) {
            int midIndex = (low + high) >> 1;
            int midValue = source[midIndex];
            //should reverse binary search
            boolean shouldReverse = midValue > source[low];
            if (target < midValue) {
                if (!shouldReverse) {
                    high = midIndex;
                } else {
                    low = midIndex;
                }
            } else if (midValue == target) {
                return midIndex;
            } else {
                if (!shouldReverse) {
                    low = midIndex;
                } else {
                    high = midIndex;
                }
            }
        }
        return -1;
    }
}

class Point {
    private int x;
    private int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
