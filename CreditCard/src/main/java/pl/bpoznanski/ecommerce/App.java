package pl.bpoznanski.ecommerce;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import pl.bpoznanski.ecommerce.catalog.ArrayListProductStorage;
import pl.bpoznanski.ecommerce.catalog.ProductCatalog;
import pl.bpoznanski.ecommerce.infrastructure.PayUPaymentGw;
import pl.bpoznanski.ecommerce.sales.cart.CartStorage;
import pl.bpoznanski.ecommerce.sales.offering.OfferCalculator;
import pl.bpoznanski.ecommerce.sales.SalesFacade;
import pl.bpoznanski.ecommerce.sales.reservation.ReservationRepository;

@SpringBootApplication
public class App {
    public static void main (String[] args) {
        System.out.println("Here we go");
        SpringApplication.run(App.class,args);
    }

    @Bean
    SalesFacade createSalesFacade() {
        return new SalesFacade(
                new CartStorage(),
                new OfferCalculator(),
                new PayUPaymentGw(),
                new ReservationRepository()
        );
    }

    @Bean
    ProductCatalog createMyCatalog() {
        var catalog = new ProductCatalog(new ArrayListProductStorage());
        catalog.addProduct("Lego set 8083","nice one ");
        catalog.addProduct("Cobi bricks","nice one ");

        return catalog;
    }
}