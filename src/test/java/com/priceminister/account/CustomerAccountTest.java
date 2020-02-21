package com.priceminister.account;

import static org.junit.Assert.*;
import org.junit.*;
import com.priceminister.account.implementation.*;

/**
 * Please create the business code, starting from the unit tests below.
 * Implement the first test, the develop the code that makes it pass.
 * Then focus on the second test, and so on.
 * 
 * We want to see how you "think code", and how you organize and structure a simple application.
 * 
 * When you are done, please zip the whole project (incl. source-code) and send it to recrutement-dev@priceminister.com
 * 
 */
public class CustomerAccountTest
{
    
    Account customerAccount;
    AccountRule rule;

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception
    {
        customerAccount = new CustomerAccount();
        rule = new CustomerAccountRule();
    }
    
    /**
     * Tests that an empty account always has a balance of 0.0, not a NULL.
     */
    @Test
    public void testAccountWithoutMoneyHasZeroBalance()
    {
        try
        {
            // We check whether the empty account cannot be null even though we apply null
            customerAccount.add(null);
            assertTrue("An empty account cannot be null", customerAccount.getBalance() != null);
            
            // We ensure the retrivied value equals 0.0
            assertTrue("An empty account must always equals 0.0", customerAccount.getBalance().equals(0.0));
        }
        catch(IllegalBalanceException e)
        {
            e.toString();
        }
    }
    
    /**
     * Adds money to the account and checks that the new balance is as expected.
     */
    @Test
    public void testAddPositiveAmount() throws IllegalBalanceException
    {
        // The money to add must only be positive therefore we set a value
        customerAccount.add(5.00);
        assertTrue("The money to add to the account must only be positive", customerAccount.getBalance() > 0.0);
        
        // We set another value via the below variable
        Double amountToAdd = 150.00;
        Double updatedBalance = amountToAdd + customerAccount.getBalance();
        
        // We check now whether the balance got its amount updated
        assertEquals(updatedBalance, customerAccount.getBalance() + amountToAdd, 0);
    }
    
    /**
     * Tests that an illegal withdrawal throws the expected exception.
     * Use the logic contained in CustomerAccountRule; feel free to refactor the existing code.
     */
    @Test
    public void testWithdrawAndReportBalanceIllegalBalance()
    {
        try
        {
            // We add money to the account
            Double moneyAdded = 800.00;
            customerAccount.add(moneyAdded);
            
            // We set a withdrawal request, an exception is handled if the balance is not enought provisioned
            Double withdrawalRequest = 500.00;
            assertTrue("The balance in the account must be higher to the withdrawal request", customerAccount.getBalance() > withdrawalRequest);
            
            customerAccount.withdrawAndReportBalance(withdrawalRequest, rule);
            
            // We check now if the actual balance and the expected one have the same amount
            assertEquals(customerAccount.getBalance() - withdrawalRequest, customerAccount.getBalance() - withdrawalRequest, 0);
            
            // We ensure now that a withdrawal which have an amount higher than the balance is impossible
            assertFalse("The withdrawal request cannot be higher than the balance", customerAccount.getBalance() < withdrawalRequest);
        }
        catch(IllegalBalanceException e)
        {
            e.toString();
        }
    }
    
    // Also implement missing unit tests for the above functionalities.
}