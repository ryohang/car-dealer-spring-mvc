package io.ascending.training.api.v1;


import com.fasterxml.jackson.annotation.JsonView;
import io.ascending.training.domain.Car;
import io.ascending.training.service.jms.MessageSQSService;
import io.ascending.training.service.jms.MessageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = {"/api/message"},produces = MediaType.APPLICATION_JSON_VALUE)
public class MessageController {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private MessageSQSService messageService;

    @RequestMapping(value="/{Id}" , method= RequestMethod.POST)
    public Boolean postFakeMessage(@PathVariable("Id") Long messageId) {
        logger.debug("receive a message id: "+messageId);
        messageService.sendMessage(messageId.toString(),5);
        return Boolean.TRUE;
    }

}
