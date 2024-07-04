package pl.bpoznanski.ecommerce.sales.payment;

import pl.bpoznanski.ecommerce.payu.OrderCreateRequest;

public  interface PaymentGateway {

    PaymentDetails registerPayment(RegisterPaymentRequest registerPaymentRequest);

    PaymentDetails registerPayment(OrderCreateRequest registerPaymentRequest);
}