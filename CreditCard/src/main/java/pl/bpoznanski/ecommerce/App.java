package pl.bpoznanski.ecommerce;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import pl.bpoznanski.ecommerce.catalog.ProductCatalog;

@SpringBootApplication
public class App {
    public static void main (String[] args) {
        System.out.println("Here we go!!");
        SpringApplication.run(App.class, args);
    }
    @Bean
    ProductCatalog createMyCatalog(){
        var catalog = new ProductCatalog(new ArrayListProductStorage());
        catalog.addProduct("Lego set 8083", "Nice one");
        catalog.addProduct("Cobi set", "Nice one");
        return catalog;
    }



}
