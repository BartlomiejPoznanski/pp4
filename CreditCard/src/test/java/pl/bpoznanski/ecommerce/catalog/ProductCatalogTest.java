package pl.bpoznanski.ecommerce.catalog;
import static org.assertj.core.api.Assertions.assertThat;


import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;

public class ProductCatalogTest {
    @Test
    void itListsProducts() {
        ProductCatalog catalog = new ProductCatalog();

        List <Product> productList = catalog.allProducts();
        
        assertThat(productList).hasSize(0);
        
    }

    @Test
    void itAllowsAddProducts(){
        ProductCatalog catalog = new ProductCatalog();
        catalog.addProduct("Lego set 8083", "Nice One");

        List <Product> productList = catalog.allProducts();
        assertThat(productList).hasSize(1);


    }

    @Test
    void itLoadsProductDetails(){
        ProductCatalog catalog = new ProductCatalog();
        String id = catalog.addProduct("Lego set 8083", "Nice One");

        Product loadedproduct = catalog.getProductBy(id);
        assertThat(id).isEqualTo(loadedproduct.getId());

    }

    @Test
    void itAllowsToChangePrice(){
        ProductCatalog catalog = new ProductCatalog();
        String id = catalog.addProduct("Lego set 8083", "Nice One");

        catalog.changePrice(id, BigDecimal.valueOf(10.10));

        Product loadedProduct = catalog.getProductBy(id);
        assertThat(BigDecimal.valueOf(10.10))
                .isEqualTo(loadedProduct.getPrice());


    }
}
