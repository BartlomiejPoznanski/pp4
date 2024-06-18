package pl.bpoznanski.ecommerce.infrastructure;

import pl.bpoznanski.ecommerce.sales.payment.PaymentDatails;
import pl.bpoznanski.ecommerce.sales.payment.PaymentGateway;
import pl.bpoznanski.ecommerce.sales.payment.RegisterPaymentRequest;

public class PayUPaymentGw implements PaymentGateway {
    @Override
    public PaymentDatails registerPayment(RegisterPaymentRequest registerPaymentRequest) {
        return null;
    }
}