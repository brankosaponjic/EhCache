package brankosaponjic.productdata;

import brankosaponjic.productdata.entity.Product;
import brankosaponjic.productdata.repository.ProductRepository;
import org.hibernate.Session;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.Optional;

@SpringBootTest
public class ProductDataCachingTest {

    @Autowired
    ProductRepository productRepository;

    @Autowired
    EntityManager entityManager;

    @Test
    @Transactional
    void testCaching() {
        Session session = entityManager.unwrap(Session.class);
        Product product = productRepository.findById(1).get();

        productRepository.findById(1);

        session.evict(product);

        productRepository.findById(1);
    }
}
