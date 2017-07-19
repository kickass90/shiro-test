package com.jet.dsm.designpattern.decorate;

/**
 * @Author: zhangkaifeng.
 * @CreateTime: 2017/7/19 11:06
 * @Description:
 */


public class Decorate implements Human{

    private Human human;

    public Decorate(Human human) {
        this.human = human;
    }

    @Override
    public void gotoRoom() {
        human.gotoRoom();
    }

    @Override
    public void gotoKitchen() {
        human.gotoKitchen();
    }
}
