package com.wangwei.forward.factory.simple;

import com.wangwei.forward.factory.model.CarBMW;

import static org.junit.Assert.*;

public class FactoryCarTest {

    @org.junit.Test
    public void createBMW() {
        FactoryCar factoryCar = new FactoryCar();
        CarBMW bmw = factoryCar.createBMW(520);
        System.out.println(bmw);
    }
}