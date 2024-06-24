package com.umaraliev.FakePaymentProvider.dto;

public class MerchantAuthenticationDetails {
    private String merchantId;
    private String secretKey;

    public MerchantAuthenticationDetails(String merchantId, String secretKey) {
        this.merchantId = merchantId;
        this.secretKey = secretKey;
    }

    public String getMerchantId() {
        return merchantId;
    }

    public String getSecretKey() {
        return secretKey;
    }
}
