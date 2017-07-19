package com.jet.dsm.designpattern.observer;

/**
 * @Author: zhangkaifeng.
 * @CreateTime: 2017/7/19 14:24
 * @Description:未来天气的广告牌接口
 */


public class FutureWeatherDisplay implements Observer, DisplayElement {

    private Weather weather;
    private Double tempF;
    public FutureWeatherDisplay(Weather weather){
        this.weather = weather;
        this.weather.register(this);
    }

    @Override
    public void update() {
        this.tempF = this.weather.getTempF();
        display();
    }

    @Override
    public void display() {
        System.out.println("未来天气："+this.tempF);
    }
}
