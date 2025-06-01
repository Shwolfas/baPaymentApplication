package com.bapayment.enums;

public enum PaymentTypesEnum {

    TYPE1(0.1),
    TYPE2(0.2),
    TYPE3(0.3);

    private final double cancellationCoef;

    PaymentTypesEnum(double cancellationCoef) {
        this.cancellationCoef = cancellationCoef;
    }

    public double getCancellationCoef() {
        return cancellationCoef;
    }
}
