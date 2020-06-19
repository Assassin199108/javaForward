package com.wangwei.basic.algorithm;

public class MathCal {

    private double fastPow(double x, long n) {
        if (n == 0) {
            return 1.0;
        }
        double half = fastPow(x, n / 2);
        if (n % 2 == 0) {
            return half * half;
        } else {
            return half * half * x;
        }
    }

    /**
     * 直观想法
     * 快速幂算法
     *
     * @param x
     * @param n
     * @return
     */
    public double myPow(double x, int n) {
        long N = n;
        if (N < 0) {
            x = 1 / x;
            N = -N;
        }

        return fastPow(x, N);
    }

    public static void main(String[] args) {
        MathCal mathCal = new MathCal();
        System.out.println(mathCal.myPow(0.00001, 2147483647));

    }
}
