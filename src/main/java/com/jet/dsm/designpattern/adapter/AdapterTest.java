package com.jet.dsm.designpattern.adapter;

/**
 * @Author: zhangkaifeng.
 * @CreateTime: 2017/7/19 16:24
 * @Description:
 */


public class AdapterTest {


    public static void main(String[] args) {
        V220Power v220Power = new V220PowerImpl();

        Adapter adapter = new Adapter(v220Power);

        Mobile mobile = new Mobile(adapter);

        mobile.charge();

    }





}
