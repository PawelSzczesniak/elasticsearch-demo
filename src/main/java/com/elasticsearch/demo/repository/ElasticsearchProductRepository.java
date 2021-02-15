package com.elasticsearch.demo.repository;

import com.elasticsearch.demo.model.Product;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface ElasticsearchProductRepository extends ElasticsearchRepository<Product, String> {
}
