package com.umaraliev.FakePaymentProvider.model;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Random;

@Component
public class StatusTransaction {

    private  String inProgress = "IN_PROGRESS";
    private  String approved = "APPROVED";
    private  String Failed = "FAILED";



    public String getRandomStatus(){
        ArrayList<String> statusTransaction = new ArrayList<>();
        statusTransaction.add("IN_PROGRESS");
        statusTransaction.add("APPROVED");
        statusTransaction.add("FAILED");

        Random rand = new Random();
        return statusTransaction.get(rand.nextInt(statusTransaction.size()));
    };
}
