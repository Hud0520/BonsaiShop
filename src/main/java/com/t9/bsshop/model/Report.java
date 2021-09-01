package com.t9.bsshop.model;

import java.util.Date;


public class Report {
    private Date rpDate;
    private long sumOrder;
    private long sumTotalMoney;

    private double getVAT(){
        return sumTotalMoney*0.1;
    }

    public Report(Date rpDate, long sumOrder, long sumTotalMoney) {
        this.rpDate = rpDate;
        this.sumOrder = sumOrder;
        this.sumTotalMoney = sumTotalMoney;
    }


    public Date getRpDate() {
        return rpDate;
    }

    public void setRpDate(Date rpDate) {
        this.rpDate = rpDate;
    }

    public long getSumOrder() {
        return sumOrder;
    }

    public void setSumOrder(long sumOrder) {
        this.sumOrder = sumOrder;
    }

    public long getSumTotalMoney() {
        return sumTotalMoney;
    }

    public void setSumTotalMoney(long sumTotalMoney) {
        this.sumTotalMoney = sumTotalMoney;
    }
}
