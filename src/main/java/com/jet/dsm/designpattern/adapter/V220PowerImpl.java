package com.jet.dsm.designpattern.adapter;

/**
 * @Author: zhangkaifeng.
 * @CreateTime: 2017/7/19 16:21
 * @Description:
 */


public class V220PowerImpl implements V220Power{
    @Override
    public void offer220v() {
        System.out.println("提供220v电压");
    }
}
