package com.umaraliev.FakePaymentProvider.dto;

import lombok.Data;

@Data
public class RequestMerchantEntityDTO {

        private String merchantId;
        private String secretKey;
        private RequestAccountEntityDTO requestAccountEntityDTO;
}
