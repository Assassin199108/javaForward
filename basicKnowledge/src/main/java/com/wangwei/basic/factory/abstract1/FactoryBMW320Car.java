package com.wangwei.basic.factory.abstract1;

import com.wangwei.basic.factory.model.*;

/**
 * 具体的宝马工厂
 * 现在特指320型号的工厂
 *
 * @author Administrator
 */
public class FactoryBMW320Car implements AbstractFactoryCar {


    @Override
    public AbstractEngine createCarEngine() {
        return new BMW320CarEngine();
    }

    @Override
    public AbstractAirCondition createAirCondition() {
        return new BMW320AirCondition();
    }

}
