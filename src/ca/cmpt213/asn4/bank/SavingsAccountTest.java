package ca.cmpt213.asn4.bank;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * This is a junit test class for testing
 * savings account.
 */
class SavingsAccountTest {
    SavingsAccount savingsAccount;

    @Test
    void testValidConstructor() {
        try {
            savingsAccount = new SavingsAccount(0, 0);
        } catch (IllegalArgumentException e) {
            fail();
        }
    }

    @Test
    void testNonValidBalanceConstructor() {
        try {
            savingsAccount = new SavingsAccount(Integer.MIN_VALUE, 0);
            fail();
        } catch (IllegalArgumentException e) {
            assertTrue(true);
        }
    }

    @Test
    void testNonValidAnnualInterestRateConstructor() {
        try {
            savingsAccount = new SavingsAccount(Integer.MAX_VALUE, Integer.MIN_VALUE);
            fail();
        } catch (IllegalArgumentException e) {
            assertTrue(true);
        }
    }

    @Test
    void testAccountActiveWithdraw() {
        savingsAccount = new SavingsAccount(25, .32);
        savingsAccount.withdraw(5);
    }

    @Test
    void testAccountInactiveWithdraw() {
        savingsAccount = new SavingsAccount(24.99, 13);
        savingsAccount.withdraw(12);
        assertFalse (savingsAccount.isBalanceBelow(24.99));
    }

    @org.junit.jupiter.api.Test
    void testWithdraw() {
        savingsAccount = new SavingsAccount(137.49, .07);
        try {
            savingsAccount.withdraw(-34);
            fail();
        } catch (IllegalArgumentException e) {
            assertTrue(true);
        }
        try {
            savingsAccount.withdraw(137.50);
            fail();
        } catch (IllegalArgumentException e) {
            assertTrue(true);
        }
        savingsAccount.withdraw(50.99);
        assertEquals(86.5, savingsAccount.getBalance(), 0.001);
        for (int i = 0; i < 3; i++) {
            savingsAccount.withdraw(7);
            assertTrue(savingsAccount.getStatus());
        }
    }

    @org.junit.jupiter.api.Test
    void testDeposit() {
        savingsAccount = new SavingsAccount(24.99, .8);
        assertFalse(savingsAccount.getStatus());
        savingsAccount.deposit(.03);
        assertTrue(savingsAccount.getStatus());
        assertEquals(25.02, savingsAccount.getBalance(), 0.001);
        try {
            savingsAccount.deposit(-92);
            fail();
        } catch (IllegalArgumentException e) {
            assertTrue(true);
        }
        assertEquals(1, savingsAccount.getNumDeposits());
    }

    @org.junit.jupiter.api.Test
    void testValidMonthlyProcess() {
        savingsAccount = new SavingsAccount(49, 0.04);
        for (int i = 0; i < 3; i++) {
            savingsAccount.withdraw(7);
        }
        savingsAccount.monthlyProcess();
        assertEquals(28.093, savingsAccount.getBalance(), 0.0033);
        assertEquals(0, savingsAccount.getServiceCharge());
        assertTrue(savingsAccount.getStatus());
    }

    @Test
    void testInvalidMonthlyProcess() {
        savingsAccount = new SavingsAccount(49, 0.04);
        for (int i = 0; i < 5; i++) {
        savingsAccount.withdraw(3);
        }
        savingsAccount.monthlyProcess();
        double expVal = (49 - 15 - 1) + ((49 - 15 - 1) * (0.04 / 12));
        assertEquals(expVal, savingsAccount.getBalance(), 0.0009);
        assertEquals(0, savingsAccount.getServiceCharge());
        assertTrue(savingsAccount.getStatus());
    }
}