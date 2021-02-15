package com.elasticsearch.demo.configuration;

import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.client.ClientConfiguration;
import org.springframework.data.elasticsearch.client.RestClients;
import org.springframework.data.elasticsearch.config.AbstractElasticsearchConfiguration;

import javax.annotation.PreDestroy;
import java.io.IOException;

@Configuration
public class ElasticSearchConfig extends AbstractElasticsearchConfiguration {

    @Override
    @Bean(name = "elasticsearchHighLevelClient")
    public RestHighLevelClient elasticsearchClient() {
        final ClientConfiguration configuration = ClientConfiguration.builder()
                .connectedTo("localhost:9200")
                .build();
        return RestClients.create(configuration).rest();
    }


    @PreDestroy
    public void closeRestHighLevelClient() throws IOException {
        elasticsearchClient().close();
    }
}
