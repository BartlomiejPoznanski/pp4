package pl.bpoznanski.ecommerce.payu;

import java.util.Arrays;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class PayUTest {
    @Test
    void itAllowsToRegisterPayment() {
        PayU payu = thisIsPayU();
        OrderCreateRequest request = thereIsExampleOrderCreateRequest();

        OrderCreateResponse response = payu.handle(request);

        assertNotNull(response.getRedirectedUrl());
        assertNotNull(response.getOrderId());
        assertNotNull(response.getExtOrderId());
    }

    private OrderCreateRequest thereIsExampleOrderCreateRequest() {
        var request = new OrderCreateRequest();
        OrderCreateRequest orderCreateRequest = request
                .setNotifyUrl("https://your.eshop.com/Notify")
                .setCustomerIp("127.0.0.1")
                .setMerchantPosId("300746")
                .setDescription("ndejjnf")
                .setCurrencyCode("PLN")
                .setTotalAmount(21010)
                .setExtOrderId("ksdjndsnj")
                .setBuyer(new Buyer()
                        .setEmail("john.doe@example.com")
                        .setPhone("790550031")
                        .setFirstName("John")
                        .setLastName("Doe")
                        .setLanguage("pl")
                )
                .setProducts(Arrays.asList(
                        new Product()
                                .setName("Nice product")
                                .setUnitPrice(15500)
                                .setQuantity(1)
                ));
        return orderCreateRequest;
    }

    private PayU thisIsPayU() {
        return new PayU();
    }
}
