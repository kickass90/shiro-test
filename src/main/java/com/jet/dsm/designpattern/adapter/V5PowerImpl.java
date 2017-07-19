package com.jet.dsm.designpattern.adapter;

/**
 * @Author: zhangkaifeng.
 * @CreateTime: 2017/7/19 16:18
 * @Description:
 */


public class V5PowerImpl implements V5Power{
    @Override
    public void offer5V() {
        System.out.println("提供5V电压");
    }
}
