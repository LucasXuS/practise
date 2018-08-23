package com.xusong.collectionmark1.test;

/**
 * Created by xusong on 2018/8/12.
 */
public class TestCount {
    int count = 18;

    int minusCount(){
        return getCount() - 1;
    }

    int getCount(){
        return count;
    }

    public void print(){
        System.out.println(getCount());
        System.out.println(minusCount());
        System.out.println(getCount());
    }
}
