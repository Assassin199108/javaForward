package com.wangwei.basic.bigdicimal;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class CalculateTest {

    public static void main(String[] args) {
        BigDecimal divide = BigDecimal.valueOf(5)
                .divide(BigDecimal.valueOf(100), 2, RoundingMode.FLOOR);

        System.out.println(divide.doubleValue());

        BigDecimal divide1 = BigDecimal.valueOf(3000)
                .divide(BigDecimal.valueOf(100), 2, RoundingMode.FLOOR);
        System.out.println(divide1.doubleValue());
    }

}
