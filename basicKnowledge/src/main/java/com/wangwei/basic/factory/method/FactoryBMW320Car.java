package com.wangwei.basic.factory.method;

import com.wangwei.basic.factory.model.CarBMW320;

/**
 * 具体的宝马工厂
 * 现在特指320型号的工厂
 *
 * @author Administrator
 */
public class FactoryBMW320Car implements MethodFactoryCar {

    @Override
    public CarBMW320 createBMWCar() {

        return new CarBMW320();
    }

}
