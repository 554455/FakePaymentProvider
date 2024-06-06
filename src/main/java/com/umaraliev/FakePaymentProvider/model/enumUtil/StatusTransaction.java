package com.umaraliev.FakePaymentProvider.model.enumUtil;

import java.util.Random;

public enum StatusTransaction {
        IN_PROGRESS, APPROVED, FAILED;

    public String getRandomStatus(){
        StatusTransaction[] values = StatusTransaction.values();
        int length = values.length;
        int randIndex = new Random().nextInt(length);
        return String.valueOf(values[randIndex]);
    };
}
