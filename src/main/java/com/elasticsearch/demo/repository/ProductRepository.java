package com.elasticsearch.demo.repository;

import com.elasticsearch.demo.model.Product;
import org.springframework.data.elasticsearch.core.SearchHits;

import java.util.List;

public interface ProductRepository {
    SearchHits<Product> searchIndexesForPhrase(String phrase);
    SearchHits<Product> searchProductsByName(String name);
    SearchHits<Product> searchProductsMatchPhrase(String phrase);
    void saveAll(List<Product> products);
    void deleteAll();
}
