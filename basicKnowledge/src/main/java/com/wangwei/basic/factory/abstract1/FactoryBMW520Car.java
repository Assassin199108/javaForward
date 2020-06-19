package com.wangwei.basic.factory.abstract1;

import com.wangwei.basic.factory.model.*;

/**
 * 具体宝马工厂
 * 这里特指宝马520
 *
 * @author Administrator
 */
public class FactoryBMW520Car implements AbstractFactoryCar {


    @Override
    public AbstractEngine createCarEngine() {
        return new BMW520CarEngine();
    }

    @Override
    public AbstractAirCondition createAirCondition() {
        return new BMW520AirCondition();
    }
}
