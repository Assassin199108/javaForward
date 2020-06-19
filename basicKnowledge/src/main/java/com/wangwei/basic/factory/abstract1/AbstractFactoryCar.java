package com.wangwei.basic.factory.abstract1;

import com.wangwei.basic.factory.model.AbstractAirCondition;
import com.wangwei.basic.factory.model.AbstractEngine;

/**
 * 抽象工厂模式，
 * 他下面有具体的工厂类型
 * @author Administrator
 */
public interface AbstractFactoryCar {

    /**
     * 由具体工厂生产具体零件
     *
     * @return AbstractEngine
     */
    AbstractEngine createCarEngine();

    /**
     * 由具体工厂生产具体空调
     *
     * @return AbstractAirCondition
     */
    AbstractAirCondition createAirCondition();

}
