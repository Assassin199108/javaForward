package com.wangwei.basic.factory.method;

import com.wangwei.basic.factory.model.CarBMW320;
import com.wangwei.basic.factory.model.CarBMW520;
import org.junit.Test;

public class MethodFactoryCarTest {

    @Test
    public void createBMWCar() {
        FactoryBMW320Car factoryBMW320Car = new FactoryBMW320Car();
        CarBMW320 bmwCar = factoryBMW320Car.createBMWCar();
        System.out.println(bmwCar);

        FactoryBMW520Car factoryBMW520Car = new FactoryBMW520Car();
        CarBMW520 bmwCar1 = factoryBMW520Car.createBMWCar();
        System.out.println(bmwCar1);
    }


}