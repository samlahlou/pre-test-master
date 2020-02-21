package com.priceminister.account.implementation;

import com.priceminister.account.*;

public class CustomerAccount implements Account
{
    
    private static Double accountBalance;
    private final static Double emptyAccount = 0.0;
    
    CustomerAccount customerAccount = this;

    public void add(Double addedAmount) throws IllegalBalanceException
    {
        if(addedAmount == null)
        {
            throw new IllegalBalanceException(addedAmount);
        }
        else
        {
            Double balanceVariation = customerAccount.getBalance();
            this.accountBalance = balanceVariation + addedAmount;
        }
    }

    public Double getBalance()
    {
        return this.accountBalance == null ? this.accountBalance = CustomerAccount.emptyAccount : this.accountBalance;
    }

    public Double withdrawAndReportBalance(Double withdrawnAmount, AccountRule rule) throws IllegalBalanceException
    {
        if(rule.withdrawPermitted(withdrawnAmount))
        {
            return customerAccount.getBalance() - withdrawnAmount;
        }
        else
        {
            throw new IllegalBalanceException(withdrawnAmount);
        }
    }
    
}