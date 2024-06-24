package com.umaraliev.FakePaymentProvider.service;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Random;

@Component
public class StatusTransaction {

    private final Random rand = new Random();
    public String getRandomStatus(){
        ArrayList<String> statusTransaction = new ArrayList<>();
        statusTransaction.add("IN_PROGRESS");
        statusTransaction.add("APPROVED");
        statusTransaction.add("FAILED");


        return statusTransaction.get(rand.nextInt(statusTransaction.size()));
    };
}
