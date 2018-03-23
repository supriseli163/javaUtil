package com.base.java.util.sign.bean;

import lombok.Data;

import java.io.Serializable;

@Data
public class PaymentInfo implements Serializable {
    private static final long serialVersionUID = 5757199281221064960L;
    /**业务码*/
    private int bizCode;
    /**业务流水号*/
    private Long merchantNum;
    /**付款金额，单位：分*/
    private Long moncyCent;
    /**账号类型：1：对公， 2：对私*/
    private int acctType;
    /**收款银行账号*/
    private String cardNo;
    /**收款方名称*/
    private String name;
    /**收款方名称*/
    private Integer branchId;
    /**收款银行id*/
    private Integer bank;
    /**收款城市名称*/
    private Integer city;
    /**收款支行名称*/
    private String branchName;
}
