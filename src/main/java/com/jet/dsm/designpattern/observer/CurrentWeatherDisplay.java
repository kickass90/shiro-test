package com.jet.dsm.designpattern.observer;

/**
 * @Author: zhangkaifeng.
 * @CreateTime: 2017/7/19 14:24
 * @Description:当前天气的广告牌接口
 */


public class CurrentWeatherDisplay implements Observer, DisplayElement {

    private Weather weather;
    private Double temp;
    public CurrentWeatherDisplay(Weather weather){
        this.weather = weather;
        this.weather.register(this);
    }


    @Override
    public void update() {
        this.temp = weather.getTemp();
        display();
    }

    @Override
    public void display() {
        System.out.println("当前天气："+temp);
    }
}
