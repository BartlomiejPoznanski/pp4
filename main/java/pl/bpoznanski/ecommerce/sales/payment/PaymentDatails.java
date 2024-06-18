package pl.bpoznanski.ecommerce.sales.payment;

public class PaymentDatails {
    private final String url;

    public PaymentDatails(String url) {

        this.url = url;
    }

    public String getPaymentUrl() {
        return url;
    }
}