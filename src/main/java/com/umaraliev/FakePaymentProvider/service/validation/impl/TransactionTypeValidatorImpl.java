package com.umaraliev.FakePaymentProvider.service.validation.impl;

import com.umaraliev.FakePaymentProvider.model.enums.TransactionType;
import com.umaraliev.FakePaymentProvider.service.validation.ValidTransactionType;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class TransactionTypeValidatorImpl implements ConstraintValidator<ValidTransactionType, TransactionType> {


    @Override
    public void initialize(ValidTransactionType constraintAnnotation) {}

    @Override
    public boolean isValid(TransactionType value, ConstraintValidatorContext context) {
        if (value == null) {
            return false;
        }
        switch (value) {
            case TO_UP:
            case PAY_UOT:
                return true;
            default:
                return false;
        }
    }
}