package com.umaraliev.FakePaymentProvider.service.validation;

import com.umaraliev.FakePaymentProvider.service.validation.impl.TransactionTypeValidatorImpl;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = TransactionTypeValidatorImpl.class)
public @interface ValidTransactionType {

    String message() default "Invalid transaction type";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}

