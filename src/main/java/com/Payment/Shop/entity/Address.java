package com.Payment.Shop.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Embeddable
public class Address {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private long id;

    private String province;
    private String district;
    private String commune;
    private String address;


}
