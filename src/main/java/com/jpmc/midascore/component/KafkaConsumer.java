package com.jpmc.midascore.component;

import com.jpmc.midascore.foundation.Transaction;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class KafkaConsumer {

    private static final Logger log = LoggerFactory.getLogger(KafkaConsumer.class);

    @KafkaListener(topics = "${general.kafka-topic}", groupId = "midas", containerFactory = "kafkaListenerContainerFactory")
    public void consume(Transaction transaction) {
        log.info("Received Transaction: {}", transaction);
        log.info("Amount: {}", transaction.getAmount());
    }
}
