package com.contactbook.ContactBookApplication;

import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class KafkaListeners {

    private static final Logger LOGGER =
            LoggerFactory.getLogger(KafkaListener.class);

    @Autowired
    private RestHighLevelClient client;

    /*
    TODO spring annotation for elastic search transactions
     */

    @KafkaListener(topics = "profile", groupId = "group-id")
    private void listen(String message) {

        LOGGER.info("Received Messasge in group - group-id: " + message);

        IndexRequest indexRequest = new IndexRequest("profile","profile").source(message, XContentType.JSON);

        IndexResponse indexResponse = null;
        try {
            indexResponse = client.index(indexRequest, RequestOptions.DEFAULT);
            String id = indexResponse.getId();

            LOGGER.info(id);

            client.close();
        } catch (IOException e) {
            e.printStackTrace();
        }





    }

}
