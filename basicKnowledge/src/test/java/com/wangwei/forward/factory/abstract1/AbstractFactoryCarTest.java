package com.wangwei.forward.factory.abstract1;

import com.wangwei.forward.factory.model.AbstractAirCondition;
import com.wangwei.forward.factory.model.AbstractEngine;
import org.junit.Test;

import static org.junit.Assert.*;

public class AbstractFactoryCarTest {

    @Test
    public void createCarEngine() {
        FactoryBMW320Car factoryBMW320Car = new FactoryBMW320Car();
        AbstractEngine carEngine = factoryBMW320Car.createCarEngine();
        AbstractAirCondition airCondition = factoryBMW320Car.createAirCondition();

        System.out.println(carEngine);
        System.out.println(airCondition);
    }

    @Test
    public void createAirCondition() {
        FactoryBMW520Car factoryBMW520Car = new FactoryBMW520Car();
        AbstractAirCondition airCondition = factoryBMW520Car.createAirCondition();
        AbstractEngine carEngine = factoryBMW520Car.createCarEngine();

        System.out.println(airCondition);
        System.out.println(carEngine);
    }
}