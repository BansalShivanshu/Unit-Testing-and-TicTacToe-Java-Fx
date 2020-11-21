package ca.cmpt213.asn4.bank;

/**
 * This is an abstract class modeling a Bank Account
 * Most methods are public to allow anyone to access
 * Getters and setters are made protected to only
 * allow access to a subclass or class in the same package
 */
public abstract class BankAccount {
    private double balance;
    private int numDeposits;
    private int numWithdrawals;
    private double annualInterestRate;
    private double serviceCharge;

    /**
     * Constructor
     * @pre arguments are positive
     * @pre interest rate is (x% / 100) not (x %)
     * @param balance is non-negative
     * @param annualInterestRate is non-negative
     * @post account created with a opening balance and annual interest rate
     * @throws IllegalArgumentException if arguments are negative
     */
    public BankAccount(double balance, double annualInterestRate) {
        if (balance < 0) throw new IllegalArgumentException("Balance Cannot be Negative");
        if (annualInterestRate < 0) throw new IllegalArgumentException("Annual Interest Rate Cannot be Negative");
        this.balance = balance;
        this.annualInterestRate = annualInterestRate;
    }

    /**
     * Checks if the balance amount is less or more than the specified amount
     * @pre amount is non-negative
     * @param amount is non-negative
     * @return true if balance is below the amount, false if balance is more than amount
     */
    protected boolean isBalanceBelow(double amount) {
        return balance < amount;
    }

    /**
     * @return balance of the account
     */
    protected double getBalance() {
        return balance;
    }

    /**
     * Returns the number of withdrawals made in a month
     * @return number of withdrawals made in a month
     */
    protected double getNumWithdrawals() {
        return numWithdrawals;
    }

    /**
     * @return number of deposits made in the month
     */
    protected int getNumDeposits() {
        return numDeposits;
    }

    /**
     * @return service charges incurred on the account
     */
    protected double getServiceCharge() {
        return serviceCharge;
    }

    /**
     * Updates the service charge to the amount specified
     * @pre amount in non-negative
     * @throws IllegalArgumentException if amount is negative
     * @param amount is always positive
     */
    protected void setServiceCharge(double amount) {
        if (amount < 0) throw new IllegalArgumentException("Amount cannot be negative");
        serviceCharge = amount;
    }

    /**
     * Deposits money into the bank account
     * @pre depositAmount is positive
     * @post balance increases by the amount deposited
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
     * @pre withdrawalAmount is positive
     * @pre withdrawal amount is greater than equal to account balance
     * @post balance decreases by the amount withdrawn
     * @post increases number of withdrawals made
     * @param withdrawalAmount is the amount to be taken out
     * @throws IllegalArgumentException if withdrawalAmount is negative
     * @throws IllegalArgumentException if amount requested is more than current balance
     */
    public void withdraw(double withdrawalAmount) {
        if (withdrawalAmount < 0) throw new IllegalArgumentException("Withdrawal amount cannot be negative");
        if (balance - withdrawalAmount < 0) throw new IllegalArgumentException("Cannot withdraw more than account balance");
        balance -= withdrawalAmount;
        numWithdrawals++;
    }

    /**
     * Updates the balance by the calculating the monthly interest earned by the account
     * @post balance increased by the amount of interest for that month
     */
    public void calcInterest() {
        double monthlyInterestRate = annualInterestRate / 12;
        double monthlyInterest = balance * monthlyInterestRate;
        balance += monthlyInterest;
    }

    /**
     * @post balance reduced by monthly charges incurred
     * @post balance affected by interest incurred
     * @post monthly charges, number of deposits and withdrawals are reset to 0
     */
    public void monthlyProcess() {
        // balance can be negative if the service charges are more than balance
        balance -= serviceCharge;
        calcInterest();
        serviceCharge = numDeposits = numWithdrawals = 0;
    }
}
