package com.jet.dsm.decorate;

/**
 * @Author: zhangkaifeng.
 * @CreateTime: 2017/7/19 11:15
 * @Description:
 */


public class DecorateTest {

    public static void main(String[] args) {
        Human human = new Man();
        Decorate decorate = new BlackShoes(human);
        decorate.gotoRoom();
        decorate = new DrinkMilk(decorate);
        decorate.gotoKitchen();
    }
}
