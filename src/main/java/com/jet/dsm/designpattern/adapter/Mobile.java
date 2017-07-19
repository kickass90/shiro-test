package com.jet.dsm.designpattern.adapter;

/**
 * @Author: zhangkaifeng.
 * @CreateTime: 2017/7/19 16:11
 * @Description:
 */


public class Mobile {

    private V5Power v5Power;

    public Mobile(V5Power v5Power) {
        this.v5Power = v5Power;
    }

    public void charge(){
        System.out.println("充电要求");
        v5Power.offer5V();
    }
}
