package pl.bpoznanski.ecommerce.sales.reservation;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pl.bpoznanski.ecommerce.sales.SalesFacade;
import pl.bpoznanski.ecommerce.sales.cart.CartStorage;
import pl.bpoznanski.ecommerce.sales.offering.OfferCalculator;


import static org.assertj.core.api.Assertions.*;

import java.math.BigDecimal;
import java.util.Optional;

public class OfferAcceptanceTest {
    private SpyPaymentGateway spyPaymentGateway;
    private ReservationRepository reservationRepository;

    @BeforeEach
    void setUp(){
        spyPaymentGateway = new SpyPaymentGateway();
        reservationRepository = new ReservationRepository();
    }

    @Test
    void itAllowsToAcceptAnOffer() {
        SalesFacade sales = thereIsSales();
        String customerId = thereIsCustomer("bartek");
        String productId = thereIsProduct("X", BigDecimal.valueOf(10));

        sales.addProductToCart(customerId, productId);
        sales.addProductToCart(customerId, productId);

        var acceptOfferRequest = new AcceptOfferRequest();
        acceptOfferRequest
                .setFirstname("bartek")
                .setLastname("poznanski")
                .setEmail("bartek@poznanski.pl");

        ReservationDetails reservationDetails = sales.acceptOffer(customerId, acceptOfferRequest);

        assertThat(reservationDetails.getPaymentUrl()).isNotBlank();
        assertThat(reservationDetails.getReservationId()).isNotBlank();

        assertPaymentHasBeenRegistered();
        asserThereIsReservationWithId(reservationDetails.getReservationId());
        asserReservationIsPending(reservationDetails.getReservationId());
        asserReservationIsDoneForCustomer(reservationDetails.getReservationId(), "Zuza", "Zimmer", "zuza@gmail.pl");
        asserReservationTotalMatchOffer(reservationDetails.getReservationId(), BigDecimal.valueOf(20));

    }

    private void asserReservationTotalMatchOffer(String reservationId, BigDecimal expectedTotal) {
        Reservation loaded = reservationRepository.load(reservationId)
                .get();

        assertThat(loaded.getTotal()).isEqualTo(expectedTotal);
    }

    private void asserReservationIsDoneForCustomer(String reservationId, String fname, String lname, String email) {
        Reservation loaded = reservationRepository.load(reservationId)
                .get();

        CustomerDetails clientData = loaded.getCustomerDetails();

        assertThat(clientData.getFirstName()).isEqualTo(fname);
        assertThat(clientData.getLastName()).isEqualTo(lname);
        assertThat(clientData.getEmail()).isEqualTo(email);
    }

    private void asserReservationIsPending(String reservationId) {
        Reservation loaded = reservationRepository.load(reservationId)
                .get();

        assertThat(loaded.isPending()).isTrue();
    }

    private void asserThereIsReservationWithId(String reservationId) {
        Optional<Reservation> loaded = reservationRepository.load(reservationId);

        assertThat(loaded).isPresent();
    }

    private void assertPaymentHasBeenRegistered() {

        assertThat(spyPaymentGateway.getRequestCount()).isEqualTo(1);
    }

    private String thereIsProduct(String productId, BigDecimal bigDecimal) {
        return productId;
    }

    private String thereIsCustomer(String id) {
        return id;
    }

    private SalesFacade thereIsSales() {
        return new SalesFacade(
                new CartStorage(),
                new OfferCalculator(),
                spyPaymentGateway,
                reservationRepository
        );
    }
}