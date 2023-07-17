package com.job.todo.Configuration;

import com.couchbase.client.java.Cluster;
import com.couchbase.client.java.manager.query.CreatePrimaryQueryIndexOptions;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.couchbase.config.AbstractCouchbaseConfiguration;
import org.springframework.data.couchbase.repository.config.EnableCouchbaseRepositories;

import java.time.Duration;
import java.util.Arrays;
import com.couchbase.client.java.Cluster;
import com.couchbase.client.java.manager.query.CreatePrimaryQueryIndexOptions;
import com.couchbase.client.java.manager.query.DropPrimaryQueryIndexOptions;
import com.couchbase.client.java.manager.query.CreateQueryIndexOptions;
import com.couchbase.client.java.manager.query.WatchQueryIndexesOptions;


@Configuration
@EnableCouchbaseRepositories(basePackages = { "com.job.todo" })
public class CouchbaseConfiguration extends AbstractCouchbaseConfiguration {




    public static final String NODE_LIST = "localhost";
    public static final String BUCKET_NAME = "ToDo";
    public static final String BUCKET_USERNAME = "Administrator";
    public static final String BUCKET_PASSWORD = "adminix";

    Cluster cluster = Cluster.connect(NODE_LIST, BUCKET_USERNAME, BUCKET_PASSWORD);




    @Override
    public String getConnectionString() {
        return NODE_LIST;
    }

    @Override
    public String getUserName() {
        return BUCKET_USERNAME;
    }

    @Override
    public String getPassword() {
        return BUCKET_PASSWORD;
    }

    @Override
    public String getBucketName() {
        return BUCKET_NAME;
    }

    @Override
    protected boolean autoIndexCreation() {
        return true;
    }
}

