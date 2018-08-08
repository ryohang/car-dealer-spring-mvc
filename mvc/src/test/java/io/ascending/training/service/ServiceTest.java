package io.ascending.training.service;

import io.ascending.training.TestBase;
import io.ascending.training.config.AppConfig;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

@WebAppConfiguration
@ContextConfiguration(classes = {AppConfig.class})
@RunWith(SpringJUnit4ClassRunner.class)
@ActiveProfiles("unit")
public class ServiceTest extends TestBase {
    @Autowired
    protected CarService carService;
    @Autowired
    protected ImageService imageService;
}
