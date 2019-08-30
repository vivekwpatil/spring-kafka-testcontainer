package com.java.engineering.springkafkatestcontainer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class KafkaRestController {

    @Autowired
    private KafkaService kafkaService;

    @PostMapping
    public void sendMessage(String message){
        kafkaService.sendMessage(message);
    }

}
