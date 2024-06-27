package com.umaraliev.FakePaymentProvider.service.validation;

import com.umaraliev.FakePaymentProvider.service.validation.impl.CheckingAvailabilityAccountImpl;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = CheckingAvailabilityAccountImpl.class)
public @interface ValidCheckingAvailabilityAccount {
    String message() default "Account ID does not exist";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
