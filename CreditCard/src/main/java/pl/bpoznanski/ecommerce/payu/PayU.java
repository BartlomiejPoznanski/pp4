package pl.bpoznanski.ecommerce.payu;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class PayU {
    RestTemplate http;
    PayUCredentials credentials;


    public PayU(){
        this.http=http;
    }

    public OrderCreateResponse handle(OrderCreateRequest request) {
        var url = "https://secure.snd.payu.com/api/v2_1/orders";
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type","application/json");
        headers.add("Authorization", String.format("Bearer %s", getToken()));
        HttpEntity<OrderCreateRequest> headerAwareRequest = new HttpEntity<>(request,headers);
        ResponseEntity<OrderCreateResponse> response = http.postForEntity(url, request, OrderCreateResponse.class);

        return response.getBody();
    }

    private Object getToken() {
        String body = String.format(
                "grant_type=client_credentials&client_id=%s&client_secret=%s",
                "300746",
                "2ee86a66e5d97e3fadc400c9f19b065d"
        );
        var url = "https://secure.snd.payu.com/pl/standard/user/oauth/authorize";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        HttpEntity<String> request =new HttpEntity<>(body,headers);
        ResponseEntity<AuthorizationResponse> response =http.postForEntity(
                url,
                request,
                AuthorizationResponse.class);


        return response.getBody().getAccesToken();
    }
    private String getUrl(String path) {
        return String.format("%s%s", credentials.getBaseUrl(), path);
    }
}
