package com.ascending.training.worker.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class ProcessService {
    protected final Logger logger = LoggerFactory.getLogger(getClass());
    @JmsListener(destination = "car-demo-dev")
    public void processMessage(Map<String,Object> msg){
        String msgType = (String)msg.get("msgType");
        String msgText = (String)msg.get("msgText");
        Long userID = Long.valueOf(msgText);
        logger.info("receive msgType: "+msgType);
        logger.info("receive msgText: "+msgText);
    }

}
