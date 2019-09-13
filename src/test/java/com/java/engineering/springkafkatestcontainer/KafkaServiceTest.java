package com.java.engineering.springkafkatestcontainer;

import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.LongDeserializer;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.rnorth.ducttape.unreliables.Unreliables;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringRunner;
import org.testcontainers.containers.KafkaContainer;
import org.testcontainers.shaded.com.fasterxml.jackson.databind.deser.std.StringDeserializer;
import org.testcontainers.shaded.com.google.common.collect.ImmutableMap;

import java.util.Collections;
import java.util.Properties;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import static java.lang.Thread.sleep;
import static org.assertj.core.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class KafkaServiceTest {

    @Value("${kafka.bootstrap.address}")
    private String bootstrapServers;

    @Rule
    public KafkaContainer kafka = new KafkaContainer();

    @Autowired
    KafkaService kafkaService ;

    @Test
    public void whenKafkaIsCalled_testcontainerKafka_used() throws InterruptedException {
        kafkaService.sendMessage("Hello World String format");

        TestProto.test test=  TestProto.test.newBuilder().setMessage("Hello Java Engineers using protobuf").build();
        kafkaService.sendProtocMessage(test);
        sleep(10000);
        /*Unreliables.retryUntilTrue(10, TimeUnit.SECONDS, () -> {
            ConsumerRecords<String, String> records = createConsumer().poll(100);

            if (records.isEmpty()) {
                return false;
            }

            assertThat(records)
                .hasSize(1)
                .extracting(ConsumerRecord::topic, ConsumerRecord::key, ConsumerRecord::value)
                .containsExactly(tuple("helloworld", null, "Hello World"));

            return true;
        });*/

    }

    /*public static Consumer<String, String> createConsumer() {
        Properties props = new Properties();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "PLAINTEXT://192.168.99.100:32793");
        props.put(ConsumerConfig.GROUP_ID_CONFIG, "group1");
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        props.put(ConsumerConfig.MAX_POLL_RECORDS_CONFIG, 1);
        props.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, "false");
        props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
        Consumer<String, String> consumer = new KafkaConsumer<>(props);
        consumer.subscribe(Collections.singletonList("helloworld"));
        return consumer;
    }*/

}
