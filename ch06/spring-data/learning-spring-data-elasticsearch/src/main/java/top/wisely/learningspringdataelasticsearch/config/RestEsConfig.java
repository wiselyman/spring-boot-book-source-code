package top.wisely.learningspringdataelasticsearch.config;

import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.config.AbstractElasticsearchConfiguration;

@Configuration
public class RestEsConfig extends AbstractElasticsearchConfiguration {
    private RestHighLevelClient restHighLevelClient;

    public RestEsConfig(RestHighLevelClient restHighLevelClient) {
        this.restHighLevelClient = restHighLevelClient;
    }

    @Override
    public RestHighLevelClient elasticsearchClient() {
        return this.restHighLevelClient;
    }
}
