package com.anyatech.workers_service.kafka;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;

import java.time.Duration;
import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class KafkaService {

    private final KafkaConsumer<String, String> consumer;
    private final KafkaProducer<String, String> producer;
    private final String inputTopic;
    private final String outputTopic;
    private final ExecutorService executor;
    private volatile boolean running = true;

    public KafkaService(String bootstrapServers,
                             String inputTopic,
                             String outputTopic,
                             int workerThreads) {

        this.inputTopic = inputTopic;
        this.outputTopic = outputTopic;

        Properties consumerProps = new Properties();
        consumerProps.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        consumerProps.put(ConsumerConfig.GROUP_ID_CONFIG, "high-throughput-pipe-group");
        consumerProps.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        consumerProps.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        consumerProps.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, "false");
        consumerProps.put(ConsumerConfig.MAX_POLL_RECORDS_CONFIG, "500"); // tune for throughput

        this.consumer = new KafkaConsumer<>(consumerProps);
        consumer.subscribe(Collections.singletonList(inputTopic));

        Properties producerProps = new Properties();
        producerProps.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        producerProps.put(ProducerConfig.ACKS_CONFIG, "all");
        producerProps.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        producerProps.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        producerProps.put(ProducerConfig.LINGER_MS_CONFIG, "5"); // small batching
        producerProps.put(ProducerConfig.BATCH_SIZE_CONFIG, "" + 64 * 1024); // tune for throughput

        this.producer = new KafkaProducer<>(producerProps);

        this.executor = Executors.newFixedThreadPool(workerThreads);
    }

    public void start() {
        try {
            while (running) {
                ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(500));
                if (records.isEmpty()) {
                    continue;
                }

                // For each record, process asynchronously
                List<CompletableFuture<Void>> futures = new ArrayList<>();

                for (ConsumerRecord<String, String> record : records) {
                    CompletableFuture<Void> future = CompletableFuture
                            .supplyAsync(() -> transform(record.value()), executor)
                            .thenAcceptAsync(transformedValue -> {
                                ProducerRecord<String, String> outRecord =
                                        new ProducerRecord<>(outputTopic, record.key(), transformedValue);
                                producer.send(outRecord); // async send
                            }, executor);

                    futures.add(future);
                }

                // Wait for all processing + sends for this batch to complete
                CompletableFuture<Void> allDone =
                        CompletableFuture.allOf(futures.toArray(new CompletableFuture[0]));
                allDone.join();

                // Commit offsets after successful processing of the batch
                consumer.commitAsync();
            }
        } finally {
            shutdown();
        }
    }

    // Simple transformation placeholder – in real life this could be CPU-heavy or I/O-bound
    private String transform(String value) {
        // Example: uppercase transformation
        return value.toUpperCase(Locale.ROOT);
    }

    public void stop() {
        running = false;
    }

    private void shutdown() {
        try {
            consumer.wakeup(); // in case we're blocked on poll
        } catch (Exception ignored) {
        }
        try {
            consumer.close();
        } catch (Exception ignored) {
        }
        try {
            producer.flush();
            producer.close();
        } catch (Exception ignored) {
        }
        executor.shutdown();
    }

    // Simple main for illustration
//    public static void main(String[] args) {
//        HighThroughputKafkaPipe pipe = new HighThroughputKafkaPipe(
//                "localhost:9092",
//                "input-topic",
//                "output-topic",
//                Runtime.getRuntime().availableProcessors()
//        );
//        pipe.start();
//    }




}
