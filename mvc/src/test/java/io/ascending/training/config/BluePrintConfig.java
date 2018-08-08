package io.ascending.training.config;

import com.tobedevoured.modelcitizen.ModelFactory;
import com.tobedevoured.modelcitizen.RegisterBlueprintException;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BluePrintConfig {
    @Bean(name="modelFactory")
    public ModelFactory registerBluePrintsPackage() throws RegisterBlueprintException {
        ModelFactory modelFactory = new ModelFactory();
        modelFactory.setRegisterBlueprintsByPackage("io.ascending.training.blueprint");
        return modelFactory;
    }
}
