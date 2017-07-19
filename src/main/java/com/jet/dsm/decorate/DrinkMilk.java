package com.jet.dsm.decorate;

/**
 * @Author: zhangkaifeng.
 * @CreateTime: 2017/7/19 11:07
 * @Description:
 */


public class DrinkMilk extends Decorate{

    public DrinkMilk(Human human){
        super(human);
    }

    @Override
    public void gotoRoom() {
        super.gotoRoom();
    }

    @Override
    public void gotoKitchen() {
        super.gotoKitchen();
        drinkMilk();
    }

    public void drinkMilk(){
        System.out.println("喝了一杯牛奶");
    }
}
