package com.wangwei.forward.factory.method;

import com.wangwei.forward.factory.model.CarBMW520;

/**
 * 具体宝马工厂
 * 这里特指宝马520
 *
 * @author Administrator
 */
public class FactoryBMW520Car implements MethodFactoryCar {

    @Override
    public CarBMW520 createBMWCar() {

        return new CarBMW520();
    }

}
