package com.jpmc.midascore.component;

import com.jpmc.midascore.foundation.Transaction;
import com.jpmc.midascore.service.TransactionService;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class KafkaConsumer {

    private static final Logger log = LoggerFactory.getLogger(KafkaConsumer.class);
    private final TransactionService transactionService;

    public KafkaConsumer(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @KafkaListener(topics = "${general.kafka-topic}", groupId = "midas", containerFactory = "kafkaListenerContainerFactory")
    public void consume(Transaction transaction) {
        log.info("Received Transaction: {}", transaction);
        boolean success = transactionService.processTransaction(transaction);

        if (success) {
            log.info("✅ Transaction processed successfully: {}", transaction);
        } else {
            log.warn("❌ Transaction discarded: {}", transaction);
        }
    }
}
