package com.elasticsearch.demo.service;

import com.elasticsearch.demo.model.Product;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.test.context.ActiveProfiles;
import org.testcontainers.elasticsearch.ElasticsearchContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

@Testcontainers
@SpringBootTest
@ActiveProfiles("test")
class ProductServiceWithContainersTests {

    @Autowired
    ProductService productService;

    @Container
    private static final ElasticsearchContainer CONTAINER = new ElasticsearchContainer("docker.elastic.co/elasticsearch/elasticsearch:7.11.0");

    static {
        CONTAINER.start();
    }

    @Test
    void shouldReturnAllProducts() {
        SearchHits<Product> searchHits = productService.searchProductsByName("iphone");
        Assertions.assertFalse(searchHits.isEmpty());
        Assertions.assertEquals(searchHits.getTotalHits(), 2);
    }
}
