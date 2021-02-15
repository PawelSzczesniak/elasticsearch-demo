package com.elasticsearch.demo.repository;

import com.elasticsearch.demo.model.Product;
import lombok.RequiredArgsConstructor;

import static org.elasticsearch.index.query.QueryBuilders.matchPhraseQuery;
import static org.elasticsearch.index.query.QueryBuilders.matchQuery;

import org.elasticsearch.common.unit.Fuzziness;
import org.elasticsearch.index.query.Operator;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.mapping.IndexCoordinates;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.stereotype.Repository;

import java.util.List;

@RequiredArgsConstructor
@Repository
class ProductRepositoryImpl implements ProductRepository {
    private final ElasticsearchProductRepository elasticsearchProductRepository;
    private final ElasticsearchRestTemplate elasticsearchRestTemplate;

    private static final String NAME = "name";
    private static final String PRODUCTS_INDEX_NAME = "products";

    @Override
    public SearchHits<Product> searchIndexesForPhrase(String phrase) {

        NativeSearchQuery query = new NativeSearchQueryBuilder()
                .withQuery(matchQuery(NAME, phrase)
                        .operator(Operator.AND)
                        .fuzziness(Fuzziness.ONE))
                .build();

        return elasticsearchRestTemplate.search(query, Product.class, IndexCoordinates.of(PRODUCTS_INDEX_NAME));
    }

    @Override
    public SearchHits<Product> searchProductsByName(String name) {
        NativeSearchQuery searchQuery = new NativeSearchQueryBuilder().withQuery(matchQuery(NAME, name)).build();
        return elasticsearchRestTemplate.search(searchQuery, Product.class, IndexCoordinates.of(PRODUCTS_INDEX_NAME));
    }

    @Override
    public SearchHits<Product> searchProductsMatchPhrase(String phrase) {
        NativeSearchQuery nativeSearchQuery =
                new NativeSearchQueryBuilder().withFields(NAME).withQuery(matchPhraseQuery(NAME, phrase).slop(1)).build();
        return elasticsearchRestTemplate.search(nativeSearchQuery, Product.class, IndexCoordinates.of(PRODUCTS_INDEX_NAME));
    }

    @Override
    public void saveAll(List<Product> products) {
        elasticsearchProductRepository.saveAll(products);
    }

    @Override
    public void deleteAll() {
        elasticsearchProductRepository.deleteAll();
    }
}
