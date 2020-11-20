package ca.cmpt213.asn4.bank;

public abstract class BankAccount {
    private double balance;
    private int numDeposits;
    private int numWithdrawals;
    private double annualInterestRate;
    private double serviceCharge;

    public BankAccount(double balance, double annualInterestRate) {
        if (balance < 0) throw new IllegalArgumentException("Balance Cannot be Negative");
        if (annualInterestRate < 0) throw new IllegalArgumentException("Annual Interest Rate Cannot be Negative");
        this.balance = balance;
        this.annualInterestRate = annualInterestRate;
    }

    /**
     * Deposits money into the bank account
     * @pre depositAmount is positive
     * @post balance increase by the amount deposited
     * @post increased number of deposits per month
     * @param depositAmount is the amount being added to the account
     * @throws IllegalArgumentException if depositAmount is negative
     */
    public void deposit(double depositAmount) {
        if (depositAmount < 0) throw new IllegalArgumentException("Deposit Amount cannot be negative");
        balance += depositAmount;
        ++numDeposits;
    }

    /**
     * Withdraws money from the bank account
     * @pre
     * @param withdrawalAmount
     */
    public void withdraw(double withdrawalAmount) {
        if (withdrawalAmount < 0) throw new IllegalArgumentException("Withdrawal amount cannot be negative");
        if (balance - withdrawalAmount < 0) throw new IllegalArgumentException("Cannot withdraw more than account balance");
        balance -= withdrawalAmount;
        --numWithdrawals;
    }
}
