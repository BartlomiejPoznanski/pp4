package pl.bpoznanski.ecommerce.payu;

public class OrderCreateResponse {
    String redirectUri;
    String orderId;
    String extOrderId;

    public Object getRedirectedUrl() {
        return redirectUri;
    }

    public Object getOrderId() {
        return orderId;
    }

    public Object getExtOrderId() {
        return extOrderId;
    }
}
