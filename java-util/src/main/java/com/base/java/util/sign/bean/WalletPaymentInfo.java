package com.base.java.util.sign.bean;

import lombok.Data;

@Data
public class WalletPaymentInfo {

    private String bizerialNo;
    private Long iphPaymentNo;
    private int bizType;
    private Long amount;
    private Long mwalletId;
}