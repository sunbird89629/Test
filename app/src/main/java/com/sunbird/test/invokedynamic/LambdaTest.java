package com.sunbird.test.invokedynamic;

import java.util.function.Consumer;

/**
 * 作者：王豪
 * 日期：2021/7/10
 * 描述：<必填>
 */

class LambdaTest {
    public static void main(String[] args) {
        Consumer<String> consumer = (s) -> System.out.println(s);
        consumer.accept("Lambda demo");
    }
}
