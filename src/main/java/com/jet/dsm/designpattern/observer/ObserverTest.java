package com.jet.dsm.designpattern.observer;

/**
 * @Author: zhangkaifeng.
 * @CreateTime: 2017/7/19 14:30
 * @Description:
 */


public class ObserverTest {

    public static void main(String[] args) {
        Weather weather = new Weather();
        CurrentWeatherDisplay currentWeatherDisplay = new CurrentWeatherDisplay(weather);
        FutureWeatherDisplay futureWeatherDisplay = new FutureWeatherDisplay(weather);
        weather.setTemp(20.01, 30.09);
        weather.setTemp(21.01, 31.09);
        weather.setTemp(22.01, 32.09);
    }
}
