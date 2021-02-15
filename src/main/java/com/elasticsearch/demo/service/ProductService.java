package com.elasticsearch.demo.service;

import com.elasticsearch.demo.model.Product;
import com.elasticsearch.demo.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;

    public SearchHits<Product> searchIndexesForPhrase(String phrase) {
        return productRepository.searchIndexesForPhrase(phrase);
    }

    public SearchHits<Product> searchProductsByName(String name) {
        return productRepository.searchProductsByName(name);
    }

    public SearchHits<Product> searchProductMatchPhrase(String name) {
        return productRepository.searchProductsMatchPhrase(name);
    }
}
