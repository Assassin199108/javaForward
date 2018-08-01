package com.wangwei.forward.factory.abstract1;

import com.wangwei.forward.factory.method.MethodFactoryCar;
import com.wangwei.forward.factory.model.*;

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
