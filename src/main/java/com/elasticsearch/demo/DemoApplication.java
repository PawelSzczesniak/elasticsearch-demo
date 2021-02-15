package com.elasticsearch.demo;

import com.elasticsearch.demo.model.Product;
import com.elasticsearch.demo.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.elasticsearch.action.admin.indices.refresh.RefreshRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.GetIndexRequest;
import org.elasticsearch.common.xcontent.XContentType;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.List;

@SpringBootApplication
@RequiredArgsConstructor
public class DemoApplication {
    private final ProductRepository repository;
    private final RestHighLevelClient client;

    @Value("#{T(com.elasticsearch.demo.util.ResourceReader).readFileToString('classpath:/elasticsearch/mappings.json')}")
    private String json;

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    @PostConstruct
    public void buildIndex() throws IOException {
        if (!client.indices().exists(new GetIndexRequest(Product.INDEX_NAME), RequestOptions.DEFAULT)) {
            final CreateIndexRequest createIndexRequest = new CreateIndexRequest(Product.INDEX_NAME);
            createIndexRequest.mapping(json, XContentType.JSON);
            client.indices().create(createIndexRequest, RequestOptions.DEFAULT);
        } else {
            repository.deleteAll();
            client.indices().refresh(new RefreshRequest("products"), RequestOptions.DEFAULT);
        }

        saveExampleProducts();
    }

    private void saveExampleProducts() {
        repository.saveAll(List.of(Product.builder().name("Iphone SE").build(),
                Product.builder().name("Iphone 11").build(),
                Product.builder().name("Macbook Pro 13").build(),
                Product.builder().name("Macbook Pro 15").build(),
                Product.builder().name("Lenovo Yoga Tab 10").build(),
                Product.builder().name("Lenovo ThinkPad 14s").build(),
                Product.builder().name("Lenovo Yoga Slim 13").build(),
                Product.builder().name("Dell Latitude 5410").build(),
                Product.builder().name("Dell XPS 13").build(),
                Product.builder().name("Dell XPS 15").build()));
    }


}
