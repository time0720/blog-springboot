package com.time.blog.config;

import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.client.ClientConfiguration;
import org.springframework.data.elasticsearch.client.RestClients;
import org.springframework.data.elasticsearch.config.AbstractElasticsearchConfiguration;

/**
 * @author mjw
 * @date 2023/5/16
 */
@Configuration
public class ElasticsearchConfig extends AbstractElasticsearchConfiguration {


    @Override
    @Bean
    public RestHighLevelClient elasticsearchClient() {
        final ClientConfiguration clientConfiguration = ClientConfiguration.builder()
                .connectedTo("119.91.22.108:9200")
                .build();
        return RestClients.create(clientConfiguration).rest();
    }
}
