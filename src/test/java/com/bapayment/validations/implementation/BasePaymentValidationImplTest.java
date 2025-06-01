package com.bapayment.validations.implementation;

import com.bapayment.api.BasePaymentApi;
import com.bapayment.api.Type1PaymentApi;
import com.bapayment.enums.CurrencyEnum;
import com.bapayment.enums.PaymentTypesEnum;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BasePaymentValidationImplTest {

    private BasePaymentValidationImpl basePaymentValidation;

    @BeforeEach
    void setUp() {
        basePaymentValidation = new BasePaymentValidationImpl();
    }

    @Test
    void pass_validData(){
        BasePaymentApi api = new Type1PaymentApi();
        api.setAmount(100.0);
        api.setCurrency(CurrencyEnum.EUR);
        api.setDebtor_iban("LT123456789");
        api.setCreditor_iban("LT987654321");
        api.setType(PaymentTypesEnum.TYPE1);

        assertDoesNotThrow(() -> basePaymentValidation.validateBase(api));
    }

    @Test
    void doesNotPass_validInvalidAmmount(){
        BasePaymentApi api = new Type1PaymentApi();
        api.setAmount(-100.0);
        api.setCurrency(CurrencyEnum.EUR);
        api.setDebtor_iban("LT123456789");
        api.setCreditor_iban("LT987654321");
        api.setType(PaymentTypesEnum.TYPE1);

        assertThrows(IllegalArgumentException.class, () -> basePaymentValidation.validateBase(api));
    }

    @Test
    void doesNotPass_validInvalidCurrency(){
        BasePaymentApi api = new Type1PaymentApi();
        api.setAmount(100.0);
        api.setCurrency(null);
        api.setDebtor_iban("LT123456789");
        api.setCreditor_iban("LT987654321");
        api.setType(PaymentTypesEnum.TYPE1);

        assertThrows(IllegalArgumentException.class, () -> basePaymentValidation.validateBase(api));
    }

    @Test
    void doesNotPass_validInvalidDebtorIban(){
        BasePaymentApi api = new Type1PaymentApi();
        api.setAmount(100.0);
        api.setCurrency(CurrencyEnum.EUR);
        api.setDebtor_iban(null);
        api.setCreditor_iban("LT987654321");
        api.setType(PaymentTypesEnum.TYPE1);

        assertThrows(IllegalArgumentException.class, () -> basePaymentValidation.validateBase(api));
    }

    @Test
    void doesNotPass_validInvalidCreditorIban(){
        BasePaymentApi api = new Type1PaymentApi();
        api.setAmount(100.0);
        api.setCurrency(CurrencyEnum.EUR);
        api.setDebtor_iban("LT123456789");
        api.setCreditor_iban(null);
        api.setType(PaymentTypesEnum.TYPE1);

        assertThrows(IllegalArgumentException.class, () -> basePaymentValidation.validateBase(api));
    }

    @Test
    void doesNotPass_validInvalidType(){
        BasePaymentApi api = new Type1PaymentApi();
        api.setAmount(100.0);
        api.setCurrency(CurrencyEnum.EUR);
        api.setDebtor_iban("LT123456789");
        api.setCreditor_iban("LT987654321");
        api.setType(null);

        assertThrows(IllegalArgumentException.class, () -> basePaymentValidation.validateBase(api));
    }
}