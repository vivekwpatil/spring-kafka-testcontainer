package com.java.engineering.springkafkatestcontainer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class KafkaRestController {

    @Autowired
    private KafkaService kafkaService;

    @PostMapping("/sendMessage")
    public void sendMessage(String message){
        kafkaService.sendMessage(message);
    }

    @PostMapping("/sendProtobufMessage")
    public void sendProtobufMessage(TestProto.test  test){
        kafkaService.sendProtocMessage(test);
    }

}
