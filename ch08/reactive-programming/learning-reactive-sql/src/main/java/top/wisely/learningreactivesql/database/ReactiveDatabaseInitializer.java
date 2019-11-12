package top.wisely.learningreactivesql.database;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.data.r2dbc.core.DatabaseClient;
import org.springframework.stereotype.Component;
import org.springframework.util.FileCopyUtils;
import reactor.core.publisher.Mono;

import java.io.InputStreamReader;
import java.io.Reader;

@Component
public class ReactiveDatabaseInitializer {

    private final DatabaseClient client;

    private final String sql;

    public ReactiveDatabaseInitializer(DatabaseClient client,
                                       @Value("classpath:schema.sql") Resource resource) throws Exception{
        this.client = client;
        try (Reader in = new InputStreamReader(resource.getInputStream())) {
            this.sql = FileCopyUtils.copyToString(in);
        }
    }

    public Mono<Void> initialize () {
        return client.execute().sql(this.sql).then()
                .onErrorResume(throwable -> Mono.empty());
    }

}
