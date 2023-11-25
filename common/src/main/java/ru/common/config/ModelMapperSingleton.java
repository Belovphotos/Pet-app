package ru.common.config;

import org.modelmapper.ModelMapper;
import org.modelmapper.config.Configuration;
import org.modelmapper.convention.MatchingStrategies;
import org.modelmapper.spi.MatchingStrategy;

public class ModelMapperSingleton {
    private static ModelMapper instance;

    public static ModelMapper getInstance(){
        if (instance == null){
            synchronized (ModelMapperSingleton.class) {
                    instance = new ModelMapper();
                    instance.getConfiguration()
                            .setMatchingStrategy(MatchingStrategies.STRICT)
                            .setFieldMatchingEnabled(true)
                            .setSkipNullEnabled(true)
                            .setFieldAccessLevel(Configuration.AccessLevel.PRIVATE)
                            .setAmbiguityIgnored(true);
            }
        }
        return instance;
    }
}
