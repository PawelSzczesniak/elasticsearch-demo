package com.elasticsearch.demo.controller;

import com.elasticsearch.demo.model.Product;
import com.elasticsearch.demo.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/products/")
public class ProductController {
    private final ProductService productService;

    @GetMapping("phrase/{phrase}")
    public SearchHits<Product> searchIndexesFromPhrase(@PathVariable String phrase) {
        return productService.searchIndexesForPhrase(phrase);
    }

    @GetMapping("{name}")
    public SearchHits<Product> searchProductsByName(@PathVariable String name) {
        return productService.searchProductsByName(name);
    }

    @GetMapping("match-phrase/{phrase}")
    public SearchHits<Product> searchProductsMatchPhrase(@PathVariable String phrase) {
        return productService.searchProductMatchPhrase(phrase);
    }
}
