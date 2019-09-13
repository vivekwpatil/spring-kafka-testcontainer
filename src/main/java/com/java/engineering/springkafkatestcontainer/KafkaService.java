package com.java.engineering.springkafkatestcontainer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class KafkaService {

    @Autowired
    KafkaTemplate<String, String> kafkaTemplate;

    @Autowired
    KafkaTemplate<String, TestProto.test> kafkaTemplate2;

    public void sendMessage(String message) {
        kafkaTemplate.send("HelloWorld", message);
    }

    public void sendProtocMessage(TestProto.test  test) {
        //TestProto.test test=  TestProto.test.newBuilder().setMessage("helloworld using protobuf").build();
        kafkaTemplate2.send("HelloWorld",test);

    }


}
