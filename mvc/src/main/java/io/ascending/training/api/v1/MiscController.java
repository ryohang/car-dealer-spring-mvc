package io.ascending.training.api.v1;

import io.ascending.training.domain.Image;
import io.ascending.training.domain.User;
import io.ascending.training.enumdef.WorkerMessageType;
import io.ascending.training.extend.exp.ServiceException;
import io.ascending.training.service.EmailService;
import io.ascending.training.service.ImageService;
import io.ascending.training.service.UserService;
import io.ascending.training.service.jms.MessageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.Map;

/**
 * Created By. User: hanqinghang
 */
@RestController("UtilRestV2")
@RequestMapping(value = "/api/misc")
public class MiscController {

    @Autowired
    private ImageService imageService;
    @Autowired
    private UserService userService;
    @Autowired
    private EmailService emailService;
    @Autowired
    private MessageService messageService;

    private final Logger logger = LoggerFactory.getLogger(getClass());
    
    @ResponseBody
    @RequestMapping(value = "/picture", method = RequestMethod.POST, consumes = {"multipart/form-data"})
    public Map<String, String> uploadPicture(@RequestParam(value = "pic") MultipartFile picture) {
        Map<String, String> result = new HashMap<>(1);
        try {
            Image image = imageService.saveFakeImage(picture);
            result.put("s3_url", image.getUrl());
            //result.put("s3_uuid", s3Image.getUuid());
        } catch (ServiceException e) {
            logger.error("error on saving record",e);
        }
        return result;
    }

    @RequestMapping(value = "/email", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void sendEmail(@RequestParam(value = "user_id") Long userId) {
        messageService.sendMessage(WorkerMessageType.UserSignUpMsg, String.valueOf(userId), 50000);
//        User newUser = userService.findById(userId).get();
//        emailService.sendInviteEmailToNewUser(newUser);
    }
}
