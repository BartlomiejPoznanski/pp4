package pl.bpoznanski.ecommerce.sales.payment;

public interface PaymentGateway {
    PaymentDatails registerPayment(RegisterPaymentRequest registerPaymentRequest);
}