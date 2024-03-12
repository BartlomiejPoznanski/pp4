package pl.bpoznanski.CreditCard;

import java.math.BigDecimal;

public class CreditCard {
    private BigDecimal creditLimit;

    public void assignCreditLimit(BigDecimal creditLimit) {
        this.creditLimit = creditLimit;
    }

    public BigDecimal getBalance() {
        return creditLimit;
    }
}