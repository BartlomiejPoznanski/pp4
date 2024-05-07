package pl.bpoznanski.ecommerce;

import pl.bpoznanski.ecommerce.catalog.ProductCatalog;
import pl.bpoznanski.ecommerce.catalog.ProductStorage;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class App {

    private ProductStorage productStorage;

    public static void main(String[] args) {
        System.out.println("Here we go!!!");
        SpringApplication.run(App.class, args);
    }

    @Bean
    ProductCatalog createMyCatalog() {
        var catalog = new ProductCatalog(productStorage);
        catalog.addProduct("Lego set 8083", "Nice one");
        catalog.addProduct("Cobi Bricks","Nice one");
        return catalog;
    }
}
