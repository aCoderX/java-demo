package com.acoderx.demo.commons.algorithm;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by xudi on 2018/7/12.
 */
public class RedPackage {
    public static void main(String[] args){
        MoneyPackage moneyPackage = new MoneyPackage(10,0.11);
        List<Double> moneys = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            double d = getRandomMoney(moneyPackage);
            moneys.add(d);
            System.out.println(d);
        }

        System.out.println(moneys.stream().mapToDouble(r->r).sum());
    }

    public static double getRandomMoney(MoneyPackage _leftMoneyPackage) {
        // remainSize 剩余的红包数量
        // remainMoney 剩余的钱
        if (_leftMoneyPackage.remainSize == 1) {
            _leftMoneyPackage.remainSize--;
            return (double) Math.round(_leftMoneyPackage.remainMoney * 100) / 100;
        }
        Random r     = new Random();
        double min   = 0.01; //
        double max   = _leftMoneyPackage.remainMoney / _leftMoneyPackage.remainSize * 2;
        double money = r.nextDouble() * max;
        money = money < min ? 0.01: money;
        money = Math.floor(money * 100) / 100;
        _leftMoneyPackage.remainSize--;
        _leftMoneyPackage.remainMoney -= money;
        return money;
    }

    static class MoneyPackage{
        int remainSize;
        double remainMoney;

        public MoneyPackage(int remainSize, double remainMoney) {
            this.remainSize = remainSize;
            this.remainMoney = remainMoney;
        }
    }
}
