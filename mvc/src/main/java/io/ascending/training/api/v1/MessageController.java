package io.ascending.training.api.v1;


import com.amazonaws.services.sqs.model.MessageAttributeValue;
import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.ascending.training.domain.Car;
import io.ascending.training.service.jms.MessageSQSService;
import io.ascending.training.service.jms.MessageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.jms.ObjectMessage;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(value = {"/api/message"},produces = MediaType.APPLICATION_JSON_VALUE)
public class MessageController {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private MessageSQSService messageService;

    @RequestMapping(value="/{Id}" , method= RequestMethod.POST)
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Boolean postFakeMessage(@PathVariable("Id") Long messageId, @RequestParam("domainName")String domainName) {
        logger.debug("receive a message id: "+messageId);
        ObjectMapper mapper = new ObjectMapper();
        String json = "";

        Map<String, String> map = new HashMap<>();
        map.put("id", messageId.toString());
        map.put("domainName",domainName);
//        map.put("body", "29");
        try {
            json = mapper.writeValueAsString(map);
            //"{\"id\",5,\"domainName\",\"User\"}"
            messageService.sendMessage(json,5);
        } catch (JsonProcessingException e) {
            logger.error("error message",e);
        }
        return Boolean.TRUE;
    }

}
