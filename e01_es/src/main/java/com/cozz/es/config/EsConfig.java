package com.cozz.es.config;

import org.apache.http.HttpHost;
import org.elasticsearch.client.ElasticsearchClient;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

/**
 * @author zt
 * @date 2024/4/4
 **/
@Configuration
public class EsConfig {

    @Value("spring.elasticsearch.uris")
    private List<String> esUris;

    @Bean
    RestHighLevelClient getRestHighLevelClient() {
        return new RestHighLevelClient(RestClient.builder(
                new HttpHost("localhost", 9200, "http")
        ));
    }


}
