package com.jet.dsm.decorate;

/**
 * @Author: zhangkaifeng.
 * @CreateTime: 2017/7/19 11:07
 * @Description:
 */


public class BlackShoes extends Decorate{



    public BlackShoes(Human human){
        super(human);
    }

    @Override
    public void gotoRoom() {
        super.gotoRoom();
        wearShoes();
    }

    @Override
    public void gotoKitchen() {
        super.gotoKitchen();

    }

    public void wearShoes(){
        System.out.println("穿了一双鞋子");
    }
}
