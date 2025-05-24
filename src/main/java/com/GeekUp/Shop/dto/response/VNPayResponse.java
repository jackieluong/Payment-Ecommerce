package com.GeekUp.Shop.dto.response;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class VNPayResponse extends BasePaymentResponse{

    private String vnpUrl;

}
