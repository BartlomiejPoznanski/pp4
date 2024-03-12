package pl.bpoznanski.CreditCard;

import org.junit.jupiter.api.Test;
import pl.bpoznanski.CreditCard.CreditBelowThresholdException;
import pl.bpoznanski.CreditCard.CreditCard;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;

public class CreditCardTest {
    @Test
    void itAllowsToAssignCredit() {
        CreditCard card = new CreditCard();

        card.assignCreditLimit(BigDecimal.valueOf(1000));

        assert BigDecimal.valueOf(1000).equals(card.getBalance());
    }

    @Test
    void itAllowsToAssignCredit2() {
        CreditCard card = new CreditCard();

        card.assignCreditLimit(BigDecimal.valueOf(1200));

        assert BigDecimal.valueOf(1200).equals(card.getBalance());
    }

    @Test
    void itDenyCreditBelowThreshold() {
        CreditCard card = new CreditCard();

        try {
            card.assignCreditLimit(BigDecimal.valueOf(50));
            fail("Should throw exception");
        } catch (CreditBelowThresholdException e) {
            assertTrue(true);
        }
    }


}