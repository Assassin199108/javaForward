package com.wangwei.basic.factory.simple;

import com.wangwei.basic.factory.model.CarBMW;

public class FactoryCarTest {

    @org.junit.Test
    public void createBMW() {
        FactoryCar factoryCar = new FactoryCar();
        CarBMW bmw = factoryCar.createBMW(520);
        System.out.println(bmw);
    }
}