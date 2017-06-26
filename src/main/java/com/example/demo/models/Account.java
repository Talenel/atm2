package com.example.demo.models;

/**
 * Created by student on 6/20/17.
 */

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.*;
import java.math.BigDecimal;

@Entity
public class Account {

    @Id
    @NotNull
    private int acctNum;


    @Size(max=50)
    private String userName;
    //@Size(min=4, max=4)
    //private int pin;
    //private String userName;
    //private BigDecimal balance2;


    @DecimalMin("0.00")
    @Digits(integer=12, fraction=2)
    private BigDecimal balance;

    /*
    public Account(int  anAcct,int aPin, String aUserName, BigDecimal aBalance) {
        acctNum = anAcct;
        pin = aPin;
        userName = aUserName;
        balance2 = aBalance;

    }
    public Account(int  anAcct,BigDecimal aBalance)
    {
        acctNum = anAcct;
        balance2 = aBalance;

    }
    */
    public Account()
    {
        balance=new BigDecimal(0);
    }

    public Account(int  anAcct,BigDecimal aBalance)
    {

        acctNum = anAcct;

        balance = aBalance;

    }

    public int getAcctNum()
    {

        return acctNum;
    }
    public void setAcctNum(int aAcct)
    {
        acctNum=aAcct;
    }
    /*
    public String getUser()
    {
        return userName;
    }
    public int getPin()
    {
        return pin;
    }
    */
    public void setBalance(BigDecimal abalance)
    {
        balance=abalance;

    }
    public BigDecimal getBalance()
    {
        return balance;
    }

    public BigDecimal depositFunds(BigDecimal amt)
    {
        balance=balance.add(amt);
        return balance;
    }
    public BigDecimal withdrawFunds(BigDecimal amt)
    {
        balance=balance.subtract(amt);
        return balance;
    }


    /*public BigDecimal getBalance2()
    {
        return balance2;
    }
    public void setBalance2(BigDecimal newBal)
    {
        balance2 =newBal;
    }
*/

}
