package ca.cmpt213.asn4.bank;

/**
 * This is a class modeling a saving account inside
 * a back account.
 */
public class SavingsAccount extends BankAccount {

    private boolean isActive;

    /**
     * @return true if active, false otherwise
     */
    protected boolean getStatus() {
        return isActive;
    }

    /**
     * Constructor
     *
     * @param balance is non-negative
     * @param annualInterestRate is non-negative
     * @pre arguments are positive
     * @post account created with a opening balance and annual interest rate
     * @post account status is inactive if balance below $25.00
     */
    public SavingsAccount(double balance, double annualInterestRate) {
        super(balance, annualInterestRate);
        isActive = !(balance < 25);
    }

    /**
     * @pre account is active
     * @post balance decreased by amount requested
     * @post account status changed if balance below $25
     * @param withdrawalAmount is the amount to be taken out
     */
    @Override
    public void withdraw(double withdrawalAmount) {
        if (isActive) super.withdraw(withdrawalAmount);
        isActive = !(super.isBalanceBelow(25));
    }

    /**
     * @pre deposit is positive
     * @post deposit the amount
     * @post make account active if not active and
     * @throws IllegalArgumentException if deposit amount is negative
     * @param depositAmount is the amount being added to the account
     */
    @Override
    public void deposit(double depositAmount) {
        if (depositAmount < 0) throw new IllegalArgumentException("Deposit amount cannot be negative");
        if (!isActive && super.getBalance() + depositAmount >= 25) isActive = true;
        super.deposit(depositAmount);
    }

    /**
     * @post service charge of $1 is added to monthly charges for over 4 withdrawals
     * @post account remains active only if the balance after deducting service charges is above or equal to 25
     */
    @Override
    public void monthlyProcess() {
        if (super.getNumWithdrawals() > 4) super.setServiceCharge(getNumWithdrawals() - 4);
        super.monthlyProcess();
        isActive = !(super.getBalance() - super.getServiceCharge() < 25);
    }
}
