package com.wangwei.forward.factory.simple;

import com.wangwei.forward.factory.model.CarBMW;
import com.wangwei.forward.factory.model.CarBMW320;
import com.wangwei.forward.factory.model.CarBMW520;

/**
 * 简单工厂模式
 *
 * @author Administrator
 */
public class FactoryCar {

    /**
     * 由工厂创建具体对象
     *
     * @param model 具体类型
     * @return CarBMW
     */
    public CarBMW createBMW(Integer model){
        CarBMW carBMW = null;
        switch (model){
           case 320:
               carBMW = new CarBMW320();
               break;

           case 520:
               carBMW = new CarBMW520();
               break;

           default:
               break;
        }

        return carBMW;
    }

}
