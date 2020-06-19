package com.wangwei.basic.emumerate;

public enum EnumTest {

    Red(1, "red") {
        @Override
        EnumTest nextColor() {
            return Yellow;
        }
    },
    Yellow(2, "yellow") {
        @Override
        EnumTest nextColor() {
            return Blue;
        }
    },
    Blue(3, "blue") {
        @Override
        EnumTest nextColor() {
            return Red;
        }
    },
    ;

    int code;

    String name;

    abstract EnumTest nextColor();

    EnumTest(int code, String name) {
        this.code = code;
        this.name = name;
    }
}
