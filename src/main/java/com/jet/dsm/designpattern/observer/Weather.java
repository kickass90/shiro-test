package com.jet.dsm.designpattern.observer;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: zhangkaifeng.
 * @CreateTime: 2017/7/19 14:25
 * @Description:天气数据
 */


public class Weather implements Subject{
    List<Observer> observerList = new ArrayList<>();
    private Double temp = null;
    private Double tempF = null;
    @Override
    public void register(Observer observer) {
        this.observerList.add(observer);
    }

    @Override
    public void remove(Observer observer) {
        this.observerList.remove(observer);
    }

    @Override
    public void notifyObserver() {
        observerList.stream().forEach(o -> o.update());
    }

    public void weatherUpdate(){
        notifyObserver();
    }

    public void setTemp(Double temp, Double tempF){
        this.temp = temp;
        this.tempF = tempF;
        weatherUpdate();
    }

    public Double getTemp() {
        return temp;
    }

    public Double getTempF() {
        return tempF;
    }

}
