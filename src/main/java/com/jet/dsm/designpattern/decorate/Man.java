package com.jet.dsm.designpattern.decorate;

/**
 * @Author: zhangkaifeng.
 * @CreateTime: 2017/7/19 11:05
 * @Description:
 */


public class Man implements Human{
    @Override
    public void gotoRoom() {
        System.out.println("去了卧室");
    }

    @Override
    public void gotoKitchen() {
        System.out.println("去了厨房");
    }
}
