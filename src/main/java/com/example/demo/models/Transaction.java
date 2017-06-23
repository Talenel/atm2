package com.example.demo.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 * Created by student on 6/20/17.
 */

@Entity
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int transID;

    @NotNull
    private int acctNum;

    @NotNull
    private String reason;

    private String action;

    @NotNull
    @DecimalMin("0.00")
    @Digits(integer=12, fraction=2)
    private BigDecimal amt;



    /*
        private BigDecimal oldBal;
        private BigDecimal amt;
        private BigDecimal newBal;


        public Transaction(int anAcctNum, BigDecimal bal, String anAction, BigDecimal anAmt, BigDecimal aNewBal,String aReason)
        {
            acctNum=anAcctNum;
            oldBal=bal;
            action=anAction;
            amt=anAmt;
            newBal=aNewBal;
            reason=aReason;



        }
    */
    public Transaction()
    {

    }
    public Transaction(String anAction)
    {
        action=anAction;
    }

    public Transaction(int anAcctNum, BigDecimal bal, String anAction, BigDecimal anAmt, BigDecimal aNewBal,String aReason)
    {
        acctNum=anAcctNum;

        action=anAction;
        amt=anAmt;
        reason=aReason;



    }


    public int getTransID() {
        return transID;
    }

    public void setTransID(int transID) {
        this.transID = transID;
    }

    public int getAcctNum() {
        return acctNum;
    }

    public void setAcctNum(int acctNum) {
        this.acctNum = acctNum;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public BigDecimal getAmt() {
        return amt;
    }

    public void setAmt(BigDecimal amt) {
        this.amt = amt;
    }

}
