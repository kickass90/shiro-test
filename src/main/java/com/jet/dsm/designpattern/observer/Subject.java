package com.jet.dsm.designpattern.observer;


/**
 * @Author: zhangkaifeng.
 * @CreateTime: 2017/7/19 14:19
 * @Description:主题接口
 */


public interface Subject {

    void register(Observer observer);

    void remove(Observer observer);

    void notifyObserver();

}
