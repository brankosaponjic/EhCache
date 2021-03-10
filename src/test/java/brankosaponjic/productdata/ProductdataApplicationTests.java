package brankosaponjic.productdata;

import brankosaponjic.productdata.entity.Product;
import brankosaponjic.productdata.repository.ProductRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
class ProductdataApplicationTests {

    @Autowired
    ProductRepository productRepository;

    @Test
    void contextLoads() {
    }

    @Test
    void testCreate() {
        Product product = new Product();
        product.setId(1);
        product.setName("Iphone");
        product.setDesc("Awesome");
        product.setPrice(1000d);

        productRepository.save(product);
    }

    @Test
    void testRead() {
        Product product = productRepository.findById(3).get();
        Assertions.assertNotNull(product);
        Assertions.assertEquals("Iphone", product.getName());
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>" + product.getDesc());
    }

    @Test
    void testUpdate() {
        Product product = productRepository.findById(3).get();
        product.setPrice(1200d);
        productRepository.save(product);
    }

    @Test
    void testDelete() {
        productRepository.deleteById(4);
    }

    // method existsById
    @Test
    void testDeleteIfExists() {
        if (productRepository.existsById(5)) {
            System.out.println("Deleting a project");
            productRepository.deleteById(5);
        }
    }

    // method count - returns a number of rows in the table
    @Test
    void testCount() {
        System.out.println("Total records ============>>>>>>>>>>>>> " + productRepository.count());
    }

    @Test
    void testFindByName() {
        List<Product> products = productRepository.findByName("Iwatch");
        products.forEach(p -> {
            System.out.println(p.getName());
            System.out.println(p.getDesc());
            System.out.println(p.getPrice());
        });
    }

    @Test
    void testFindByNameAndDesc() {
        List<Product> products = productRepository.findByNameAndDesc("TV", "Sony corporation");
        products.forEach(p -> {
            System.out.println(p.getName());
            System.out.println(p.getDesc());
            System.out.println(p.getPrice());
        });
    }

    @Test
    void testFindByPriceGreaterThan() {
        List<Product> products = productRepository.findByPriceGreaterThan(1000d);
        products.forEach(p -> {
            System.out.println(p.getName());
            System.out.println(p.getDesc());
            System.out.println(p.getPrice());
        });
    }

    @Test
    void testFindByDescContains() {
        List<Product> products = productRepository.findByDescContains("ap");
        products.forEach(p -> {
            System.out.println(p.getName());
            System.out.println(p.getDesc());
            System.out.println(p.getPrice());
        });
    }

    @Test
    void testFindByPriceBetween() {
        List<Product> products = productRepository.findByPriceBetween(400d, 1200d);
        products.forEach(p -> {
            System.out.println(p.getName());
            System.out.println(p.getDesc());
            System.out.println(p.getPrice());
            System.out.println("===========");
        });
    }

    @Test
    void testFindByDescLike() {
        List<Product> products = productRepository.findByDescLike("%ap%");
        products.forEach(p -> {
            System.out.println(p.getName());
            System.out.println(p.getDesc());
            System.out.println(p.getPrice());
            System.out.println("===========");
        });
    }

    @Test
    void testFindByIdIn() {
        Pageable pageable = PageRequest.of(1,3, Sort.Direction.DESC, "price");
        List<Product> products = productRepository.findByIdIn(Arrays.asList(2,3,5,12,9,7), pageable);
        products.forEach(p -> {
            System.out.println(p.getName());
            System.out.println(p.getDesc());
            System.out.println(p.getPrice());
            System.out.println("===========");
        });
    }

    //==============================================
    // Paging and Sorting
    @Test
    void testFindAllPaging() {
        Pageable pageable = PageRequest.of(3,2);
        Page<Product> results = productRepository.findAll(pageable);
        results.forEach(p -> {
            System.out.println(p.getName());
            System.out.println("=========");
        });
    }

    // Sorting
    @Test
    void testFindAllSorting() {
//        productRepository.findAll(Sort.by("name")).forEach(product -> System.out.println(product.getName()));
        productRepository.findAll(Sort.by(Sort.Direction.DESC,"name")).forEach(p -> System.out.println(p.getName()));
    }

    // Sorting by multiple properties
    @Test
    void testFindAllSortingByNameAndPrice() {
        productRepository.findAll(Sort.by(Sort.Direction.DESC,"name", "price"))
                .forEach(p -> {
                    System.out.println(p.getName());
                });
    }

    // Sorting by multiple properties and directions
    @Test
    void testFindAllSortingByMultiplePropsAndDirections() {
        productRepository.findAll(Sort.by(
                Sort.Order.desc("name"),
                Sort.Order.desc("price")))
                .forEach(p -> {
                    System.out.println(p.getName());
                    System.out.println(p.getPrice());
                });
    }

    // Paging and Sorting in the sametime
    @Test
    void testFindAllPagingAndSorting() {
        Pageable pageable = PageRequest.of(0,2, Sort.Direction.DESC, "name");
        productRepository.findAll(pageable).forEach(p -> {
            System.out.println(p.getName());
            System.out.println(p.getPrice());
        });
    }

    //=======================================
    // Storing procedures
    @Test
    void testFindAllProducts() {
        System.out.println(productRepository.findAllProducts());
    }

    @Test
    void testFindAllProductsByPrice() {
        System.out.println(productRepository.findAllProductsByPrice(800));
    }

    @Test
    void testFindAllProductsCountByPrice() {
        System.out.println(productRepository.findAllProductsCountByPrice(800));
    }
}
