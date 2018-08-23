package com.xusong.collectionmark1.test;

import java.lang.reflect.Array;

/**
 * Created by xusong on 2018/8/5.
 */
public class Test {


    static int count = 18;

    static int minusCount(){
        return getCount() - 1;
    }

    static int getCount(){
        return count;
    }

    public static void main(String[] args){
//        Integer[] values = (Integer[]) Array.newInstance(Integer.class, 3);
//        System.out.println(values.length);

        TestCount tc = new TestCount();
        tc.print();

    }
}
