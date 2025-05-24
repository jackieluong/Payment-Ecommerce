package com.GeekUp.Shop.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class IpnResponse {
    @JsonProperty("RspCode")
    private String responseCode;

    @JsonProperty("Message")
    private String message;
}
