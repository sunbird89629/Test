package com.sunbird.test;

import org.junit.Assert;
import org.junit.Test;

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
    public void testFindX() {

        //1,3,5,6,7
//        int[] target = {1, 3, 5, 6, 7};
//        int[] target = {6, 7, 1, 3, 5};
        int[] source = {6, 7, 8, 9, 1, 3, 5};
        int target = 3;

        int xIndex = findX(source, target);
        Assert.assertEquals(5, xIndex);
    }

    public int findX(int[] source, int target) {
        int low = 0;
        int high = source.length;

        while (low < high) {
            int midIndex = (low + high) >> 1;
            int midValue = source[midIndex];
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
