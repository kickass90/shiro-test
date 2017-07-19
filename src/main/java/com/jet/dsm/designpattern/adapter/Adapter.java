package com.jet.dsm.designpattern.adapter;

/**
 * @Author: zhangkaifeng.
 * @CreateTime: 2017/7/19 16:22
 * @Description:
 */


public class Adapter implements V5Power{

    private V220Power v220Power;

    public Adapter(V220Power v220Power){
        this.v220Power = v220Power;
    }


    @Override
    public void offer5V() {
        this.v220Power.offer220v();
    }
}
