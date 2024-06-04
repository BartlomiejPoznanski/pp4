package pl.bpoznanski.ecommerce.sales;

import pl.bpoznanski.ecommerce.sales.cart.Cart;
import pl.bpoznanski.ecommerce.sales.cart.CartStorage;
import pl.bpoznanski.ecommerce.sales.payment.PaymentDatails;
import pl.bpoznanski.ecommerce.sales.payment.PaymentGateway;
import pl.bpoznanski.ecommerce.sales.payment.RegisterPaymentRequest;
import pl.bpoznanski.ecommerce.sales.reservation.AcceptOfferRequest;
import pl.bpoznanski.ecommerce.sales.offering.Offer;
import pl.bpoznanski.ecommerce.sales.offering.OfferCalculator;
import pl.bpoznanski.ecommerce.sales.reservation.Reservation;
import pl.bpoznanski.ecommerce.sales.reservation.ReservationDetails;
import pl.bpoznanski.ecommerce.sales.reservation.ReservationRepository;

import java.util.UUID;

public class SalesFacade {

    private CartStorage cartStorage;
    private OfferCalculator offerCalculator;
    private PaymentGateway paymentGateway;
    private ReservationRepository reservationRepository;

    public SalesFacade(CartStorage cartStorage, OfferCalculator offerCalculator,PaymentGateway paymentGateway, ReservationRepository reservationRepository) {
        this.cartStorage = cartStorage;
        this.offerCalculator = offerCalculator;
        this.paymentGateway = paymentGateway;
        this.reservationRepository = reservationRepository;
    }


    public Offer getCurrentOffer(String customerId) {
        Cart cart = cartStorage.loadForCustomer(customerId).orElse(Cart.empty());

        Offer offer = offerCalculator.calculate(cart.getItems());

        return offer;
    }

    public void addProductToCart(String customerId, String productId) {
        Cart cart = cartStorage.loadForCustomer(customerId)
                .orElse(Cart.empty());

        cart.addProduct(productId);
    }

    public ReservationDetails acceptOffer(String customerId, AcceptOfferRequest acceptOfferRequest) {
        String reservationId = UUID.randomUUID().toString();
        Offer offer = this.getCurrentOffer(customerId);

        PaymentDatails paymentDatails = paymentGateway.registerPayment(
                RegisterPaymentRequest.of(reservationId, acceptOfferRequest, offer.getTotal())
        );

        Reservation reservation = Reservation.of(
                reservationId,
                customerId,
                acceptOfferRequest,
                offer,
                paymentDatails);

        reservationRepository.add(reservation);

        return new ReservationDetails(reservationId, paymentDatails.getPaymentUrl());
    }
}